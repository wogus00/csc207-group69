package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CollectionsTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;
    @Mock
    private DocumentReference mockDocumentReference;
    @Mock
    private ApiFuture<DocumentSnapshot> mockApiFuture;
    @Mock
    private DocumentSnapshot mockDocumentSnapshot;

    private Collections collections;
    @Mock
    private Firestore mockFirestore;
    @Mock
    private CollectionReference mockIDCollectionReference;
    @Mock
    private CollectionReference mockCollection1Reference;
    @Mock
    private CollectionReference mockCollection2Reference;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockFirebaseAccessObject.db = mockFirestore;
        when(mockFirestore.collection("IDCollection")).thenReturn(mockIDCollectionReference);
        when(mockFirebaseAccessObject.db.collection("IDCollection").document("IDCollection")).thenReturn(mockDocumentReference);
        when(mockDocumentReference.get()).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mockDocumentSnapshot);
        when(mockDocumentSnapshot.get("IDCollection")).thenReturn(new ArrayList<String>() {{
            add("Collection1");
            add("Collection2");
        }});

        when(mockFirestore.collection("Collection1")).thenReturn(mockCollection1Reference);
        when(mockFirestore.collection("Collection2")).thenReturn(mockCollection2Reference);
        collections = new Collections(mockFirebaseAccessObject);
    }

    @Test
    public void testIterator() {
        Iterator<CollectionReference> it = collections.iterator();
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertTrue(it.hasNext());
        assertNotNull(it.next());
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorThrowsException() {
        Iterator<CollectionReference> it = collections.iterator();
        while (it.hasNext()) {
            it.next();
        }
        it.next(); // This should throw the NoSuchElementException
    }
}
