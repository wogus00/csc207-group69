package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.Map;
import java.util.HashMap;

public class MeetingListGetterTest {

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


    private MeetingListGetter getter;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);
        mockFirebaseAccessObject.db = mockFirestore; // Replace the real Firestore instance with the mock
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockDocumentSnapshot);
        getter = new MeetingListGetter();
    }

    @Test
    public void testGetInfoList() {
        // Setup mock data
        Map<String, Object> mockData = new HashMap<>();

        // Uncompleted task
        Map<String, Object> meetingData = new HashMap<>();
        meetingData.put("meetingName", "Test Meeting");
        meetingData.put("meetingDate", "01/01/2024");
        meetingData.put("endTime", "01:00");



        // Completed task that shouldn't be included


        mockData.put("Test Meeting",meetingData);
        // Populate mockData as needed to simulate Firestore data
        when(mockDocumentSnapshot.getData()).thenReturn(mockData);


        List<String> result = getter.getInfoList("TestProject", mockFirebaseAccessObject);

        assertEquals("Test Meeting", result.get(0));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetInfoListThrowsInterruptedException() throws ExecutionException, InterruptedException {
        // Set up the condition for the InterruptedException
        when(mockApiFuture.get()).thenThrow(InterruptedException.class);

        // Define the expected exception
        thrown.expect(RuntimeException.class);
        thrown.expectCause(isA(InterruptedException.class));

        // Call the method that is expected to throw the exception
        getter.getInfoList("projectName", mockFirebaseAccessObject);
    }

    @Test
    public void testGetInfoListThrowsExecutionException() throws ExecutionException, InterruptedException {
        // Set up the condition for the ExecutionException
        when(mockApiFuture.get()).thenThrow(ExecutionException.class);

        // Define the expected exception
        thrown.expect(RuntimeException.class);
        thrown.expectCause(isA(ExecutionException.class));

        // Call the method that is expected to throw the exception
        getter.getInfoList("projectName", mockFirebaseAccessObject);
    }
}
