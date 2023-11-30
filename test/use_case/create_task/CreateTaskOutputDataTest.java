package use_case.create_task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateTaskOutputDataTest {

    @Test
    void testConstructorAndGetTaskName() {
        String expectedTaskName = "SampleTask";

        // Create an instance of CreateTaskOutputData
        CreateTaskOutputData outputData = new CreateTaskOutputData(expectedTaskName);

        // Assert that the taskName is correctly assigned and retrieved
        assertEquals(expectedTaskName, outputData.getTaskName(), "Task name should match the input provided in the constructor");
    }
}
