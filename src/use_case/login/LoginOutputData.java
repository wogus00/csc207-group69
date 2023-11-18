package use_case.login;

import java.util.ArrayList;

public class LoginOutputData {

    private final String projectName;
    private final String userEmail;
    private final String leaderEmail;
    private final ArrayList<String> memberEmails;
    private final ArrayList<String> taskList;
    private final ArrayList<String> meetingList;
    private final ArrayList<String> announcements;
    private boolean useCaseFailed;

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

    public String getProjectName() {
        return projectName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getLeaderEmail() { return leaderEmail; }

    public ArrayList<String> getMemberEmails() { return memberEmails; }

    public ArrayList<String> getTaskList() { return taskList; }

    public ArrayList<String> getMeetingList() { return meetingList; }

    public ArrayList<String> getAnnouncements() { return  announcements; }

}
