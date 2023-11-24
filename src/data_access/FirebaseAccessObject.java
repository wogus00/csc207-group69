package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.CommonProject;
import entity.Meeting;
import entity.Announcement;
import entity.CommonAnnouncement;
import entity.Project;
import entity.ProjectFactory;
import entity.Task;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.delete_announcement.DeleteAnnouncementDataAccessInterface;
import use_case.create_announcement.CreateAnnouncementDataAccessInterface;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class FirebaseAccessObject implements CreateProjectDataAccessInterface, AddEmailDataAccessInterface, CreateAnnouncementDataAccessInterface, DeleteAnnouncementDataAccessInterface, LoginDataAccessInterface {

    Firestore db;
    ProjectFactory projectFactory;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Project> projects = new HashMap<>();
    // Load Firebase Admin SDK credentials
    public FirebaseAccessObject() {
        try {
            // Check if FirebaseApp has already been initialized
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount = new FileInputStream("Google_Firebase_SDK.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
            }
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
        };
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

    @Override
    public void saveTask(String projectName, Task newTask) {
        ;
    }

    @Override
    public boolean taskNameExists(String projectName, String taskName) {
        return false;
    }

    @Override
    public void completeTask(String projectName, String taskName) {
        // TODO: add methods
    }

    @Override
    public boolean userHasAccessToTask(String projectName, String taskName, String userEmail) {
        return false;
    }

    @Override
    public boolean memberExists(String projectName, ArrayList<String> workingMembersList) {
        return false;
    }

    @Override
    public void save(Announcement announcement) {
        // Initialize Firestore if not already done
        if (db == null) {
            // Initialize Firestore
        }

        // Generate a unique ID for the announcement
//        String announcementId = UUID.randomUUID().toString();

        // Convert LocalDateTime to a Firebase compatible format
        String formattedCreationTime = announcement.getCreationTime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Create a Map to hold announcement data
        Map<String, Object> announcementData = new HashMap<>();
        announcementData.put("id", announcement.getId()); // Add the generated ID
        announcementData.put("title", announcement.getAnnouncementTitle());
        announcementData.put("message", announcement.getMessage());
        announcementData.put("creationTime", formattedCreationTime);
        announcementData.put("author", announcement.getAuthor());

        // Use the generated ID as the document ID in Firestore
        ApiFuture<WriteResult> addedDocRef = db.collection("announcements")
                .document(announcement.getId())
                .set(announcementData);
        // Handle completion of the future
    }

    @Override
    public boolean deleteAnnouncement(String announcementId) {
        // Assuming that the Firestore database has already been initialized in the constructor
        // and db is your Firestore instance
        try {
            ApiFuture<WriteResult> writeResult = db.collection("announcements").document(announcementId).delete();
            writeResult.get(); // This line throws InterruptedException or ExecutionException
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }

    @Override
    public CommonAnnouncement getAnnouncementById(String announcementId) {
        DocumentReference docRef = db.collection("announcements").document(announcementId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // Constructing CommonAnnouncement from the document
                String title = document.getString("title");
                String message = document.getString("message");
                String author = document.getString("author");
                String creationTimeString = document.getString("creationTime");
                String id = document.getId();

                // Assuming creationTime is stored in ISO_LOCAL_DATE_TIME format
                LocalDateTime creationTime = LocalDateTime.parse(creationTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                return new CommonAnnouncement(title, message, creationTime, author, id);
            } else {
                // Handle the case where the announcement doesn't exist
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
            return null;
        }
    }


}

