package use_case.modify_meeting;

import java.sql.Time;
import java.util.ArrayList;
import entity.Meeting;

public interface ModifyMeetingDataAccessInterface {
    void saveMeeting(Meeting newMeeting);

    boolean meetingNameExists(String projectName, String meetingName);

    void deleteOldMeeting(String projectName, String oldMeetingName);

    boolean memberExists(String projectName, ArrayList<String> participantEmail);
}

