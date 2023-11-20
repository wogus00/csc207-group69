package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.Project;
import entity.ProjectFactory;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.set_leader.SetLeaderDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class FirebaseAccessObject implements CreateProjectDataAccessInterface, AddEmailDataAccessInterface, RemoveEmailDataAccessInterface, SetLeaderDataAccessInterface, LoginDataAccessInterface {
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

    public Project getProject(String projectName) {
        return projects.get(projectName);
    }

    public void addMemberToProject(String projectName, String email) {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");

        // Run a transaction to ensure the email is not already in the list
        ApiFuture<Void> transaction = db.runTransaction(t -> {
            // Retrieve the current projectInfo document
            DocumentSnapshot snapshot = t.get(docRef).get();
            if (!snapshot.exists()) {
                throw new IllegalStateException("Project with name " + projectName + " does not exist!");
            }

            // Check if the memberEmails list already contains the email
            ArrayList<String> memberEmails = (ArrayList<String>) snapshot.get("memberEmails");
            if (memberEmails != null && memberEmails.contains(email)) {
                // If it does, throw an exception or handle accordingly
                throw new IllegalArgumentException("Email " + email + " already exists in the project.");
            } else {
                // If not, add the email to the memberEmails list
                if (memberEmails == null) memberEmails = new ArrayList<>();
                memberEmails.add(email);
                t.update(docRef, "memberEmails", memberEmails);
            }

            // Return successfully completing the transaction
            return null;
        });

        // When the transaction is complete, you can handle if it was successful or if it failed
        try {
            transaction.get(); // This will throw if the transaction failed
            System.out.println("Member added successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle any errors thrown during the transaction
        }
    }


    public boolean existsByName(String newProjectName) {
        //TODO: add ways to check if newProjectName exists in db collection
        try {
            DocumentReference docRef = db.collection(newProjectName).document("projectInfo");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            return document.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeMemberFromProject(String projectName, String email) {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");

        // Run a transaction to ensure the email list is not empty
        ApiFuture<Void> transaction = db.runTransaction(t -> {
            // Retrieve the current projectInfo document
            DocumentSnapshot snapshot = t.get(docRef).get();
            if (!snapshot.exists()) {
                throw new IllegalStateException("Project with name " + projectName + " does not exist!");
            }

            // Retrieve the memberEmails list
            ArrayList<String> memberEmails = (ArrayList<String>) snapshot.get("memberEmails");
            if (memberEmails == null || memberEmails.isEmpty()) {
                // If the list is empty or null, throw an exception or handle accordingly
                throw new IllegalStateException("The member email list is empty. No members to remove.");
            }

            // If the email exists in the list, remove it
            if (memberEmails.contains(email)) {
                memberEmails.remove(email);
                t.update(docRef, "memberEmails", memberEmails);
            } else {
                // If the email does not exist in the list, handle this case, possibly with an exception or a warning
                throw new IllegalArgumentException("Email " + email + " does not exist in the project.");
            }

            // Return successfully completing the transaction
            return null;
        });

        // When the transaction is complete, handle if it was successful or if it failed
        try {
            transaction.get(); // This will throw if the transaction failed
            System.out.println("Member removed successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle any errors thrown during the transaction
        }
    }


    @Override
    public void SetLeaderToNewLeader(String projectName, String newLeaderEmail) {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");

        // Run a transaction to ensure the new leader is different from the current leader
        ApiFuture<Void> transaction = db.runTransaction(t -> {
            // Retrieve the current projectInfo document
            DocumentSnapshot snapshot = t.get(docRef).get();
            if (!snapshot.exists()) {
                throw new IllegalStateException("Project with name " + projectName + " does not exist!");
            }

            // Check the current leader email
            String currentLeaderEmail = snapshot.getString("leaderEmail");
            if (newLeaderEmail.equals(currentLeaderEmail)) {
                // If the new leader is the same as the current leader, throw an exception
                throw new IllegalArgumentException("New leader email is the same as the current leader email.");
            } else {
                // If the new leader is different, update the document
                t.update(docRef, "leaderEmail", newLeaderEmail);
            }

            // Return successfully completing the transaction
            return null;
        });

        // When the transaction is complete, handle if it was successful or if it failed
        try {
            transaction.get(); // This will throw if the transaction failed
            System.out.println("Leader updated successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle any errors thrown during the transaction
        }
    }

}
