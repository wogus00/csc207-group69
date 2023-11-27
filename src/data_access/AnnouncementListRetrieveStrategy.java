package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AnnouncementListRetrieveStrategy implements InfoListRetrieveStrategy{

    @Override
    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject) {
        ArrayList<String> list = new ArrayList<>();

        DocumentReference docRef = firebaseAccessObject.db.collection(projectName).document("announcementInfo");
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot typeInfo = null;
        try {
            typeInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        };
        Map<String, Object> fields = typeInfo.getData();
        Map<String, String> Time_to_Message = new HashMap<>();
        list.addAll(fields.keySet());
        for (String key: list) {
            Map<String, Object> entry = (Map<String, Object>) fields.get(key);
            String message = (String) entry.get("message");
            String creationTime = (String) entry.get("creationTime");
            Time_to_Message.put(creationTime, message);
        }
        ArrayList<String> timeList = new ArrayList<>();
        timeList.addAll(Time_to_Message.keySet());
        Collections.sort(timeList);
        list = new ArrayList<>();
        for (String key: timeList) {
            list.add(Time_to_Message.get(key));
        }
        return list;
    }
}
