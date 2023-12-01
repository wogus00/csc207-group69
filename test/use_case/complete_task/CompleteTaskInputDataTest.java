package use_case.complete_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.create_meeting.CreateMeetingInputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompleteTaskInputDataTest {
    private CompleteTaskInputData inputData;
    private String projectName;
    private String taskName;
    private String userEmail;


    @BeforeEach
    void setUp() {
        inputData = new CompleteTaskInputData(projectName, taskName, userEmail);
    }

    @Test
    void testGetProjectName() {
        assertEquals(projectName, inputData.getProjectName(), "The project name should match the input.");
    }

    @Test
    void testGetTaskName() {
        assertEquals(taskName, inputData.getTaskName(), "The task name should match the input.");
    }

    @Test
    void testGetUserEmail() {
        assertEquals(userEmail, inputData.getUserEmail(), "The projectName should match the input.");
    }
}
