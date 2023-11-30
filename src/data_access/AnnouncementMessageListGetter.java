package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.*;
import java.util.concurrent.ExecutionException;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AnnouncementMessageListGetter implements InfoListGetter {

    @Override
    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject) {
        ApiFuture<QuerySnapshot> query = firebaseAccessObject.db.collection("announcements").whereEqualTo("projectName", projectName).get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        Map<String, String> Time_to_Message = new HashMap<>();
        for (QueryDocumentSnapshot document : documents) {;
            String message = document.getString("message");
            Timestamp creationTime = (Timestamp) document.get("creationTime");
            Time_to_Message.put(String.valueOf(creationTime), message);
        }
        ArrayList<String> timeList = new ArrayList<>();
        timeList.addAll(Time_to_Message.keySet());
        Collections.sort(timeList);
        ArrayList<String> list = new ArrayList<>();
        for (String key: timeList) {
            list.add(Time_to_Message.get(key));
        }
        return list;
    }
}
