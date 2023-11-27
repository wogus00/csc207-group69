package use_case.create_task;

import entity.CommonTask;
import entity.CommonTaskFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTaskInteractorTest {

    @Test
    public void testExecuteTaskNameExists() {
        // Mock dependencies
        CreateTaskDataAccessInterface mockDataAccess = mock(CreateTaskDataAccessInterface.class);
        CreateTaskGmailDataAccessInterface mockGmailAccess = mock(CreateTaskGmailDataAccessInterface.class);
        CreateTaskOutputBoundary mockPresenter = mock(CreateTaskOutputBoundary.class);
        CommonTaskFactory mockTaskFactory = mock(CommonTaskFactory.class);

        // Configure mocks
        when(mockDataAccess.taskNameExists("Project X", "Task Y")).thenReturn(false); // Task name exists

        // Create interactor with mocks
        CreateTaskInteractor interactor = new CreateTaskInteractor(mockDataAccess, mockGmailAccess, mockPresenter, mockTaskFactory);

        // Create test input data
        CreateTaskInputData inputData = new CreateTaskInputData("Project X", "Task Y", "supervisor@example.com", "member1@example.com,member2@example.com", "2023-01-01", "Comments");

        // Execute the method
        interactor.execute(inputData);

        // Verify interactions
        verify(mockPresenter).prepareFailView("Task name already Exists");
    }

    @Test
    public void testExecuteMemberDoesNotExist() {
        // Mock dependencies
        CreateTaskDataAccessInterface mockDataAccess = mock(CreateTaskDataAccessInterface.class);
        CreateTaskGmailDataAccessInterface mockGmailAccess = mock(CreateTaskGmailDataAccessInterface.class);
        CreateTaskOutputBoundary mockPresenter = mock(CreateTaskOutputBoundary.class);
        CommonTaskFactory mockTaskFactory = mock(CommonTaskFactory.class);

        // Configure mocks
        when(mockDataAccess.taskNameExists("Project X", "Task Y")).thenReturn(true); // Task name does not exist
        when(mockDataAccess.memberExists("Project X", new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")))).thenReturn(false); // Member does not exist

        // Create interactor with mocks
        CreateTaskInteractor interactor = new CreateTaskInteractor(mockDataAccess, mockGmailAccess, mockPresenter, mockTaskFactory);

        // Create test input data
        CreateTaskInputData inputData = new CreateTaskInputData("Project X", "Task Y", "supervisor@example.com", "member1@example.com,member2@example.com", "2023-01-01", "Comments");

        // Execute the method
        interactor.execute(inputData);

        // Verify interactions
        verify(mockPresenter).prepareFailView("Member does not exist");
    }

    @Test
    public void testExecuteSuccessfulTaskCreation() throws Exception {
        // Mock dependencies
        CreateTaskDataAccessInterface mockDataAccess = mock(CreateTaskDataAccessInterface.class);
        CreateTaskGmailDataAccessInterface mockGmailAccess = mock(CreateTaskGmailDataAccessInterface.class);
        CreateTaskOutputBoundary mockPresenter = mock(CreateTaskOutputBoundary.class);
        CommonTaskFactory mockTaskFactory = mock(CommonTaskFactory.class);

        // Configure mocks
        when(mockDataAccess.taskNameExists("Project X", "Task Y")).thenReturn(true); // Task name does not exist
        when(mockDataAccess.memberExists("Project X", new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")))).thenReturn(true); // Member exists

        CommonTask mockTask = mock(CommonTask.class);
        when(mockTaskFactory.create(anyString(), anyString(), anyString(), Mockito.<ArrayList<String>>any(), any(LocalDate.class), anyString(), anyBoolean())).thenReturn(mockTask);

        // Create interactor with mocks
        CreateTaskInteractor interactor = new CreateTaskInteractor(mockDataAccess, mockGmailAccess, mockPresenter, mockTaskFactory);

        // Create test input data
        CreateTaskInputData inputData = new CreateTaskInputData("Project X", "Task Y", "supervisor@example.com", "member1@example.com,member2@example.com", "2023-01-01", "Comments");

        // Execute the method
        interactor.execute(inputData);

        // Verify interactions
        verify(mockDataAccess).saveTask(eq("Project X"), eq(mockTask));
        verify(mockGmailAccess, times(2)).sendTaskCreationEmail(anyString(), anyString(), anyString());
        verify(mockPresenter).prepareSuccessView(any(CreateTaskOutputData.class));
    }



    // Similar test cases for other scenarios like 'member does not exist' and 'successful task creation'
    // Each test will setup the mocks to simulate the specific scenario and verify the interactions accordingly
}
