package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import data_access.FirebaseAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;
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
    private DocumentReference mockDocumentReference2;
    @Mock
    private DocumentReference mockDocumentReference3;
    @Mock
    private DocumentReference mockDocumentReference4;
    @Mock
    private ApiFuture<WriteResult> mockWriteResult;

    private FirebaseAccessObject firebaseAccessObject;

    @Mock
    private CollectionReference mockCollectionReference;
    @Mock
    private DocumentSnapshot mockDocumentSnapshot;
    @Mock
    private Collections mockCollections;
    @Mock
    private Iterator<CollectionReference> mockIterator;

    @Mock
    private ProjectInfoAccessorImplementation mockProjectInfoAccessor;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        firebaseAccessObject = new FirebaseAccessObject();
        firebaseAccessObject.db = mockFirestore; // Replace the real Firestore instance with the mock
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockDocumentSnapshotFuture);

    }

    @Test
    public void testGetProjectInfo() throws InterruptedException, ExecutionException {

        ArrayList<String> members = new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com"));
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.getString("leaderEmail")).thenReturn("leader@example.com");
        when(mockDocumentSnapshot.get("memberEmails")).thenReturn(members);
        Project project = firebaseAccessObject.getProjectInfo("Test Project");

        assertNotNull(project);
        assertEquals("leader@example.com", project.getLeaderEmail());
        assertTrue(project.getMemberEmails().contains("member1@example.com"));
        assertTrue(project.getMemberEmails().contains("member2@example.com"));
    }

    @Test
    public void testMeetingNameExists_Exists() throws Exception {
        // Setup test data
        String projectName = "ProjectX";
        String meetingName = "Meeting1";
        Map<String, Object> meetings = new HashMap<>();
        meetings.put(meetingName, new HashMap<>()); // Add meetingName to the mock data

        // Mock the DocumentSnapshot to return our test data
        when(mockDocumentSnapshot.exists()).thenReturn(true);
        when(mockDocumentSnapshot.getData()).thenReturn(meetings);
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);

        // Call the method
        boolean exists = firebaseAccessObject.meetingNameExists(projectName, meetingName);

        // Assert the result
        assertTrue(exists);
    }

    @Test
    public void testMeetingNameExists_DoesNotExist() throws Exception {
        // Setup test data
        String projectName = "ProjectX";
        String meetingName = "Meeting1";
        Map<String, Object> meetings = new HashMap<>(); // No meetingName in the mock data

        // Mock the DocumentSnapshot to return our test data
        when(mockDocumentSnapshot.exists()).thenReturn(true);
        when(mockDocumentSnapshot.getData()).thenReturn(meetings);
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);

        // Call the method
        boolean exists = firebaseAccessObject.meetingNameExists(projectName, meetingName);

        // Assert the result
        assertFalse(exists);
    }


    @Test
    public void testGetInfoList_InvalidType() {
        // Setup
        String projectName = "ProjectX";
        String type = "invalidType";

        // Call the method
        ArrayList<String> result = firebaseAccessObject.getInfoList(projectName, type);

        // Assert that the result is an empty list
        assertTrue(result.isEmpty());
    }



    @Test
    public void testExistsByName() throws InterruptedException, ExecutionException {
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.get("IDCollection")).thenReturn(new ArrayList<>(Arrays.asList("Test Project")));
        when(mockIterator.hasNext()).thenReturn(true, false); // Adjust based on your scenario
        when(mockIterator.next()).thenReturn(mockCollectionReference);
        when(mockCollectionReference.getId()).thenReturn("ExistingProject");

        assertTrue(firebaseAccessObject.existsByName("ExistingProject"));

        assertFalse(firebaseAccessObject.existsByName("NonExistingProject"));
    }


    @Test
    public void testSaveProject() throws InterruptedException, ExecutionException {
        String projectName = "Test Project";
        Project project = new CommonProject(projectName, "leader@example.com", new ArrayList<>());
        DocumentSnapshot mockIDInfoSnapshot = mock(DocumentSnapshot.class);

        when(mockFirestore.collection("IDCollection").document(anyString())).thenReturn(mockDocumentReference);
        when(mockFirestore.collection(projectName).document("projectInfo")).thenReturn(mockDocumentReference2);
        when(mockFirestore.collection(projectName).document("meetingInfo")).thenReturn(mockDocumentReference3);
        when(mockFirestore.collection(projectName).document("taskInfo")).thenReturn(mockDocumentReference4);
        when(mockDocumentReference.get()).thenReturn(mockDocumentSnapshotFuture);
        when(mockDocumentSnapshotFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.get("IDCollection")).thenReturn(new ArrayList<String>());

        firebaseAccessObject.save(project);

        // Verify that Firestore's collection and document methods are called with correct arguments for projectInfo
        verify(mockFirestore, times(6)).collection(projectName);
        verify(mockFirestore, times(2)).collection("IDCollection");
        verify(mockCollectionReference, times(1)).document("projectInfo");

        // Verify setting of project info
        ArgumentCaptor<Map<String, Object>> projectInfoCaptor = ArgumentCaptor.forClass(Map.class);
        verify(mockCollectionReference.document("projectInfo")).set(projectInfoCaptor.capture());
        Map<String, Object> projectInfo = projectInfoCaptor.getValue();
        assertEquals("Test Project", projectInfo.get("projectName"));
        assertEquals("leader@example.com", projectInfo.get("leaderEmail"));

        // Verify initialization of taskInfo and meetingInfo
        verify(mockCollectionReference.document("taskInfo")).set(any(Map.class));
        verify(mockCollectionReference.document("meetingInfo")).set(any(Map.class));

        // Verify update of IDCollection
        verify(mockFirestore.collection("IDCollection").document("IDCollection"), times(1)).set(any(Map.class));
    }


    @Test
    public void testSaveAnnouncementSuccessfully() throws Exception {
//        // Arrange
//        Announcement announcement = new CommonAnnouncement("Test Title1", "Test Message", LocalDateTime.now(), "Test Author", "TestId");
//
//        // Act
//        firebaseAccessObject.save("project name", announcement);
//
//        // Assert
//        verify(mockFirestore).collection("announcements");
//        verify(mockDocumentReference).set(any(Map.class));
        // Arrange
        Announcement announcement = new CommonAnnouncement("Test Title1", "Test Message", LocalDateTime.now(), "Test Author", "TestId");

        // Mocking DocumentReference and DocumentSnapshot
        DocumentSnapshot mockDocumentSnapshot = mock(DocumentSnapshot.class);
        ApiFuture<DocumentSnapshot> mockApiFuture = (ApiFuture<DocumentSnapshot>) mock(ApiFuture.class);
        DocumentReference mockDocumentReference = mock(DocumentReference.class);

        // Setup mocks
        when(mockFirestore.collection("announcements").document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockDocumentSnapshot);

        // You can set this to true or false based on what behavior you want to test
        when(mockDocumentSnapshot.exists()).thenReturn(true); // or false

        // Act
        firebaseAccessObject.save("project name", announcement);
    }

//    @Test
//    public void testSaveAnnouncementWithException() {
//        // Arrange
//        Announcement announcement = new CommonAnnouncement("Test Title", "Test Message", LocalDateTime.now(), "Test Author", "TestId");
//        when(mockDocumentReference.set(any(Map.class))).thenThrow(new RuntimeException("Firestore operation failed"));
//
//        try {
//            // Act
//            firebaseAccessObject.save("project name",announcement);
//            fail("Expected an RuntimeException to be thrown");
//        } catch (RuntimeException e) {
//            // Assert
//            assertEquals("Firestore operation failed", e.getMessage());
//        }
//    }

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
//        assertEquals("2023-01-01T12:00:00", result.getCreationTime());
        assertEquals("testId", result.getId());
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

}
