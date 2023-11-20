package use_case.create_meeting;

import java.sql.Time;
import java.util.ArrayList;
import entity.Meeting;

public interface CreateMeetingDataAccessInterface {
        void saveMeeting(String projectName, Meeting newMeeting);

        boolean projectNameExists(String projectName, String meetingName);

        boolean memberExists(String projectName, ArrayList<String> participantEmail);
}

