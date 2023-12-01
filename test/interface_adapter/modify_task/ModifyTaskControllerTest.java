package interface_adapter.modify_task;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.modify_task.ModifyTaskInputBoundary;
import use_case.modify_task.ModifyTaskInputData;

class ModifyTaskControllerTest {

    private ModifyTaskController controller;
    private ModifyTaskInputBoundary interactor;

    @BeforeEach
    void setUp() {
        interactor = mock(ModifyTaskInputBoundary.class);
        controller = new ModifyTaskController(interactor);
    }

    @Test
    void testExecuteNormalOperation() {
        // Arrange
        String projectName = "ProjectX";
        String taskName = "TaskY";
        String supervisor = "supervisor@example.com";
        String workingMembers = "member1@example.com, member2@example.com";
        String deadline = "2023-12-31";
        String comments = "Urgent task";

        // Act
        controller.execute(projectName, taskName, supervisor, workingMembers, deadline, comments);

        // Assert
        verify(interactor, times(1)).execute(any(ModifyTaskInputData.class));
    }


    // Additional test cases can be written to cover other edge cases and error scenarios
}

