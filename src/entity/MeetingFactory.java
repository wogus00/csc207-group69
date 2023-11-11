package entity;

import java.time.LocalDate;
import java.sql.Time;
import java.util.ArrayList;

public interface MeetingFactory {
    Meeting create(String meetingName, ArrayList<String> participantEmail, LocalDate meetingDate, Time startTime, Time endTime, String projectName);
}
