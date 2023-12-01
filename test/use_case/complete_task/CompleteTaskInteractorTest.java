package use_case.complete_task;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_announcement.CreateAnnouncementInputData;
import use_case.create_meeting.CreateMeetingInputData;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CompleteTaskInteractorTest {

    @Mock
    private CompleteTaskDataAccessInterface dataAccessObject;
    @Mock
    private CompleteTaskGmailDataAccessInterface gmailAccessObject;
    @Mock
    private CompleteTaskOutputBoundary presenter;
    @Mock
    private TaskFactory factory;

    private CompleteTaskInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new CompleteTaskInteractor(dataAccessObject, gmailAccessObject, presenter);
    }

    @Test
    void testSuccessfulMeetingCreation() throws ExecutionException, InterruptedException {
        CompleteTaskInputData inputData = new CompleteTaskInputData("Project", "Task", "test@example.com");
        when(dataAccessObject.taskNameExists("Project", "Task")).thenReturn(true);
        when(dataAccessObject.userHasAccessToTask("Project", "Task", "test@example.com")).thenReturn(true);
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        LocalDate currentDate = LocalDate.now();
        when(factory.create("Project", "Task", "supervisor@example.com", emails, currentDate, "Hurry up pls", false)).thenReturn(new CommonTask("Project", "Task", "supervisor@example.com", emails, currentDate, "Hurry up pls", false));

        interactor.execute(inputData);

        verify(presenter).prepareSuccessView(any(CompleteTaskOutputData.class));
    }

    @Test
    void testTaskExists() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String taskName = "Task";
        CompleteTaskInputData inputData = new CompleteTaskInputData("Project", "Task", "test@example.com");
        when(dataAccessObject.taskNameExists(projectName, taskName)).thenReturn(false);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("Task name does not exist");
    }

    @Test
    void testUserHasAccess() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String taskName = "Task";
        String userEmail = "test@example.com";
        CompleteTaskInputData inputData = new CompleteTaskInputData("Project", "Task", "test@example.com");
        when(dataAccessObject.taskNameExists(projectName, taskName)).thenReturn(true);
        when(dataAccessObject.userHasAccessToTask(projectName, taskName, userEmail)).thenReturn(false);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("User does not have access");
    }
}