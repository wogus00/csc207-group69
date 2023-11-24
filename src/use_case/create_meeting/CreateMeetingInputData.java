package use_case.create_meeting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Input Data class representing the data inputted by user for creating a meeting.
 * This class encapsulates the necessary information to create a meeting.
 */
public class CreateMeetingInputData {
    private final String meetingName;
    private final ArrayList<String> participantEmail;
    private final String meetingDate;
    private final String startTime;
    private final String endTime;
    private final String projectName;

    /**
     * Constructor method that creates new CreateMeetingInputData with specified information needed when creating meeting.
     *
     * @param meetingName String representing title of meeting.
     * @param participantEmail ArrayList of strings representing the email addresses of all participants.
     * @param meetingDate String representing date of meeting.
     * @param startTime String representing starting time of meeting.
     * @param endTime String representing ending time of meeting.
     * @param projectName String that represents the name of the project that the meeting belongs to.
     */
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


    /**
     * Getter method that returns title of the meeting.
     * @return String representing the meeting title.
     */
    public String getMeetingName() {
        return meetingName;
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
}
