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

public class TaskListGetterTest {

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


    private TaskListGetter strategy;


    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);
        mockFirebaseAccessObject.db = mockFirestore; // Replace the real Firestore instance with the mock
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockDocumentSnapshot);
        strategy = new TaskListGetter();
    }

    @Test
    public void testGetInfoList() {
        // Setup mock data
        Map<String, Object> mockData = new HashMap<>();

        // Uncompleted task
        Map<String, Object> taskData = new HashMap<>();
        taskData.put("taskName", "Test Task");
        taskData.put("supervisor", "supervisor@example.com");
        taskData.put("workingMemberList", "supervisor@example.com");
        taskData.put("deadline", null);
        taskData.put("comments", "Test Comment");
        taskData.put("status", false);

        // Completed task that shouldn't be included
        Map<String, Object> taskData2 = new HashMap<>();
        taskData2.put("taskName", "Test Task 2");
        taskData2.put("supervisor", "supervisor2@example.com");
        taskData2.put("workingMemberList", "supervisor2@example.com");
        taskData2.put("deadline", null);
        taskData2.put("comments", "Test Comment 2");
        taskData2.put("status", true);


        mockData.put("Test Task",taskData);
        mockData.put("Test Task 2", taskData2);
        // Populate mockData as needed to simulate Firestore data
        when(mockDocumentSnapshot.getData()).thenReturn(mockData);


        List<String> result = strategy.getInfoList("TestProject", mockFirebaseAccessObject);

        assertEquals("Test Task", result.get(0));
        assertEquals(1, result.size());
    }
}
