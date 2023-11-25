package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

public class Collections implements Iterable<CollectionReference> {

    FirebaseAccessObject firebaseAccessObject;
    String[] collectionList;

    public Collections (FirebaseAccessObject firebaseAccessObject) {
        this.firebaseAccessObject = firebaseAccessObject;
        DocumentReference docRef = firebaseAccessObject.db.collection("IDCollection").document("IDCollection");
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot IDInfo = null;
        try {
            IDInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        };
        ArrayList<String> arrayList = (ArrayList<String>) IDInfo.get("IDCollection");
        if (arrayList == null) {
            this.collectionList = new String[0];
        } else {
            this.collectionList = arrayList.toArray(new String[0]);
        }
    }

    @Override
    public Iterator<CollectionReference> iterator() {
        return new DataBaseIterator();
    }
    public class DataBaseIterator implements Iterator<CollectionReference>{

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < collectionList.length ;
        }
        @Override
        public CollectionReference next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            } else {
                CollectionReference collectionReference = firebaseAccessObject.db.collection(collectionList[index++]);
                return collectionReference;
            }
        }
    };

}

