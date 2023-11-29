package entity;
import java.util.ArrayList;

public interface Meeting {
    String getMeetingName();
    ArrayList<String> getParticipantEmail();
    String getMeetingDate();
    String getStartTime();
    String getEndTime();
    String getProjectName();
}
