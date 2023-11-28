package entity;

import java.util.ArrayList;

/**
 * Represents a meeting with a title, participant emails, date, starting time, ending time, and
 * project that it is a part of.
 * It encapsulates the data for a meeting in the system.
 */
public class CommonMeeting implements Meeting {
    private final String meetingName;
    private final ArrayList<String> participantEmail;
    private final String meetingDate;
    private final String startTime;
    private final String endTime;
    private final String projectName;


    /**
     * Constructs a new CommonMeeting with the specified details.
     *
     * @param meetingName                The title of the meeting.
     * @param participantEmail           The email addresses of the participants.
     * @param meetingDate                The date of the meeting.
     * @param startTime                  The starting time of the meeting.
     * @param endTime                    The ending time of the meeting.
     * @param projectName                The name of the project that this meeting is about.
     */
    public CommonMeeting(String meetingName, ArrayList<String> participantEmail, String meetingDate, String startTime, String endTime, String projectName) {
        this.meetingName = meetingName;
        this.participantEmail = participantEmail;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
    }

    /**
     * Retrieves the title of the meeting.
     *
     * @return the meeting title.
     */
    @Override
    public String getMeetingName() {return meetingName;}

    /**
     * Retrieves the email addresses of the participants in the meeting.
     *
     * @return the meeting participants' email addresses.
     */
    @Override
    public ArrayList<String> getParticipantEmail() {return participantEmail;}

    /**
     * Retrieves the date of the meeting.
     *
     * @return the meeting date.
     */
    @Override
    public String getMeetingDate() {return meetingDate;}

    /**
     * Retrieves the starting time of the meeting.
     *
     * @return the starting time of the meeting
     */
    @Override
    public String getStartTime() {return startTime;}

    /**
     * Retrieves the ending time of the meeting.
     *
     * @return the ending time of the meeting
     */
    @Override
    public String getEndTime() {return endTime;}

    /**
     * Retrieves the name of the project that this meeting belongs to.
     *
     * @return the name of the project that this meeting belongs to.
     */
    @Override
    public String getProjectName() {return projectName;}
}
