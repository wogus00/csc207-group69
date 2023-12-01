package interface_adapter.modify_task;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ModifyTaskStateTest {

    private ModifyTaskState state;

    @BeforeEach
    void setUp() {
        state = new ModifyTaskState();
    }

    // Test for project name
    @Test
    void testSetAndGetProjectName() {
        String projectName = "Project X";
        state.setProjectName(projectName);
        assertEquals(projectName, state.getProjectName());
    }

    // Test for task name
    @Test
    void testSetAndGetTaskName() {
        String taskName = "Task Y";
        state.setTaskName(taskName);
        assertEquals(taskName, state.getTaskName());
    }

    // Test for supervisor
    @Test
    void testSetAndGetSupervisor() {
        String supervisor = "supervisor@example.com";
        state.setSupervisorName(supervisor);
        assertEquals(supervisor, state.getSupervisor());
    }

    // Test for working members list
    @Test
    void testSetAndGetWorkingMembersList() {
        String workingMembers = "member1@example.com, member2@example.com";
        state.setWorkingMembersList(workingMembers);
        assertEquals(workingMembers, state.getWorkingMembersList());
    }

    // Test for deadline
    @Test
    void testSetAndGetDeadline() {
        String deadline = "2023-12-31";
        state.setDeadline(deadline);
        assertEquals(deadline, state.getDeadline());
    }

    // Test for comments
    @Test
    void testSetAndGetComments() {
        String comments = "Urgent task";
        state.setComments(comments);
        assertEquals(comments, state.getComments());
    }

    // Test for status
    @Test
    void testSetAndGetStatus() {
        state.setStatus(true);
    }

    // Test for task name error
    @Test
    void testSetAndGetTaskNameError() {
        String error = "Invalid task name";
        state.setTaskNameError(error);
        assertEquals(error, state.getTaskNameError());
    }

    // Test for working members error
    @Test
    void testSetAndGetWorkingMembersError() {
        String error = "Invalid member list";
        state.setWorkingMembersError(error);
        assertEquals(error, state.getWorkingMembersError());
    }

    // Test for copy constructor
    @Test
    void testConstructorCopy() {
        ModifyTaskState originalState = new ModifyTaskState();
        originalState.setProjectName("Project A");
        originalState.setTaskName("Task B");
        originalState.setSupervisorName("supervisor@example.com");
        originalState.setWorkingMembersList("member@example.com");
        originalState.setDeadline("2023-12-31");
        originalState.setComments("Urgent");
        originalState.setStatus(true);
        originalState.setTaskNameError("Error");
        originalState.setWorkingMembersError("Error");

        ModifyTaskState copiedState = new ModifyTaskState(originalState);

        assertEquals(originalState.getProjectName(), copiedState.getProjectName());
        assertEquals(originalState.getTaskName(), copiedState.getTaskName());
        assertEquals(originalState.getSupervisor(), copiedState.getSupervisor());
        assertEquals(originalState.getWorkingMembersList(), copiedState.getWorkingMembersList());
        assertEquals(originalState.getDeadline(), copiedState.getDeadline());
        assertEquals(originalState.getComments(), copiedState.getComments());
        assertEquals(originalState.getTaskNameError(), copiedState.getTaskNameError());
        assertEquals(originalState.getWorkingMembersError(), copiedState.getWorkingMembersError());
    }

    @Test
    void testStrToArrayList() throws Exception {
        ModifyTaskState state = new ModifyTaskState();

        // Access the private method using reflection
        Method method = ModifyTaskState.class.getDeclaredMethod("strToArrayList", String.class);
        method.setAccessible(true);

        // Test with a regular string
        String testString = "member1@example.com,member2@example.com";
        ArrayList<String> result = (ArrayList<String>) method.invoke(state, testString);
        assertEquals(2, result.size());
        assertEquals("member1@example.com", result.get(0));
        assertEquals("member2@example.com", result.get(1));

        // Test with an empty string
        testString = "";
        result = (ArrayList<String>) method.invoke(state, testString);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));

        // Test with a string having only one element
        testString = "single@example.com";
        result = (ArrayList<String>) method.invoke(state, testString);
        assertEquals(1, result.size());
        assertEquals("single@example.com", result.get(0));

    }
}
