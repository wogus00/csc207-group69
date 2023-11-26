package use_case.create_meeting;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import entity.Meeting;

public interface CreateMeetingDataAccessInterface {
        void saveMeeting(Meeting newMeeting) throws ExecutionException, InterruptedException;

        boolean meetingNameExists(String projectName, String meetingName) throws ExecutionException, InterruptedException;

        boolean memberExists(String projectName, ArrayList<String> participantEmail) throws ExecutionException, InterruptedException;
}

