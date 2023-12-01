package use_case.create_meeting;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class CreateMeetingInputDataTest {
    private CreateMeetingInputData inputData;
    private String meetingName = "Test Meeting Name";

    private ArrayList<String> participantEmail = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));

    private String meetingDate = "11/11/1111";
    private String startTime = "11:11";
    private String endTime = "22:22";
    private String projectName = "Test Project";

    @BeforeEach
    void setUp() {
        inputData = new CreateMeetingInputData(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);
    }

    @Test
    void testGetMeetingName() {
        assertEquals(meetingName, inputData.getMeetingName(), "The meeting title should match the input.");
    }

    @Test
    void testGetParticipantEmail() {
        assertEquals(participantEmail, inputData.getParticipantEmail(), "The participant emails should match the input.");
    }

    @Test
    void testGetMeetingDate() {
        assertEquals(meetingDate, inputData.getMeetingDate(), "The meeting date should match the input.");
    }

    @Test
    void testGetStartTime() {
        assertEquals(startTime, inputData.getStartTime(), "The starting time should match the input.");
    }

    @Test
    void testGetEndTime() {
        assertEquals(endTime, inputData.getEndTime(), "The ending time should match the input.");
    }

    @Test
    void testGetProjectName() {
        assertEquals(projectName, inputData.getProjectName(), "The project name should match the input.");
    }
}
