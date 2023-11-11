package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.CommonAnnouncement;
import entity.Project;
import entity.ProjectFactory;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class FirebaseAccessObject implements CreateProjectDataAccessInterface, AddEmailDataAccessInterface {
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
    }

    Project getProject(String projectName) {
        return projects.get(projectName);
    }

    public void addMemberToProject(String projectName, String email) {
        // TODO: add member to project
    }

    public void removeMemberFromProject(String projectName, String email) {
        // TODO: remove member from project
    }

    public boolean existsByName(String newProjectName) {
        //TODO: add ways to check if newProjectName exists in db collection
        return true;
    }

    public void addAnnouncement(CommonAnnouncement announcement) {
        // Should I initialize firestore, or just treat firestore is defined as a precondition?
        if (db == null) {
            // Initialize Firestore
        }

        // Convert LocalDateTime to a Firebase compatible format
        String formattedCreationTime = announcement.getCreationTime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Create a Map to hold announcement data
        Map<String, Object> announcementData = new HashMap<>();
        announcementData.put("title", announcement.getAnnouncementTitle());
        announcementData.put("message", announcement.getMessage());
        announcementData.put("creationTime", formattedCreationTime);
        announcementData.put("author", announcement.getAuthor());

        // Add data to Firebase
        ApiFuture<DocumentReference> addedDocRef = db.collection("announcements").add(announcementData);
        // Handle completion of the future
    }
}
