package use_case.modify_meeting;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import entity.Meeting;

public interface ModifyMeetingDataAccessInterface {
    void saveMeeting(Meeting newMeeting);

    boolean meetingNameExists(String projectName, String meetingName) throws ExecutionException, InterruptedException;

    boolean memberExists(String projectName, ArrayList<String> participantEmail);
}

