package use_case.create_meeting;

import java.util.ArrayList;

/**
 * Output Data class representing the data that the user will receive for creating a meeting.
 * This sends data to be shown to the user through the CreateMeetingPresenter.
 */
public class CreateMeetingOutputData {
    private final String meetingName;
    private final ArrayList<String> participantEmail;
    private final String meetingDate;
    private final String startTime;
    private final String endTime;
    private final String projectName;

    public CreateMeetingOutputData(String meetingName, ArrayList<String> participantEmail, String meetingDate,
                                   String startTime, String endTime, String projectName) {
        this.meetingName = meetingName;
        this.participantEmail = participantEmail;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
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


