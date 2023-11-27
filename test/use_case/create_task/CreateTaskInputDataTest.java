package use_case.create_task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskInputDataTest {

    @Test
    public void testCreateTaskInputDataConstructorAndGetters() {
        // Define test inputs
        String projectName = "Project X";
        String taskName = "Task Y";
        String supervisor = "supervisor@example.com";
        String workingMembers = "member1@example.com, member2@example.com";
        String deadline = "2023-01-01";
        String comments = "Initial Task Comments";

        // Create an instance of CreateTaskInputData
        CreateTaskInputData inputData = new CreateTaskInputData(projectName, taskName, supervisor, workingMembers, deadline, comments);

        // Assert that the getters return the correct values set through the constructor
        assertEquals(projectName, inputData.getProjectName(), "Project name mismatch");
        assertEquals(taskName, inputData.getTaskName(), "Task name mismatch");
        assertEquals(supervisor, inputData.getSupervisor(), "Supervisor email mismatch");
        assertEquals(workingMembers, inputData.getWorkingMembersList(), "Working members mismatch");
        assertEquals(deadline, inputData.getDeadline(), "Deadline mismatch");
        assertEquals(comments, inputData.getComments(), "Comments mismatch");
        assertFalse(inputData.getStatus(), "Status should be false by default");
    }
}
