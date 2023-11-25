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
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.login.LoginDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class FirebaseAccessObject implements CreateProjectDataAccessInterface, AddEmailDataAccessInterface, LoginDataAccessInterface, CreateTaskDataAccessInterface, CompleteTaskDataAccessInterface {
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
            DocumentReference docRefCollection = db.collection("IDCollection").document("IDCollection");
            ApiFuture<DocumentSnapshot> snapShot = docRefCollection.get();
            DocumentSnapshot IDInfo = null;
            try {
                IDInfo = snapShot.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            };
            ArrayList<String> arrayList = (ArrayList<String>) IDInfo.get("IDCollection");
            Map<String, Object> data2 = new HashMap<>();
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(projectName);
            data2.put("IDCollection",arrayList);
            docRefCollection.set(data2);
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

    public ArrayList<String> getInfoList(String projectName, InfoListRetrieveStrategy infoListRetrieveStrategy) {
        return (ArrayList<String>) infoListRetrieveStrategy.getInfoList(projectName, this);
    }




    public void addMemberToProject(String projectName, String email) {
        // TODO: add member to project
    }

    public void removeMemberFromProject(String projectName, String email) {
        // TODO: remove member from project
    }

    public boolean existsByName(String projectName) {
        Collections collections = new Collections(this);
        Iterator<CollectionReference> collectionIterator = collections.iterator();
        while (collectionIterator.hasNext()){
            if (collectionIterator.next().getId().equals(projectName)) {
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
}
