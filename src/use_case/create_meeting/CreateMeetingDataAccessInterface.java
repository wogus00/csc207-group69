package use_case.create_meeting;

public class CreateMeetingDataAccessInterface {
    public CreateMeetingDataAccessInterface() {
        void saveMeeting(String projectName, Meeting newMeeting);

        boolean MeetingNameTaken(String projectName, String meetingName);

        boolean ParticipantsExist(String projectName, ArrayList<String> ParticipantEmail);
    }
}
