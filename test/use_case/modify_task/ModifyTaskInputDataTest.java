package use_case.modify_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModifyTaskInputDataTest {

    private ModifyTaskInputData inputData;
    private final String projectName = "Project1";
    private final String taskName = "Task1";
    private final String supervisor = "supervisor@example.com";
    private final String workingMembers = "member1@example.com, member2@example.com";
    private final String deadline = "2023-12-31";
    private final String comments = "Urgent task";

    @BeforeEach
    void setUp() {
        inputData = new ModifyTaskInputData(projectName, taskName, supervisor, workingMembers, deadline, comments);
    }

    @Test
    void testGetProjectName() {
        assertEquals(projectName, inputData.getProjectName());
    }

    @Test
    void testGetTaskName() {
        assertEquals(taskName, inputData.getTaskName());
    }

    @Test
    void testGetSupervisor() {
        assertEquals(supervisor, inputData.getSupervisor());
    }

    @Test
    void testGetWorkingMembersList() {
        assertEquals(workingMembers, inputData.getWorkingMembersList());
    }

    @Test
    void testGetDeadline() {
        assertEquals(deadline, inputData.getDeadline());
    }

    @Test
    void testGetComments() {
        assertEquals(comments, inputData.getComments());
    }

    @Test
    void testGetStatus() {
        assertFalse(inputData.getStatus());
    }
}

