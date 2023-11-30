package interface_adapter.complete_task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CompleteTaskStateTest {
    @Test
    public void testDefaultConstructor() {
        CompleteTaskState state = new CompleteTaskState();
        assertNull(state.getTaskNameError());
        assertEquals("", state.getProjectName());
        assertEquals("", state.getTaskName());
        assertEquals("", state.getUserEmail());
    }

    @Test
    public void testCopyConstructor() {
        CompleteTaskState original = new CompleteTaskState();
        original.setProjectName("Project");
        original.setTaskName("Task");
        original.setUserEmail("test@example.com");
        original.setTaskNameError("Error");

        CompleteTaskState copy = new CompleteTaskState(original);
        assertEquals("Project", copy.getProjectName());
        assertEquals("Task", copy.getTaskName());
        assertEquals("test@example.com", copy.getUserEmail());
        assertEquals("Error", copy.getTaskNameError());
    }
}
