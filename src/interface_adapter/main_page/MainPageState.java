package interface_adapter.main_page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPageState {
    private String projectName = "";

    private String userEmail = "";

    private String leaderEmail = "";

    private ArrayList<String> memberEmail = new ArrayList<>();

    private ArrayList<String> taskList = new ArrayList<>(Arrays.asList(""));
    private ArrayList<String> meetingList = new ArrayList<>(Arrays.asList(""));

    private ArrayList<String> announcements = new ArrayList<>(Arrays.asList("No announcements","",""));
    public MainPageState(MainPageState copy) {
        projectName = copy.projectName;;
        userEmail = copy.userEmail;
        leaderEmail = copy.leaderEmail;
        memberEmail = copy.memberEmail;
        taskList = copy.taskList;
        meetingList = copy.meetingList;
        announcements = copy.announcements;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public MainPageState() {}

    public String getProjectName() {return projectName;}

    public void setProjectName(String projectName) {this.projectName = projectName;}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public ArrayList<String> getMemberEmail() {return memberEmail;}

    public void setMemberEmail(ArrayList<String> memberEmail) {this.memberEmail = memberEmail;}

    public ArrayList<String> getTaskList() {return taskList;}

    public void setTaskList(ArrayList<String> taskList) {this.taskList = taskList;}

    public ArrayList<String> getMeetingList() {return meetingList;}

    public void setMeetingList(ArrayList<String> meetingList) {this.meetingList = meetingList;}

    public ArrayList<String> getAnnouncements() {return announcements;}

    public void setAnnouncements(ArrayList<String> announcements) {this.announcements = announcements;}
}
