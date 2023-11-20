package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class CommonMeetingFactory implements MeetingFactory{
    @Override
    public Meeting create(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName){
        return null;
    }
}
