package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TaskListGetter implements InfoListGetter {

    @Override
    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject) {
        ArrayList<String> list = new ArrayList<>();
        DocumentReference docRef = firebaseAccessObject.db.collection(projectName).document("taskInfo");
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot typeInfo = null;
        try {
            typeInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> fields = typeInfo.getData();
        list.addAll(fields.keySet());
        ArrayList<String> keys = new ArrayList<>();
        for (String key: list) {
            Map<String, Object> entry = (Map<String, Object>) fields.get(key);
            if (entry.get("status").equals(false)) {
                keys.add(key);
            }
        }
        return keys;
    }

}
