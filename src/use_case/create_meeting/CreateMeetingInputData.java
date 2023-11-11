package use_case.create_meeting;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateMeetingInputData {
    private final String meetingName;
    private final ArrayList<String> participantEmail;
    private final LocalDate meetingDate;
    private final Time startTime;
    private final Time endTime;
    private final String projectName;

    public CreateMeetingInputData(String meetingName, String participantEmail, String meetingDate, Time startTime, Time endTime, String projectName){
                this.meetingName = meetingName;
                this.participantEmail = stringToArrayListConverter(participantEmail);
                this.meetingDate = stringToDateConverter(meetingDate);
                this.startTime = startTime;
                this.endTime = endTime;
                this.projectName = projectName;
    }
    private ArrayList<String> stringToArrayListConverter(String participantEmailString) {
        String[] participants = participantEmailString.split(",");
        return new ArrayList<>(Arrays.asList(participants));
    }
    private LocalDate stringToDateConverter(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
    public String getMeetingName() {
        return meetingName;
    }
    public ArrayList<String> getParticipantEmail() {
        return participantEmail;
    }
    public LocalDate getMeetingDate() {
        return meetingDate;
    }
    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {return endTime;}
    public String getProjectName(){return projectName;}
}
