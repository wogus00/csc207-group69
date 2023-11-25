package use_case.login;

import java.util.ArrayList;

/**
 * Output data class for the login use case
 * This class encapsulates the information about the project that the user logs in to, including also
 * the email of the user who is logging in
 */
public class LoginOutputData {

    private final String projectName;
    private final String userEmail;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;
    private final ArrayList<String> taskList;
    private final ArrayList<String> meetingList;
    private final ArrayList<String> announcements;
    private boolean useCaseFailed;

    /**
     * Constructs a new LoginOutputData class with the specific outputs
     *
     * @param projectName String representing the project name that the user logs in into
     * @param userEmail String representing the email of the user that performs the login action
     * @param leaderEmail String representing the email of the leader of the project
     * @param memberEmails ArrayList consisting of strings representing the members of the project, it does not
     *                     include the leader email of the project
     * @param taskList ArrayList consisting of strings representing the name of tasks in the project
     * @param meetingList ArrayList consisting of strings representing the name of meetings in the project
     * @param announcements ArrayList consisting of strings representing the message of announcements in the project
     * @param useCaseFailed Boolean representing whether the login action success
     */
    public LoginOutputData(String projectName, String userEmail, String leaderEmail, ArrayList<String> memberEmails, ArrayList<String> taskList, ArrayList<String> meetingList, ArrayList<String> announcements, boolean useCaseFailed) {
        this.projectName = projectName;
        this.userEmail = userEmail;
        this.leaderEmail = leaderEmail;
        this.memberEmails = memberEmails;
        this.taskList = taskList;
        this.meetingList = meetingList;
        this.announcements = announcements;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Retrieves the project name
     *
     * @return String representing the name of project
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Retrieves the user email
     *
     * @return String representing the email address of the current user
     */
    public String getUserEmail(){
        return userEmail;
    }

    /**
     * Retrieves the email of the leader of the project
     *
     * @return String representing the email address of the project leader.
     */
    public String getLeaderEmail() { return leaderEmail; }

    /**
     * Retrieves the email of members excepting the leader in the project
     *
     * @return Array list of strings representing the collection of email address of all other members.
     */
    public ArrayList<String> getMemberEmails() { return memberEmails; }

    /**
     * Retrieves the name of tasks in the project
     *
     * @return Array list of strings representing the collection of name of tasks in the project.
     */
    public ArrayList<String> getTaskList() { return taskList; }

    /**
     * Retrieves the name of meetings in the project
     *
     * @return Array list of strings representing the collection of name of meetings in the project.
     */
    public ArrayList<String> getMeetingList() { return meetingList; }

    /**
     * Retrieves the announcement messages in the project
     *
     * @return Array list of strings representing the collection of announcement messages in the project.
     */
    public ArrayList<String> getAnnouncements() { return  announcements; }

}
