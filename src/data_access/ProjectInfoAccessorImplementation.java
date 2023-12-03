package data_access;

import java.util.ArrayList;

public class ProjectInfoAccessorImplementation implements ProjectInfoAccessor {

    private final String projectName;
    private final FirebaseAccessObject firebaseAccessObject;
    public AnnouncementMessageListGetter announcementMessageListGetter;
    public TaskListGetter taskListGetter;
    public MeetingListGetter meetingListGetter;

    public ProjectInfoAccessorImplementation(String projectName, FirebaseAccessObject firebaseAccessObject) {
        this.projectName = projectName;
        this.firebaseAccessObject = firebaseAccessObject;
        this.announcementMessageListGetter = new AnnouncementMessageListGetter();
        this.taskListGetter = new TaskListGetter();
        this.meetingListGetter = new MeetingListGetter();
    }
    public ArrayList<String> getAnnouncementInfoList(){
        return (ArrayList<String>) announcementMessageListGetter.getInfoList(projectName, firebaseAccessObject);
    }
    public ArrayList<String> getMeetingInfoList(){
        return (ArrayList<String>) meetingListGetter.getInfoList(projectName, firebaseAccessObject);
    }
    public ArrayList<String> getTaskInfoList(){
        return (ArrayList<String>) taskListGetter.getInfoList(projectName, firebaseAccessObject);
    }
}
