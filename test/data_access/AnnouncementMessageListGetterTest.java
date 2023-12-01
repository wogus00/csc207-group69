package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnnouncementMessageListGetterTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;
    @Mock
    private Firestore mockFirestore;
    @Mock
    private ApiFuture<QuerySnapshot> mockApiFuture;
    @Mock
    private QuerySnapshot mockQuerySnapshot;
    @Mock
    private QueryDocumentSnapshot mockDocumentSnapshot;

    private AnnouncementMessageListGetter getter;

    @Mock
    private Query mockQuery;

    @Mock
    private CollectionReference mockCollectionReference;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);
        mockFirebaseAccessObject.db = mockFirestore;
        when(mockFirestore.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.whereEqualTo(anyString(),anyString())).thenReturn(mockQuery);
        when(mockQuery.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockQuerySnapshot);
        getter = new AnnouncementMessageListGetter();
    }

    @Test
    public void testGetInfoList() {
        // Mock the query snapshot to return the single document snapshot

        // Mock the document snapshot for Firestore
        when(mockDocumentSnapshot.getString("message")).thenReturn("Announcement Message");
        when(mockDocumentSnapshot.get("creationTime")).thenReturn(Timestamp.now());
        List<QueryDocumentSnapshot> documentSnapshots = new ArrayList<>();
        documentSnapshots.add(mockDocumentSnapshot);

        // Mock the query snapshot to return the single document snapshot
        when(mockQuerySnapshot.getDocuments()).thenReturn(documentSnapshots);



        // Execute the method to test
        List<String> result = getter.getInfoList("TestProject", mockFirebaseAccessObject);

        // Assertions
        assertEquals(1, result.size());
        assertEquals("Announcement Message", result.get(0));
    }

    @Test
    public void testGetInfoListThrowsInterruptedException() throws ExecutionException, InterruptedException {
        // Set up the condition for the InterruptedException
        when(mockFirebaseAccessObject.db.collection("announcements").whereEqualTo("projectName", "projectName").get())
                .thenReturn(mockApiFuture);
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
        when(mockFirebaseAccessObject.db.collection("announcements").whereEqualTo("projectName", "projectName").get())
                .thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenThrow(ExecutionException.class);

        // Define the expected exception
        thrown.expect(RuntimeException.class);
        thrown.expectCause(isA(ExecutionException.class));

        // Call the method that is expected to throw the exception
        getter.getInfoList("projectName", mockFirebaseAccessObject);
    }
}

