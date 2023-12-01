package entity;

import entity.CommonTask;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonTaskTest {

    @Test
    public void testCommonTaskConstructorAndGetters() {
        String projectName = "Project X";
        String taskName = "Task 1";
        String supervisor = "John Doe";
        ArrayList<String> workingMembersList = new ArrayList<>(Arrays.asList("Member1", "Member2"));
        LocalDate deadline = LocalDate.of(2023, 1, 1);
        String comments = "Initial Task";
        boolean status = true;

        CommonTask task = new CommonTask(projectName, taskName, supervisor, workingMembersList, deadline, comments, status);

        // Test getters
        assertEquals(projectName, task.getProjectName(), "Project name mismatch");
        assertEquals(taskName, task.getTaskName(), "Task name mismatch");
        assertEquals(supervisor, task.getSupervisor(), "Supervisor name mismatch");
        assertEquals(workingMembersList, task.getWorkingMembersList(), "Working members list mismatch");
        assertEquals(deadline, task.getDeadline(), "Deadline mismatch");
        assertEquals(comments, task.getComments(), "Comments mismatch");
        assertEquals(status, task.getStatus(), "Status mismatch");
    }
}