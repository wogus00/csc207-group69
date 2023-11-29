package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MeetingListGetter implements InfoListGetter {

    @Override
    public List<String> getInfoList(String projectName, FirebaseAccessObject firebaseAccessObject) {
        ArrayList<String> list = new ArrayList<>();

        DocumentReference docRef = firebaseAccessObject.db.collection(projectName).document("meetingInfo");
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
        list.addAll(fields.keySet());
        ArrayList<String> keys = new ArrayList<>();
        for (String key: list) {
            Map<String, Object> entry = (Map<String, Object>) fields.get(key);
            String dateStr = (String) entry.get("meetingDate");
            String timeStr = (String) entry.get("endTime");
            // Define the formatters for parsing
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // Parse the date and time separately
            LocalDate date = LocalDate.parse(dateStr, dateFormatter);
            LocalTime time = LocalTime.parse(timeStr, timeFormatter);

            // Combine them into a LocalDateTime object
            LocalDateTime combinedDateTime = LocalDateTime.of(date, time);

            // Get the current LocalDateTime
            LocalDateTime currentDateTime = LocalDateTime.now();
            if (!combinedDateTime.isBefore(currentDateTime)) {
                keys.add(key);
            }
        }
        return keys;
    }

}
