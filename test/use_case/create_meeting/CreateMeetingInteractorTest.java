package use_case.create_meeting;

import entity.CommonMeeting;
import entity.MeetingFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateMeetingInteractorTest {

    @Mock
    private CreateMeetingDataAccessInterface dataAccessObject;
    @Mock
    private CreateMeetingGmailDataAccessInterface gmailAccessObject;
    @Mock
    private CreateMeetingOutputBoundary presenter;
    @Mock
    private MeetingFactory factory;

    private CreateMeetingInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new CreateMeetingInteractor(dataAccessObject, gmailAccessObject, presenter, factory);
    }

    @Test
    void testSuccessfulMeetingCreation() throws ExecutionException, InterruptedException {
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        CreateMeetingInputData inputData = new CreateMeetingInputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists("Project", "Name")).thenReturn(false);
        when(dataAccessObject.memberExists("Project", emails)).thenReturn(true);
        when(factory.create("Name", emails, "11/11/1111", "11:11", "22:22", "Project")).thenReturn(new CommonMeeting("Name", emails, "11/11/1111", "11:11", "22:22", "Project"));

        interactor.execute(inputData);

        verify(presenter).prepareSuccessView(any(CreateMeetingOutputData.class));
    }

    @Test
    void testMemberDoesNotExist() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String meetingName = "Meeting";
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("nonexistent@example.com"));
        CreateMeetingInputData inputData = new CreateMeetingInputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists(projectName, meetingName)).thenReturn(false);
        when(dataAccessObject.memberExists(projectName, emails)).thenReturn(false);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("Member does not exist");
    }

    @Test
    void testMeetingNameAlreadyTaken() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String meetingName = "ExistingMeeting";
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        CreateMeetingInputData inputData = new CreateMeetingInputData("ExistingMeeting", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists(projectName, meetingName)).thenReturn(true);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("Meeting name is already taken");
    }

    @Test
    void testExecuteError() throws MessagingException, IOException, ExecutionException, InterruptedException {
        // Mock the behavior of sendMeetingCreationEmail to throw an exception
        when(gmailAccessObject.sendMeetingCreationEmail(anyString(), anyString(), anyString())).thenThrow(new IOException("Mocked IO exception"));

        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        CreateMeetingInputData inputData = new CreateMeetingInputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists("Project", "Name")).thenReturn(false);
        when(dataAccessObject.memberExists("Project", emails)).thenReturn(true);
        when(factory.create("Name", emails, "11/11/1111", "11:11", "22:22", "Project")).thenReturn(new CommonMeeting("Name", emails, "11/11/1111", "11:11", "22:22", "Project"));

        // Use assertThrows to verify that an exception is thrown during execution
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> interactor.execute(inputData));

        // Verify that the expected exception was thrown
        assertEquals("Mocked IO exception", thrownException.getCause().getMessage());

        // Verify that certain methods were called during the exception handling
        verify(gmailAccessObject).sendMeetingCreationEmail(anyString(), anyString(), anyString());
    }
}