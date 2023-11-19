package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class CommonMeetingFactory implements MeetingFactory{
    @Override
    public Meeting create(String meetingName, ArrayList<String> participantEmail, LocalDate meetingDate, Time startTime, Time endTime, String projectName){
        return null;
    }
}
