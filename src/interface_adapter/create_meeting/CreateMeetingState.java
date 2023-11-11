package interface_adapter.create_meeting;

import java.util.*;
import java.sql.Time;

public class CreateMeetingState {
    private String meetingName = "";
    private String meetingNameError = null;
    private ArrayList<String> participantEmail =new ArrayList<>();
    private Date meetingDate = new Date();
    private Time startTime = new Time();
    private Time endTime = new Time();
    private String projectName = "";

    public CreateProjectState(CreateMeetingState copy) {
        meetingName = copy.meetingName;
        meetingNameError = copy.meetingNameError;
        participantEmail = copy.participantEmail;
        meetingDate = copy.meetingDate;
        startTime = copy.startTime;
        endTime = copy.endTime;
        projectName = copy.projectName;
    }

    public CreateMeetingState() {}

    public String getMeetingName() {
        return meetingName;
    }

    public String getMeetingNameError() {
        return meetingNameError;
    }

    public ArrayList<String> getParticipantEmail() {
        return participantEmail;
    }

    public Date getMeetingDate() {return meetingDate;}

    public Time getStartTime() {return startTime;}

    public Time getEndTime() {return endTime;}

    public String getProjectName() {return projectName;}

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public void setMeetingNameError(String meetingNameError) {
        this.meetingNameError = meetingNameError;
    }

    public void setParticipantEmail(String participantEmailString) {
        String[] parts = participantEmailString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }
        this.participantEmail = new ArrayList<>(Arrays.asList(parts));
    }

    public void setMeetingDate(Date meetingDate){
        this.meetingDate = meetingDate;
    }

    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    public void setProjectName(String projectName) { this.projectName = projectName;}

    @Override
    public String toString() {
        return "CreateMeetingState{" +
                "meeting name='" + meetingName + '\'' +
                ", participant email='" + participantEmail + '\'' +
                ", meeting date" + meetingDate + '\'' +
                ", start time" + startTime + '\'' +
                ", end time" + endTime + '\'' +
                ", project name" + projectName + '\'' +
                '}';
    }
}
