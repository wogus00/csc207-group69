package data_access;

import java.util.ArrayList;

public class ProjectInfoAccessorImplementation implements ProjectInfoAccessor {

    private final String projectName;
    private final FirebaseAccessObject firebaseAccessObject;

    public ProjectInfoAccessorImplementation(String projectName, FirebaseAccessObject firebaseAccessObject) {
        this.projectName = projectName;
        this.firebaseAccessObject = firebaseAccessObject;
    }
    public ArrayList<String> getAnnouncementInfoList(){
        InfoListGetter getter = new AnnouncementMessageListGetter();
        return (ArrayList<String>) getter.getInfoList(projectName, firebaseAccessObject);
    }
    public ArrayList<String> getMeetingInfoList(){
        InfoListGetter getter = new MeetingListGetter();
        return (ArrayList<String>) getter.getInfoList(projectName, firebaseAccessObject);
    }
    public ArrayList<String> getTaskInfoList(){
        InfoListGetter getter = new TaskListGetter();
        return (ArrayList<String>) getter.getInfoList(projectName, firebaseAccessObject);
    }
}
