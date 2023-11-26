package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.CommonProject;
import entity.Meeting;
import entity.Project;
import entity.ProjectFactory;
import entity.Task;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.create_meeting.CreateMeetingDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.login.LoginDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class FirebaseAccessObject implements CreateProjectDataAccessInterface, AddEmailDataAccessInterface, LoginDataAccessInterface, CreateMeetingDataAccessInterface {
    Firestore db;
    ProjectFactory projectFactory;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Project> projects = new HashMap<>();

    // Load Firebase Admin SDK credentials
    public FirebaseAccessObject() {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("Google_Firebase_SDK.json");
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.db = FirestoreClient.getFirestore();
    }

    public void save(Project project) {
        String projectName = project.getProjectName();
        String leaderEmail = project.getLeaderEmail();
        ArrayList<String> memberEmails = project.getMemberEmails();

            DocumentReference docRef = db.collection(projectName).document("projectInfo");
            Map<String, Object> data = new HashMap<>();
            data.put("projectName", projectName);
            data.put("leaderEmail", leaderEmail);
            data.put("memberEmails", memberEmails);
            ApiFuture<WriteResult> result = docRef.set(data);
            Map<String, Object> data1 = new HashMap<>();
            DocumentReference docRefTask = db.collection(projectName).document("taskInfo");
            DocumentReference docRefMeeting = db.collection(projectName).document("meetingInfo");
            DocumentReference docRefAnnounce = db.collection(projectName).document("announcementInfo");
            docRefTask.set(data1);
            docRefMeeting.set(data1);
            docRefAnnounce.set(data1);
    }


    public Project getProjectInfo(String projectName) {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot projectInfo = null;
        try {
            projectInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        ;
        String leaderEmail = projectInfo.getString("leaderEmail");
        ArrayList<String> memberEmails = (ArrayList<String>) projectInfo.get("memberEmails");
        Project project = new CommonProject(projectName, leaderEmail, memberEmails);
        return project;
    }

    public ArrayList<String> getInfoList(String projectName, String listType) {
        ArrayList<String> list = new ArrayList<>();

        DocumentReference docRef = db.collection(projectName).document(listType);
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
        if (listType.equals("announcementInfo")) {
            List<String> messageList = new ArrayList<>();
            for (String key: list) {
                Map<String, Object> entry = (Map<String, Object>) fields.get(key);
                messageList.add((String) entry.get("message"));
            }
            list = new ArrayList<>();
            list.addAll(messageList);
        }
        return list;
    }



    public void addMemberToProject(String projectName, String email) {
        // TODO: add member to project
    }

    public void removeMemberFromProject(String projectName, String email) {
        // TODO: remove member from project
    }

    public boolean existsByName(String projectName) {
        Iterable<CollectionReference> collections = db.listCollections();
        for (CollectionReference collRef : collections) {
            if (collRef.getId().equals(projectName)){
                return true;
            }
        }
        return false;

    }

    public boolean meetingNameExists(String projectName, String meetingName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(projectName).document("meetingInfo");
        ApiFuture<DocumentSnapshot> snapshot = docRef.get();
        DocumentSnapshot document = snapshot.get();

        if (document.exists()) {
            Map<String, Object> meetingInfo = document.getData();
            return meetingInfo != null && meetingInfo.containsKey(meetingName);
        } else {
            return false;
        }
    }

    @Override
    public void saveMeeting(Meeting meeting) throws ExecutionException, InterruptedException {
        String meetingName = meeting.getMeetingName();
        String projectName = meeting.getProjectName();
        if (!meetingNameExists(projectName, meetingName)){
            ArrayList<String> participantEmail = meeting.getParticipantEmail();
            String meetingDate = meeting.getMeetingDate();
            String startTime = meeting.getStartTime();
            String endTime = meeting.getEndTime();

            DocumentReference docRefMeeting = db.collection(projectName).document("meetingInfo");
            Map<String, Object> meetingData = new HashMap<>();
            meetingData.put("meetingName", meetingName);
            meetingData.put("participantEmail", participantEmail);
            meetingData.put("meetingDate", meetingDate);
            meetingData.put("startTime", startTime);
            meetingData.put("endTime", endTime);
            meetingData.put("projectName", projectName);
            docRefMeeting.set(meetingData);
        }
    }


    public boolean memberExists(String projectName, ArrayList<String> emails) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");
        ApiFuture<DocumentSnapshot> snapshot = docRef.get();
        DocumentSnapshot document = snapshot.get();

        if (document.exists()) {
            // Get the project's leaderEmail
            String leaderEmail = document.getString("leaderEmail");

            // Get the project's memberEmails
            ArrayList<String> memberEmails = (ArrayList<String>) document.get("memberEmails");

            // Check if all provided emails are either leaderEmail or part of memberEmails
            for (String email : emails) {
                if (!email.equals(leaderEmail) && (memberEmails == null || !memberEmails.contains(email))) {
                    return false; // At least one email is not in leaderEmail or memberEmails
                }
            }
            return true; // All emails are either leaderEmail or part of memberEmails
        }
        return false; // Project not found
    }

}
