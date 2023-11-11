package use_case.create_meeting;

import entity.Meeting;

import java.util.ArrayList;

public interface CreateMeetingDataAccessInterface {

    void saveMeeting(String projectName, Meeting newMeeting);

    boolean MeetingNameTaken(String projectName, String meetingName);

    boolean ParticipantsExist(String projectName, ArrayList<String> ParticipantEmail);

}
