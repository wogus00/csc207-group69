package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.*;

import use_case.add_email.AddEmailDataAccessInterface;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_meeting.CreateMeetingDataAccessInterface;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.login.LoginDataAccessInterface;

import use_case.modify_meeting.ModifyMeetingDataAccessInterface;
import use_case.modify_task.ModifyTaskDataAccessInterface;
import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.set_leader.SetLeaderDataAccessInterface;

import use_case.delete_announcement.DeleteAnnouncementDataAccessInterface;
import use_case.create_announcement.CreateAnnouncementDataAccessInterface;



import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FirebaseAccessObject implements CreateProjectDataAccessInterface, CreateAnnouncementDataAccessInterface, DeleteAnnouncementDataAccessInterface, LoginDataAccessInterface, CompleteTaskDataAccessInterface, SetLeaderDataAccessInterface, RemoveEmailDataAccessInterface, AddEmailDataAccessInterface, ModifyTaskDataAccessInterface, CreateTaskDataAccessInterface, CreateMeetingDataAccessInterface, ModifyMeetingDataAccessInterface {


    Firestore db;
    ProjectFactory projectFactory;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Project> projects = new HashMap<>();
    // Load Firebase Admin SDK credentials
    public FirebaseAccessObject() {
        try {
            FileInputStream serviceAccount = new FileInputStream("Google_Firebase_SDK.json");
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            boolean hasBeenInitialized = false;
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            for (FirebaseApp app : firebaseApps) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    hasBeenInitialized = true;
                }
            }
            if (!hasBeenInitialized) {

            // if (FirebaseApp.getApps().isEmpty()) {
                // FileInputStream serviceAccount = new FileInputStream("Google_Firebase_SDK.json");
                // FirebaseOptions options = new FirebaseOptions.Builder()
                        // .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        // .build();

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

    @Override
    public void saveMeeting(Meeting meeting) {
        String meetingName = meeting.getMeetingName();
        String projectName = meeting.getProjectName();
        ArrayList<String> participantEmail = meeting.getParticipantEmail();
        String meetingDate = meeting.getMeetingDate();
        String startTime = meeting.getStartTime();
        String endTime = meeting.getEndTime();
        DocumentReference docRefMeeting = db.collection(projectName).document("meetingInfo");
        ApiFuture<DocumentSnapshot> snapShot = docRefMeeting.get();
        DocumentSnapshot meetingInfo = null;
        try {
            meetingInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        };
        Map<String, Object> meetingData = new HashMap<>();
        Map<String, Object> meetings = meetingInfo.getData();
        if (meetings == null) {
            meetings = new HashMap<>();
        }
        meetingData.put("meetingName", meetingName);
        meetingData.put("participantEmail", participantEmail);
        meetingData.put("meetingDate", meetingDate);
        meetingData.put("startTime", startTime);
        meetingData.put("endTime", endTime);
        meetingData.put("projectName", projectName);
        meetings.put(meetingName,meetingData);
        docRefMeeting.set(meetings);
    }


    public boolean meetingNameExists(String projectName, String meetingName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(projectName).document("meetingInfo");
        ApiFuture<DocumentSnapshot> snapshot = docRef.get();
        DocumentSnapshot document = snapshot.get();
        Map<String, Object> meetings = document.getData();
        ArrayList<String> keySet = new ArrayList<>();
        keySet.addAll(meetings.keySet());
        if (keySet.contains(meetingName)) {
            return true;
        }
        return false;
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

    public ArrayList<String> getInfoList(String projectName, InfoListGetter infoListGetter) {
        return (ArrayList<String>) infoListGetter.getInfoList(projectName, this);
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
                if (memberEmails == null) {memberEmails = new ArrayList<>();}
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

            // Retrieve the memberEmails0 list
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
                ArrayList<String> memberList = (ArrayList<String>) snapshot.get("memberEmails");
                if (memberList != null) {
                    memberList.remove(newLeaderEmail);
                }
                memberList.add(currentLeaderEmail);
                t.update(docRef, "memberEmails", memberList);
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
        DocumentReference docRef = db.collection(projectName).document("taskInfo");
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot taskInfo = null;
        try {
            taskInfo = snapShot.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        };
        LocalDate deadlineDate = newTask.getDeadline();
        // Convert LocalDate to LocalDateTime (start of the day)
        LocalDateTime deadlineTime = deadlineDate.atStartOfDay();

        // Convert LocalDateTime to Timestamp
        Timestamp deadline = Timestamp.of(java.sql.Timestamp.valueOf(deadlineTime));

        Map<String, Object> fields = taskInfo.getData();
        Map<String, Object> task = new HashMap<>();
        task.put("taskName", newTask.getTaskName());
        task.put("supervisor", newTask.getSupervisor());
        task.put("workingMemberList",newTask.getWorkingMembersList());
        task.put("deadline",deadline);
        task.put("comments", newTask.getComments());
        task.put("status",newTask.getStatus());
        fields.put(newTask.getTaskName(), task);
        docRef.set(fields);
    }

    @Override
    public boolean taskNameExists(String projectName, String taskName) {
        TaskListGetter strategy = new TaskListGetter();
        ArrayList<String> taskList = (ArrayList<String>) strategy.getInfoList(projectName, this);
        if (taskList.contains(taskName)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteOldTask(String projectName, String oldTaskName) {
        DocumentReference docRef = db.collection(projectName).document("taskInfo");
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
        ArrayList<String> keyList = new ArrayList<>();
        Map<String, Object> newFields = new HashMap<>();
        keyList.addAll(fields.keySet());
        for (String key: keyList) {
            if (key != oldTaskName) {
                newFields.put(key, fields.get(key));
            }
        }
        docRef.set(newFields);
    }

    @Override
    public void completeTask(String projectName, String taskName) {
        DocumentReference docRef = db.collection(projectName).document("taskInfo");
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
        Map<String, Object> task = (Map<String, Object>) fields.get(taskName);
        task.put("status", true);
        fields.put(taskName, task);
        docRef.set(fields);
    }

    @Override
    public boolean userHasAccessToTask(String projectName, String taskName, String userEmail) {
        DocumentReference docRef = db.collection(projectName).document("taskInfo");
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
        Map<String, Object> task = (Map<String, Object>) fields.get(taskName);
        String supervisor = (String) task.get("supervisor");
        ArrayList<String> memberList = (ArrayList<String>) task.get("workingMemberList");
        if (supervisor.equals(userEmail) | memberList.contains(userEmail)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean memberExists(String projectName, ArrayList<String> workingMembersList) {
        ArrayList<String> allMembers = new ArrayList<>();
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
        allMembers.add(leaderEmail);
        allMembers.addAll(memberEmails);
        for (String member: workingMembersList) {
            if (!allMembers.contains(member)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void save(String projectName, Announcement announcement) {
        // Reference to the Firestore document where announcements are stored
        DocumentReference docRef = db.collection("announcements").document(announcement.getId());

        // Get the current snapshot of the document
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot announcementInfo = null;
        try {
            announcementInfo = snapShot.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Prepare announcement data for saving
        Map<String, Object> announcementData = new HashMap<>();
        announcementData.put("id", announcement.getId());
        announcementData.put("title", announcement.getAnnouncementTitle());
        announcementData.put("message", announcement.getMessage());

        // Convert LocalDateTime to Timestamp
        Timestamp creationTime = Timestamp.of(java.sql.Timestamp.valueOf(announcement.getCreationTime()));
        announcementData.put("creationTime", creationTime);

        announcementData.put("author", announcement.getAuthor());
        announcementData.put("projectName", projectName);

        // Check if the document already exists and update it
        if (announcementInfo.exists()) {
            // If the document already exists, update it
            docRef.update(announcementData);
        } else {
            // If the document does not exist, create a new one
            docRef.set(announcementData);
        }
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

