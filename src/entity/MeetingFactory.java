package entity;

import java.time.LocalDate;
import java.sql.Time;
import java.util.ArrayList;

public interface MeetingFactory {
    static Meeting create(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName);
}
