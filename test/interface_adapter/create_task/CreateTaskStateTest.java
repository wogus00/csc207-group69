package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskState;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskStateTest {

    @Test
    public void testDefaultConstructor() {
        CreateTaskState state = new CreateTaskState();
        assertNotNull(state, "State object should not be null");
        // Further assertions can be made based on the default values if they are known.
    }

    @Test
    public void testCopyConstructor() {
        CreateTaskState original = new CreateTaskState();
        original.setProjectName("Project A");
        original.setTaskName("Task A");
        original.setSupervisorName("Supervisor A");
        original.setWorkingMembersList("Member1,Member2");
        original.setDeadline("2023-01-01");
        original.setComments("Initial Comments");
        original.setStatus(true);

        CreateTaskState copy = new CreateTaskState(original);
        assertEquals("Project A", copy.getProjectName(), "Project name did not copy correctly");
        assertEquals("Task A", copy.getTaskName(), "Task name did not copy correctly");
        assertEquals("Supervisor A", copy.getSupervisor(), "Supervisor name did not copy correctly");
        assertEquals("Member1,Member2", copy.getWorkingMembersList(), "Working members list did not copy correctly");
        assertEquals("2023-01-01", copy.getDeadline(), "Deadline did not copy correctly");
        assertEquals("Initial Comments", copy.getComments(), "Comments did not copy correctly");

    }

    @Test
    public void testSettersAndGetters() {
        CreateTaskState state = new CreateTaskState();

        state.setProjectName("Project B");
        assertEquals("Project B", state.getProjectName(), "Project name was not set correctly");

        state.setTaskName("Task B");
        assertEquals("Task B", state.getTaskName(), "Task name was not set correctly");

        state.setSupervisorName("Supervisor B");
        assertEquals("Supervisor B", state.getSupervisor(), "Supervisor name was not set correctly");

        state.setWorkingMembersList("Member3,Member4");
        assertEquals("Member3,Member4", state.getWorkingMembersList(), "Working members list was not set correctly");

        state.setDeadline("2023-02-02");
        assertEquals("2023-02-02", state.getDeadline(), "Deadline was not set correctly");

        state.setComments("More Comments");
        assertEquals("More Comments", state.getComments(), "Comments were not set correctly");

//        state.setTaskNameError("Error in task name");
//        assertEquals("Error in task name", state.getTaskNameError(), "Task name error was not set correctly");
//
//        state.setWorkingMembersError("Error in working members");
//        assertEquals("Error in working members", state.getWorkingMembersError(), "Working members error was not set correctly");
    }
}
