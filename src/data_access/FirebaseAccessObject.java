package data_access;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.*;

import entity.Announcement;
import entity.CommonAnnouncement;

import entity.Project;
import entity.ProjectFactory;
import entity.Task;
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
import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.set_leader.SetLeaderDataAccessInterface;


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
            FileInputStream serviceAccount = new FileInputStream("google_firebase_sdk.json");
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
                // FileInputStream serviceAccount = new FileInputStream("google_firebase_sdk.json");
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

    /**
     * Saves the project details to a Firestore database.
     * This method performs the following actions:
     * 1. Saves basic project information (name, leader email, member emails) in a document named 'projectInfo'.
     * 2. Initializes empty documents for 'taskInfo' and 'meetingInfo' under the project collection.
     * 3. Updates the 'IDCollection' document in the 'IDCollection' collection with the project name.
     *
     * @param project The Project object containing details to be saved.
     *                It should contain the project name, leader email, and member emails.
     * @throws RuntimeException if there is an interruption or execution failure during Firestore operations.
     */
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
            docRefTask.set(data1);
            docRefMeeting.set(data1);
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

    /**
     * Saves meeting details to a Firestore database.
     * The method performs the following:
     * 1. Retrieves existing meeting information from the 'meetingInfo' document under the specified project.
     * 2. Updates or creates new meeting information with details like meeting name, participant emails, meeting date, start time, and end time.
     * 3. Saves the updated meeting information back to the 'meetingInfo' document.
     *
     * @param meeting The Meeting object containing details to be saved.
     *                It should contain the meeting name, project name, participant emails, meeting date, start time, and end time.
     * @throws RuntimeException if there is an interruption or execution failure during Firestore operations.
     */
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

    /**
     * Checks if a meeting name already exists in the specified project.
     *
     * This method queries the Firestore database for a document that represents
     * meeting information in a given project. It then checks if the specified
     * meeting name exists within the retrieved document.
     *
     * @param projectName The name of the project in which to check for the meeting name.
     * @param meetingName The name of the meeting to check for existence.
     * @return {@code true} if the meeting name exists, {@code false} otherwise.
     * @throws ExecutionException If the computation threw an exception.
     * @throws InterruptedException If the current thread was interrupted while waiting.
     */
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

    /**
     * Retrieves information about a specific project.
     *
     * This method accesses the Firestore database to obtain details of a project
     * based on its name. It fetches project information including the project leader's
     * email and the emails of all project members. The method then constructs and
     * returns a {@code Project} object with this information.
     *
     * If the operation is interrupted or fails to execute, it throws a {@code RuntimeException}.
     *
     * @param projectName The name of the project for which information is to be retrieved.
     * @return A {@code Project} object containing details of the specified project.
     * @throws RuntimeException If an {@code InterruptedException} or {@code ExecutionException} occurs.
     */
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

    /**
     * Retrieves a list of information based on the specified type within a given project.
     *
     * This method serves as a gateway to fetch different types of information (tasks, meetings,
     * or announcements) from a project. It uses a {@code ProjectInfoAccessor} to access the
     * desired information. The type of information to be retrieved is specified by the 'type' parameter.
     *
     * @param projectName The name of the project from which to retrieve information.
     * @param type The type of information to retrieve. Valid options are "task", "meeting", or "announcement".
     * @return An {@code ArrayList<String>} containing the requested information. If the type is not recognized,
     *         it returns an empty list.
     */
    public ArrayList<String> getInfoList(String projectName, String type) {
        ProjectInfoAccessor accessor = new ProjectInfoAccessorImplementation(projectName, this);
        if (type.equals("task")) {
            return accessor.getTaskInfoList();
        } if (type.equals("meeting")) {
            return accessor.getMeetingInfoList();
        } if (type.equals("announcement")) {
            return accessor.getAnnouncementInfoList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<String> getMembersEmails(String projectName) {
        DocumentReference docRef = db.collection(projectName).document("projectInfo");
        ArrayList<String> memberEmails = new ArrayList<>();

        try {
            // Retrieve the projectInfo document
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Extract the memberEmails list
                memberEmails = (ArrayList<String>) document.get("memberEmails");
                if (memberEmails == null) {
                    memberEmails = new ArrayList<>(); // Return empty list if field is null
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }

        return memberEmails;
    }


    /**
     * Adds a member to a specific project by email.
     *
     * This method updates the 'projectInfo' document of the specified project in the Firestore database.
     * It runs a transaction to ensure the member's email is not already in the project's member list.
     * If the project does not exist or if the email is already in the member list, the method throws
     * an exception.
     *
     * @param projectName The name of the project to which the member is to be added.
     * @param email The email address of the member to be added.
     * @throws IllegalStateException If the specified project does not exist in the database.
     * @throws IllegalArgumentException If the provided email already exists in the project's member list.
     * @throws RuntimeException If the transaction fails or is interrupted for other reasons.
     */
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

    /**
     * Removes a member from a specific project by email.
     *
     * This method updates the 'projectInfo' document of the specified project in the Firestore database.
     * It runs a transaction to ensure the project exists and the member's email is in the project's member list.
     * If the project does not exist, the member list is empty, or the email is not in the member list,
     * the method throws an exception.
     *
     * @param projectName The name of the project from which the member is to be removed.
     * @param email The email address of the member to be removed.
     * @throws IllegalStateException If the specified project does not exist or the member list is empty.
     * @throws IllegalArgumentException If the provided email is not in the project's member list.
     * @throws RuntimeException If the transaction fails or is interrupted for other reasons.
     */
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

    /**
     * Sets a new leader for a specified project.
     *
     * This method performs a transaction in the Firestore database to update the leader of a project.
     * It ensures that the new leader's email is different from the current leader's email. If the project
     * does not exist, or if the new leader's email is the same as the current leader's, the method throws
     * an exception. It also updates the member list to reflect this change in leadership.
     *
     * @param projectName The name of the project for which the leadership change is to occur.
     * @param newLeaderEmail The email address of the new leader.
     * @throws IllegalStateException If the specified project does not exist in the database.
     * @throws IllegalArgumentException If the new leader's email is the same as the current leader's email.
     * @throws RuntimeException If the transaction fails or is interrupted for other reasons.
     */
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

    /**
     * Checks if a project with the specified name exists.
     *
     * This method iterates over a collection of project references, comparing each project's
     * ID with the provided project name. It also checks for specific project names like
     * "announcements" or "IDCollection" as a special case. If a project with the given name
     * is found, or if the name matches one of the special cases, the method returns true.
     *
     * @param projectName The name of the project to check for existence.
     * @return {@code true} if the project exists or matches a special case; {@code false} otherwise.
     */
    public boolean existsByName(String projectName) {
        Collections collections = new Collections(this);
        Iterator<CollectionReference> collectionIterator = collections.iterator();
        while (collectionIterator.hasNext()){
            if (collectionIterator.next().getId().equals(projectName)) {
                return true;
            }
        }
        if (projectName.equals("announcements") | projectName.equals("IDCollection")) {
            return true;
        }
        return false;
    }

    /**
     * Saves a new task to the Firestore database under a specified project.
     *
     * This method first retrieves the document for task information within the given project.
     * It then constructs a map of task details from the provided {@code Task} object, which includes
     * task name, supervisor, working members list, deadline, comments, and status. These details are
     * then saved to the Firestore document. If the retrieval of task information is interrupted or fails
     * to execute, a {@code RuntimeException} is thrown.
     *
     * @param projectName The name of the project under which the task is to be saved.
     * @param newTask The {@code Task} object containing the details of the task to be saved.
     * @throws RuntimeException If the task information retrieval is interrupted or fails.
     */
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

    /**
     * Checks if a task name already exists within a project.
     *
     * @param projectName The name of the project to check.
     * @param taskName The name of the task to verify.
     * @return {@code true} if the task name exists within the project; {@code false} otherwise.
     */
    @Override
    public boolean taskNameExists(String projectName, String taskName) {
        ProjectInfoAccessor accessor = new ProjectInfoAccessorImplementation(projectName, this);
        ArrayList<String> taskList = accessor.getTaskInfoList();
        if (taskList.contains(taskName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes an old task from a specified project.
     *
     * @param projectName The name of the project from which the task should be deleted.
     * @param oldTaskName The name of the task to be deleted.
     */
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

    /**
     * Marks a specified task as completed within a project.
     *
     * @param projectName The name of the project containing the task.
     * @param taskName The name of the task to mark as complete.
     */
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

    /**
     * Checks if a user has access to a specified task within a project.
     *
     * @param projectName The name of the project containing the task.
     * @param taskName The name of the task to check access for.
     * @param userEmail The email of the user to check for access.
     * @return {@code true} if the user has access to the task; {@code false} otherwise.
     */
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

    /**
     * Checks if members in a given list exist within a project.
     *
     * @param projectName The name of the project to check in.
     * @param workingMembersList The list of member emails to verify.
     * @return {@code true} if all members in the list exist within the project; {@code false} otherwise.
     */
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


    /**
     * Saves an announcement to the Firestore database. If the announcement already exists, it updates the existing one; otherwise, it creates a new document.
     *
     * @param projectName The name of the project associated with the announcement.
     * @param announcement The announcement object to be saved or updated.
     * @throws RuntimeException If an InterruptedException or ExecutionException occurs during the Firestore operation.
     */
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

    /**
     * Deletes an announcement from the Firestore database based on the given announcement ID.
     *
     * @param announcementId The ID of the announcement to be deleted.
     * @return True if the deletion was successful, false if it failed or an exception occurred.
     */
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



    /**
     * Retrieves an announcement from the Firestore database using the provided announcement ID.
     *
     * @param announcementId The ID of the announcement to be retrieved.
     * @return An Announcement object if it exists, or null if it does not exist or if an exception occurs.
     */
    @Override
    public Announcement getAnnouncementById(String announcementId) {
        DocumentReference docRef = db.collection("announcements").document(announcementId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                // Constructing CommonAnnouncement from the document
                String title = document.getString("title");
                String message = document.getString("message");
                String author = document.getString("author");
                Timestamp creationTimeStamp = (Timestamp) document.get("creationTime");
                String id = document.getId();

                LocalDateTime creationTime = null;
                if (creationTimeStamp != null) {
                    // Assuming creationTime is stored in ISO_LOCAL_DATE_TIME format
                    creationTime = creationTimeStamp.toSqlTimestamp().toLocalDateTime();
                }

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

//    @Override
//    public Announcement getAnnouncementById(String announcementId) {
//        DocumentReference docRef = db.collection("announcements").document(announcementId);
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        try {
//            DocumentSnapshot document = future.get();
//            if (document.exists()) {
//                // Constructing CommonAnnouncement from the document
//                String title = document.getString("title");
//                String message = document.getString("message");
//                String author = document.getString("author");
//                Timestamp creationTimeStamp = (Timestamp) document.get("creationTime");
//                String id = document.getId();
//
//                // Assuming creationTime is stored in ISO_LOCAL_DATE_TIME format
//                LocalDateTime creationTime = creationTimeStamp.toSqlTimestamp().toLocalDateTime();
//
//                return new CommonAnnouncement(title, message, creationTime, author, id);
//            } else {
//                // Handle the case where the announcement doesn't exist
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//            return null;
//        }
//    }
}

