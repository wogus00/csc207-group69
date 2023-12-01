package use_case.complete_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompleteTaskOutputDataTest {
    private CompleteTaskOutputData outputData;
    private String projectName;
    private String taskName;
    private String userEmail;


    @BeforeEach
    void setUp() {
        outputData = new CompleteTaskOutputData(taskName);
    }

    @Test
    void testGetTaskName() {
        assertEquals(taskName, outputData.getTaskName(), "The task name should match the input.");
    }
}