package use_case.modify_task;

import entity.Task;
import entity.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ModifyTaskInteractorTest {

    private ModifyTaskInteractor interactor;
    private ModifyTaskDataAccessInterface mockDataAccess;
    private ModifyTaskGmailDataAccessInterface mockGmailAccess;
    private ModifyTaskOutputBoundary mockPresenter;
    private TaskFactory mockTaskFactory;
    private ModifyTaskInputData mockInputData;

    @BeforeEach
    void setUp() {
        mockDataAccess = mock(ModifyTaskDataAccessInterface.class);
        mockGmailAccess = mock(ModifyTaskGmailDataAccessInterface.class);
        mockPresenter = mock(ModifyTaskOutputBoundary.class);
        mockTaskFactory = mock(TaskFactory.class);
        interactor = new ModifyTaskInteractor(mockDataAccess, mockGmailAccess, mockPresenter, mockTaskFactory);
        mockInputData = mock(ModifyTaskInputData.class);

        // Stubbing the methods of mockInputData
        when(mockInputData.getProjectName()).thenReturn("Project Name");
        when(mockInputData.getTaskName()).thenReturn("Task Name");
        when(mockInputData.getSupervisor()).thenReturn("supervisor@example.com");
        when(mockInputData.getWorkingMembersList()).thenReturn("member1@example.com,member2@example.com");
        when(mockInputData.getDeadline()).thenReturn("2023-12-31");
        when(mockInputData.getComments()).thenReturn("Some comments");
        when(mockInputData.getStatus()).thenReturn(true); // or false based on your test case requirements

    }

    @Test
    void testExecuteWhenTaskDoesNotExist() {
        when(mockDataAccess.taskNameExists(anyString(), anyString())).thenReturn(false);

        interactor.execute(mockInputData);

        verify(mockPresenter).prepareFailView("Task Does not Exist");
    }

    @Test
    void testExecuteWhenMemberDoesNotExist() {
        when(mockDataAccess.taskNameExists(anyString(), anyString())).thenReturn(true);
        when(mockDataAccess.memberExists(anyString(), any())).thenReturn(false);

        interactor.execute(mockInputData);

        verify(mockPresenter).prepareFailView("Member does not Exist");
    }

    @Test
    void testExecuteWithSuccessfulModification() {
        // Configure mocks to simulate successful task modification scenario
        when(mockDataAccess.taskNameExists(anyString(), anyString())).thenReturn(true);
        when(mockDataAccess.memberExists(anyString(), any())).thenReturn(true);

        // Create a mock Task to be returned by the TaskFactory
        Task mockTask = mock(Task.class);

        // Stub the TaskFactory to return the mock Task
        when(mockTaskFactory.create(anyString(), anyString(), anyString(), any(ArrayList.class), any(LocalDate.class), anyString(), anyBoolean()))
                .thenReturn(mockTask);

        // Execute the method
        interactor.execute(mockInputData);

        // Verify that the TaskFactory was called with expected arguments
        verify(mockTaskFactory).create(anyString(), anyString(), anyString(), any(ArrayList.class), any(LocalDate.class), anyString(), anyBoolean());

        // Verify that the old task was deleted and a new task was saved
        verify(mockDataAccess).deleteOldTask(anyString(), anyString());
        verify(mockDataAccess).saveTask(anyString(), eq(mockTask));

        // Verify that the success view is prepared
        verify(mockPresenter).prepareSuccessView(any(ModifyTaskOutputData.class));
    }



}

