package interface_adapter.modify_meeting;

import java.util.*;
import java.sql.Time;

/**
 * State class for modify meeting use case.
 * This class stores data about the meeting and any errors encountered in its creation.
 */
public class ModifyMeetingState {
    private String meetingName = "";
    private String meetingNameError = null;
    private ArrayList<String> participantEmail =new ArrayList<>();
    private String meetingDate = new String();
    private String startTime = new String();
    private String endTime = new String();
    private String projectName = "";
    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy the state instance to copy the data from
     */
    public ModifyMeetingState(ModifyMeetingState copy) {
        meetingName = copy.meetingName;
        meetingNameError = copy.meetingNameError;
        participantEmail = copy.participantEmail;
        meetingDate = copy.meetingDate;
        startTime = copy.startTime;
        endTime = copy.endTime;
        projectName = copy.projectName;
    }

    /**
     * Default constructor for creating an initial blank state.
     */
    public ModifyMeetingState() {}

    /**
     * Setter method to set meetingName as the title of the meeting to be modified.
     * @param meetingName String of the meeting's title
     */
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    /**
     * Setter method to sets an error when creating a meeting.
     * @param meetingNameError String indicating that an error occurred during meeting modification.
     */
    public void setMeetingNameError(String meetingNameError) {
        this.meetingNameError = meetingNameError;
    }

    /**
     * Setter method to update participants in the meeting.
     * @param participantEmailString String containing all participant email addresses, separated by a comma.
     */
    public void setParticipantEmail(String participantEmailString) {
        String[] parts = participantEmailString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].strip();
        }
        this.participantEmail = new ArrayList<>(Arrays.asList(parts));
    }

    /**
     * Setter method to update the date of the meeting.
     * @param meetingDate String containing the date of the meeting.
     */
    public void setMeetingDate(String meetingDate){
        this.meetingDate = meetingDate;
    }

    /**
     * Setter method to update the starting time of the meeting.
     * @param startTime String containing the starting time of the meeting.
     */
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    /**
     * Setter method to update the ending time of the meeting.
     * @param endTime String containing the ending time of the meeting.
     */
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    /**
     * Setter method to set projectName as the name of the project that this meeting is about.
     * @param projectName String of the name of the project that this meeting is about.
     */
    public void setProjectName(String projectName) { this.projectName = projectName;}

    /**
     * Getter method that returns title of the meeting.
     * @return String representing the meeting title.
     */
    public String getMeetingName() {
        return meetingName;
    }

    /**
     * Getter method that returns an error when creating a meeting.
     * @return String indicating that an error occurred during meeting creation.
     */
    public String getMeetingNameError() {
        return meetingNameError;
    }

    /**
     * Getter method that returns the email addresses of all participants in the meeting.
     * @return ArrayList<String> containing email addresses of all meeting participants.
     */
    public ArrayList<String> getParticipantEmail() {
        return participantEmail;
    }

    /**
     * Getter method that returns the date of the meeting.
     * @return String representing the meeting date.
     */
    public String getMeetingDate() {return meetingDate;}

    /**
     * Getter method that returns the starting time of the meeting.
     * @return String representing the meeting starting time.
     */
    public String getStartTime() {return startTime;}

    /**
     * Getter method that returns the ending time of the meeting.
     * @return String representing the meeting ending time.
     */
    public String getEndTime() {return endTime;}

    /**
     * Getter method that returns the project that this meeting is about.
     * @return String representing the project that this meeting is about.
     */
    public String getProjectName() {return projectName;}

    /**
     * Method to return meeting details in a sentence.
     * @return String is a sentence including the details of the meeting.
     */
    @Override
    public String toString() {
        return "ModifyMeetingState{" +
                "meeting name='" + meetingName + '\'' +
                ", participant email='" + participantEmail + '\'' +
                ", meeting date='" + meetingDate + '\'' +
                ", start time='" + startTime + '\'' +
                ", end time='" + endTime + '\'' +
                ", project name='" + projectName + '\'' +
                '}';
    }
}
