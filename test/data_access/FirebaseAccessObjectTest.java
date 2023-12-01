package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import data_access.FirebaseAccessObject;
import entity.Announcement;
import entity.CommonAnnouncement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FirebaseAccessObjectTest {

    @Mock
    private Firestore mockFirestore;

    @Mock
    private ApiFuture<DocumentSnapshot> mockDocumentSnapshotFuture;
    @Mock
    private DocumentReference mockDocumentReference;
    @Mock
    private ApiFuture<WriteResult> mockWriteResult;

    private FirebaseAccessObject firebaseAccessObject;

    @Mock
    private CollectionReference mockCollectionReference;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        firebaseAccessObject = new FirebaseAccessObject();
        firebaseAccessObject.db = mockFirestore; // Replace the real Firestore instance with the mock
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockDocumentSnapshotFuture);
        ;
    }

    @Test
    public void testSaveAnnouncementSuccessfully() throws Exception {
        // Arrange
        CommonAnnouncement announcement = new CommonAnnouncement("Test Title1", "Test Message", LocalDateTime.now(), "Test Author", "TestId");

        // Act
        firebaseAccessObject.save(announcement);

        // Assert
        verify(mockFirestore).collection("announcements");
        verify(mockDocumentReference).set(any(Map.class));
    }

    @Test
    public void testSaveAnnouncementWithException() {
        // Arrange
        CommonAnnouncement announcement = new CommonAnnouncement("Test Title", "Test Message", LocalDateTime.now(), "Test Author", "TestId");
        when(mockDocumentReference.set(any(Map.class))).thenThrow(new RuntimeException("Firestore operation failed"));

        try {
            // Act
            firebaseAccessObject.save(announcement);
            fail("Expected an RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Assert
            assertEquals("Firestore operation failed", e.getMessage());
        }
    }

    @Test
    public void testDeleteAnnouncementSuccessfully() throws Exception {
        // Arrange
        String announcementId = "testId";
        when(mockDocumentReference.delete()).thenReturn(mockWriteResult);

        // Act
        boolean result = firebaseAccessObject.deleteAnnouncement(announcementId);

        // Assert
        assertTrue(result);
        verify(mockFirestore.collection("announcements").document(announcementId)).delete();
    }

    @Test
    public void testDeleteAnnouncementFailure() throws Exception {
        // Arrange
        String announcementId = "testId";
        when(mockDocumentReference.delete()).thenThrow(new RuntimeException("Deletion failed"));

        // Act
        boolean result = firebaseAccessObject.deleteAnnouncement(announcementId);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetAnnouncementByIdFound() throws Exception {
        // Arrange
        String announcementId = "testId";
        DocumentSnapshot mockDocumentSnapshot = mock(DocumentSnapshot.class);
        when(mockDocumentSnapshot.exists()).thenReturn(true);
        when(mockDocumentSnapshot.getString("title")).thenReturn("Test Title");
        when(mockDocumentSnapshot.getString("message")).thenReturn("Test Message");
        when(mockDocumentSnapshot.getString("author")).thenReturn("Test Author");
        when(mockDocumentSnapshot.getString("creationTime")).thenReturn("2023-01-01T12:00:00");
        when(mockDocumentSnapshot.getId()).thenReturn(announcementId);
        when(mockDocumentReference.get()).thenReturn(mockDocumentSnapshotFuture);
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockFirestore.collection("announcements").document(announcementId)).thenReturn(mockDocumentReference);

        // Act
        Announcement result = firebaseAccessObject.getAnnouncementById(announcementId);

        // Assert
        assertNotNull(result);
        assertEquals("Test Title", result.getAnnouncementTitle());
        assertEquals("Test Message", result.getMessage());
        assertEquals("Test Author", result.getAuthor());
        assertEquals("2023-01-01T12:00:00", result.getCreationTime());
        assertEquals("testID", result.getId());
    }

    @Test
    public void testGetAnnouncementByIdNotFound() throws Exception {
        // Arrange
        String announcementId = "testId";
        DocumentSnapshot mockDocumentSnapshot = mock(DocumentSnapshot.class);
        when(mockDocumentSnapshot.exists()).thenReturn(false);
        when(mockDocumentSnapshot.getId()).thenReturn(announcementId);
        when(mockDocumentReference.get()).thenReturn(mockDocumentSnapshotFuture);
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockFirestore.collection("announcements").document(announcementId)).thenReturn(mockDocumentReference);


        // Act
        Announcement result = firebaseAccessObject.getAnnouncementById(announcementId);

        // Assert
        assertNull(result);
    }

    @Test
    public void testGetProjectNameFromAnnouncementId_Success() throws ExecutionException, InterruptedException {
        // Mock Firestore behavior
        String expectedProjectName = "Project X";
        DocumentSnapshot documentSnapshotMock = Mockito.mock(DocumentSnapshot.class);
        Mockito.when(documentSnapshotMock.exists()).thenReturn(true);
        Mockito.when(documentSnapshotMock.getString("projectName")).thenReturn(expectedProjectName);
        ApiFuture<DocumentSnapshot> futureMock = Mockito.mock(ApiFuture.class);
        Mockito.when(futureMock.get()).thenReturn(documentSnapshotMock);
        CollectionReference collectionRefMock = Mockito.mock(CollectionReference.class);
        DocumentReference docRefMock = Mockito.mock(DocumentReference.class);
        Mockito.when(mockFirestore.collection("announcements")).thenReturn(collectionRefMock);
        Mockito.when(collectionRefMock.document(any(String.class))).thenReturn(docRefMock);
        Mockito.when(docRefMock.get()).thenReturn(futureMock);

        // Call the method and assert results
        String result = firebaseAccessObject.getProjectNameFromAnnouncementId("validAnnouncementId");
        assertEquals(expectedProjectName, result);
    }

    @Test
    public void testGetProjectNameFromAnnouncementId_NonExistentAnnouncement() throws ExecutionException, InterruptedException {
        // Mock Firestore behavior for non-existent announcement
        DocumentSnapshot documentSnapshotMock = Mockito.mock(DocumentSnapshot.class);
        Mockito.when(documentSnapshotMock.exists()).thenReturn(false);
        ApiFuture<DocumentSnapshot> futureMock = Mockito.mock(ApiFuture.class);
        Mockito.when(futureMock.get()).thenReturn(documentSnapshotMock);
        CollectionReference collectionRefMock = Mockito.mock(CollectionReference.class);
        DocumentReference docRefMock = Mockito.mock(DocumentReference.class);
        Mockito.when(mockFirestore.collection("announcements")).thenReturn(collectionRefMock);
        Mockito.when(collectionRefMock.document(any(String.class))).thenReturn(docRefMock);
        Mockito.when(docRefMock.get()).thenReturn(futureMock);

        // Call the method
        String result = firebaseAccessObject.getProjectNameFromAnnouncementId("nonExistentAnnouncementId");
        assertNull(result);
    }

    @Test
    public void testGetProjectNameFromAnnouncementId_MissingOrEmptyProjectName() throws ExecutionException, InterruptedException {
        // Mock Firestore behavior for missing or empty project name
        DocumentSnapshot documentSnapshotMock = Mockito.mock(DocumentSnapshot.class);
        Mockito.when(documentSnapshotMock.exists()).thenReturn(true);
        Mockito.when(documentSnapshotMock.getString("projectName")).thenReturn(null); // For missing project name
        // Mockito.when(documentSnapshotMock.getString("projectName")).thenReturn(""); // Uncomment for empty project name
        ApiFuture<DocumentSnapshot> futureMock = Mockito.mock(ApiFuture.class);
        Mockito.when(futureMock.get()).thenReturn(documentSnapshotMock);
        CollectionReference collectionRefMock = Mockito.mock(CollectionReference.class);
        DocumentReference docRefMock = Mockito.mock(DocumentReference.class);
        Mockito.when(mockFirestore.collection("announcements")).thenReturn(collectionRefMock);
        Mockito.when(collectionRefMock.document(any(String.class))).thenReturn(docRefMock);
        Mockito.when(docRefMock.get()).thenReturn(futureMock);

        // Call the method
        String result = firebaseAccessObject.getProjectNameFromAnnouncementId("validAnnouncementIdWithNoProjectName");
        assertNull(result);
    }

    @Test
    public void testGetProjectNameFromAnnouncementId_ExceptionHandling() {
        // Mock an exception being thrown during Firestore interactions
        DocumentReference docRefMock = Mockito.mock(DocumentReference.class);
        Mockito.when(mockFirestore.collection("announcements")).thenReturn(Mockito.mock(CollectionReference.class));
        Mockito.when(mockFirestore.collection("announcements").document(any(String.class))).thenReturn(docRefMock);
        Mockito.when(docRefMock.get()).thenThrow(new RuntimeException("Firestore access error"));

        // Call the method and expect it to handle the exception gracefully
        String result = null;
        try {
            result = firebaseAccessObject.getProjectNameFromAnnouncementId("announcementIdWithException");
        } catch (Exception e) {
            // Optionally assert something about the exception
        }
        assertNull(result);
    }







    // Additional test cases as necessary
}
