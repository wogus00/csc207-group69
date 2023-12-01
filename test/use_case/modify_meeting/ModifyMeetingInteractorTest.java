package use_case.modify_meeting;

import entity.CommonAnnouncement;
import entity.MeetingFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import entity.CommonMeeting;
import use_case.create_meeting.CreateMeetingInputData;


import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ModifyMeetingInteractorTest {

    @Mock
    private ModifyMeetingDataAccessInterface dataAccessObject;
    @Mock
    private ModifyMeetingGmailDataAccessInterface gmailAccessObject;
    @Mock
    private ModifyMeetingOutputBoundary presenter;
    @Mock
    private MeetingFactory factory;

    private ModifyMeetingInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new ModifyMeetingInteractor(dataAccessObject, gmailAccessObject, presenter, factory);
    }

    @Test
    void testSuccessfulMeetingCreation() throws ExecutionException, InterruptedException {
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        ModifyMeetingInputData inputData = new ModifyMeetingInputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists("Project", "Name")).thenReturn(true);
        when(dataAccessObject.memberExists("Project", emails)).thenReturn(true);
        when(factory.create("Name", emails, "11/11/1111", "11:11", "22:22", "Project")).thenReturn(new CommonMeeting("Name", emails, "11/11/1111", "11:11", "22:22", "Project"));

        interactor.execute(inputData);

        verify(presenter).prepareSuccessView(any(ModifyMeetingOutputData.class));
    }

    @Test
    void testMemberDoesNotExist() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String meetingName = "Meeting";
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("nonexistent@example.com"));
        ModifyMeetingInputData inputData = new ModifyMeetingInputData("Meeting", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists(projectName, meetingName)).thenReturn(true);
        when(dataAccessObject.memberExists(projectName, emails)).thenReturn(false);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("Member does not exist");
    }

    @Test
    void testMeetingNameDoesNotExist() throws ExecutionException, InterruptedException {
        String projectName = "Project";
        String meetingName = "ExistingMeeting";
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        ModifyMeetingInputData inputData = new ModifyMeetingInputData("ExistingMeeting", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists(projectName, meetingName)).thenReturn(false);

        interactor.execute(inputData);

        verify(presenter).prepareFailView("Meeting does not exist");
    }

    @Test
    void testExecuteError() throws MessagingException, IOException, ExecutionException, InterruptedException {
        // Mock the behavior of sendMeetingCreationEmail to throw an exception
        when(gmailAccessObject.sendMeetingModificationEmail(anyString(), anyString(), anyString())).thenThrow(new IOException("Mocked IO exception"));

        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        ModifyMeetingInputData inputData = new ModifyMeetingInputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");
        when(dataAccessObject.meetingNameExists("Project", "Name")).thenReturn(true);
        when(dataAccessObject.memberExists("Project", emails)).thenReturn(true);
        when(factory.create("Name", emails, "11/11/1111", "11:11", "22:22", "Project")).thenReturn(new CommonMeeting("Name", emails, "11/11/1111", "11:11", "22:22", "Project"));

        // Use assertThrows to verify that an exception is thrown during execution
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> interactor.execute(inputData));

        // Verify that the expected exception was thrown
        assertEquals("Mocked IO exception", thrownException.getCause().getMessage());

        // Verify that certain methods were called during the exception handling
        verify(gmailAccessObject).sendMeetingModificationEmail(anyString(), anyString(), anyString());
    }
}