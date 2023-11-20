package use_case.create_meeting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateMeetingInputData {
    private final String meetingName;
    private final ArrayList<String> participantEmail;
    private final String meetingDate;
    private final String startTime;
    private final String endTime;
    private final String projectName;

    public CreateMeetingInputData(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName){
                this.meetingName = meetingName;
                this.participantEmail = participantEmail;
                this.meetingDate = meetingDate;
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
    public String getMeetingDate() {
        return meetingDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {return endTime;}
    public String getProjectName(){return projectName;}
}
