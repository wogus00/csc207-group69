package data_access;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CollectionName implements Iterable<CollectionReference> {

    FirebaseAccessObject firebaseAccessObject;
    String[] collectionList;

    public CollectionName (String[] collectionList, FirebaseAccessObject firebaseAccessObject) {
        this.collectionList = collectionList;
        this.firebaseAccessObject = firebaseAccessObject;
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

