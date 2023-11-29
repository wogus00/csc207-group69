package use_case.modify_meeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ModifyMeetingOutputDataTest {

    private ModifyMeetingOutputData outputData;
    private final String meetingName = "Meeting";
    private final ArrayList<String> participantEmail = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
    private final String meetingDate = "11/11/1111";
    private final String startTime = "11:11";
    private final String endTime = "11:11";
    private final String projectName = "Project";

    @BeforeEach
    void setUp() {
        outputData = new ModifyMeetingOutputData(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
    }

    @Test
    void testGetMeetingName() {
        assertEquals(meetingName, outputData.getMeetingName(), "The meeting title should match the output.");
    }

    @Test
    void testGetParticipantEmail() {
        assertEquals(participantEmail, outputData.getParticipantEmail(), "The participant emails should match the output.");
    }

    @Test
    void testGetMeetingDate() {
        assertEquals(meetingDate, outputData.getMeetingDate(), "The meeting date should match the output.");
    }

    @Test
    void testGetStartTime() {
        assertEquals(startTime, outputData.getStartTime(), "The starting time should match the output.");
    }

    @Test
    void testGetEndTime() {
        assertEquals(endTime, outputData.getEndTime(), "The ending time should match the output.");
    }

    @Test
    void testGetProjectName() {
        assertEquals(projectName, outputData.getProjectName(), "The project name should match the output.");
    }
}


