package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Factory for creating CommonMeeting instances.
 * This class provides a method to create new meetings with given parameters.
 */
public class CommonMeetingFactory implements MeetingFactory{
    @Override
    public Meeting create(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName){
        return null;
    }
}
