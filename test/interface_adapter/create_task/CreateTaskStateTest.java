package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskState;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

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


    }

    @Test
    public void testSetAndGetCreateTaskError() {
        CreateTaskState state = new CreateTaskState();
        String error = "Error message";
        state.setCreateTaskError(error);
        assertEquals(error, state.getCreateTaskError(), "Create task error was not set or retrieved correctly");
    }

    @Test
    public void testDefaultValues() {
        CreateTaskState state = new CreateTaskState();

        assertEquals("", state.getProjectName(), "Default project name should be an empty string");
        assertEquals("", state.getTaskName(), "Default task name should be an empty string");
        assertEquals("", state.getSupervisor(), "Default supervisor should be an empty string");
        assertEquals("", state.getWorkingMembersList(), "Default working members list should be an empty string");
        assertNull(state.getDeadline(), "Default deadline should be null");
        assertEquals("", state.getComments(), "Default comments should be an empty string");
        assertNull(state.getCreateTaskError(), "Default create task error should be null");
    }

    @Test
    public void testStrToArrayList() throws Exception {
        CreateTaskState state = new CreateTaskState();
        Method method = CreateTaskState.class.getDeclaredMethod("strToArrayList", String.class);
        method.setAccessible(true);

        String input = "Member1,Member2,Member3";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Member1");
        expected.add("Member2");
        expected.add("Member3");

        ArrayList<String> result = (ArrayList<String>) method.invoke(state, input);
        assertEquals(expected, result, "strToArrayList did not convert string to ArrayList correctly");

    }

    @Test
    public void testStrToArrayListWithNullInput() throws Exception {
        CreateTaskState state = new CreateTaskState();
        Method method = CreateTaskState.class.getDeclaredMethod("strToArrayList", String.class);
        method.setAccessible(true);

        // Test with null input
        Exception exception = assertThrows(InvocationTargetException.class, () -> method.invoke(state, new Object[]{null}));
        assertTrue(exception.getCause() instanceof NullPointerException, "strToArrayList should throw NullPointerException for null input");
    }

}
