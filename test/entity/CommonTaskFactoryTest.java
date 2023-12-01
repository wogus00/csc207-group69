package entity;

import entity.CommonTaskFactory;
import entity.CommonTask;
import entity.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonTaskFactoryTest {

    @Test
    public void testCreate() {
        CommonTaskFactory factory = new CommonTaskFactory();

        String projectName = "Project Y";
        String taskName = "Task 2";
        String supervisor = "Jane Smith";
        ArrayList<String> workingMembersList = new ArrayList<>(Arrays.asList("Member3", "Member4"));
        LocalDate deadline = LocalDate.of(2023, 2, 2);
        String comments = "New Task";
        boolean inputStatus = true; // This value should be ignored

        Task task = factory.create(projectName, taskName, supervisor, workingMembersList, deadline, comments, inputStatus);

        assertTrue(task instanceof CommonTask, "Object is not an instance of CommonTask");
        CommonTask commonTask = (CommonTask) task;

        // Test that all properties except status are set as per input
        assertEquals(projectName, commonTask.getProjectName(), "Project name mismatch");
        assertEquals(taskName, commonTask.getTaskName(), "Task name mismatch");
        assertEquals(supervisor, commonTask.getSupervisor(), "Supervisor name mismatch");
        assertEquals(workingMembersList, commonTask.getWorkingMembersList(), "Working members list mismatch");
        assertEquals(deadline, commonTask.getDeadline(), "Deadline mismatch");
        assertEquals(comments, commonTask.getComments(), "Comments mismatch");

        // Test that the status is always false regardless of the input value
        assertFalse(commonTask.getStatus(), "Status should be false irrespective of input status");
    }
}

