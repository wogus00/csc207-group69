package use_case.create_meeting;

import java.sql.Time;

public class CreateMeetingDataAccessInterface {
    public CreateMeetingDataAccessInterface() {
        void saveMeeting(String projectName, Meeting newMeeting);

        boolean projectNameExists(String projectName, String meetingName);

        boolean memberExists(String projectName, ArrayList<String> ParticipantEmail);
    }
}
