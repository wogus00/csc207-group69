package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.Map;
import java.util.HashMap;

public class AnnouncementListGetterTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;
    @Mock
    private DocumentReference mockDocumentReference;
    @Mock
    private Firestore mockFirestore;
    @Mock
    private ApiFuture<DocumentSnapshot> mockApiFuture;
    @Mock
    private DocumentSnapshot mockDocumentSnapshot;

    @Mock
    private CollectionReference mockCollectionReference;

    AnnouncementListGetter strategy;

    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);
        mockFirebaseAccessObject.db = mockFirestore; // Replace the real Firestore instance with the mock
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockDocumentSnapshot);
        strategy = new AnnouncementListGetter();
    }

    @Test
    public void testGetInfoList() {
        // Setup mock data
        Map<String, Object> mockData = new HashMap<>();

        Map<String, Object> announcementData = new HashMap<>();
        announcementData.put("id", "123e4567-e89b-12d3-a456-426655440000");
        announcementData.put("title", "Test Tittle");
        announcementData.put("message", "Test Announcement Message");
        announcementData.put("creationTime",  "2023-11-26T14:45:20");
        announcementData.put("author", "Test Author");

        Map<String, Object> announcementData2 = new HashMap<>();
        announcementData2.put("id", "92e6b5d4-2d83-4b6a-b9df-4779b7b60a07");
        announcementData2.put("title", "Test Tittle 2");
        announcementData2.put("message", "Test Announcement Message 2");
        announcementData2.put("creationTime",  "2023-11-26T15:37:20");
        announcementData2.put("author", "Test Author");


        mockData.put("Test Announcement", announcementData);
        mockData.put("Test Announcement 2", announcementData2);
        // Populate mockData as needed to simulate Firestore data
        when(mockDocumentSnapshot.getData()).thenReturn(mockData);


        List<String> result = strategy.getInfoList("TestProject", mockFirebaseAccessObject);

        assertEquals("Test Announcement Message", result.get(0));
        assertEquals("Test Announcement Message 2", result.get(1));
    }
}
