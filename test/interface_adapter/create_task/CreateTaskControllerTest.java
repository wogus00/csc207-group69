package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskController;
import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInputData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CreateTaskControllerTest {

    @Test
    public void testExecute() {
        // Create a mock CreateTaskInputBoundary
        CreateTaskInputBoundary mockInteractor = Mockito.mock(CreateTaskInputBoundary.class);

        // Instantiate the controller with the mock interactor
        CreateTaskController controller = new CreateTaskController(mockInteractor);

        // Define test inputs
        String projectName = "Project Z";
        String taskName = "Task 3";
        String supervisor = "Alex Johnson";
        String workingMembers = "user1@example.com, user2@example.com";
        String deadline = "2023-03-03";
        String comments = "Urgent Task";

        // Execute the method
        controller.execute(projectName, taskName, supervisor, workingMembers, deadline, comments);

        // Verify that the interactor's execute method was called with the correct CreateTaskInputData
        verify(mockInteractor).execute(any(CreateTaskInputData.class));
    }
}
