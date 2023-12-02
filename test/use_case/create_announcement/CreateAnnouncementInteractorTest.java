package use_case.create_announcement;

import entity.AnnouncementFactory;
import entity.CommonAnnouncement;
import entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import javax.mail.MessagingException;


class CreateAnnouncementInteractorTest {

    @Mock
    private CreateAnnouncementDataAccessInterface dataAccessObject;
    @Mock
    private CreateAnnouncementOutputBoundary presenter;
    @Mock
    private AnnouncementFactory factory;

    private CreateAnnouncementInteractor interactor;

    @Mock
    private CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new CreateAnnouncementInteractor(dataAccessObject, presenter, factory, gmailDataAccessObject);
    }

    @Test
    void testSuccessfulAnnouncementCreation() {
        CreateAnnouncementInputData inputData = new CreateAnnouncementInputData("project name","Title", "Message", "Author");
        CommonAnnouncement mockAnnouncement = new CommonAnnouncement("Title", "Message", LocalDateTime.now(), "Author", "ID");

        when(factory.create(eq(inputData.getAnnouncementTitle()), eq(inputData.getMessage()),
                any(LocalDateTime.class), eq(inputData.getAuthor()),
                eq(inputData.getAnnouncementId()))).thenReturn(mockAnnouncement);

        interactor.execute(inputData);

        verify(factory).create(eq(inputData.getAnnouncementTitle()), eq(inputData.getMessage()),
                any(LocalDateTime.class), eq(inputData.getAuthor()),
                eq(inputData.getAnnouncementId()));
        verify(dataAccessObject).save("project name", mockAnnouncement);
        verify(presenter).prepareSuccessView(any(CreateAnnouncementOutputData.class));
    }


    @Test
    void testFailedAnnouncementCreation() {
        CreateAnnouncementInputData inputData = new CreateAnnouncementInputData("project name","Title", "Message", "Author");
        CommonAnnouncement mockAnnouncement = new CommonAnnouncement("Title", "Message", LocalDateTime.now(), "Author", "ID");

        when(factory.create(eq(inputData.getAnnouncementTitle()), eq(inputData.getMessage()),
                any(LocalDateTime.class), eq(inputData.getAuthor()),
                eq(inputData.getAnnouncementId()))).thenReturn(mockAnnouncement);

        doThrow(new RuntimeException("Database error")).when(dataAccessObject).save("project name", mockAnnouncement);

        interactor.execute(inputData);

        verify(factory).create(eq(inputData.getAnnouncementTitle()), eq(inputData.getMessage()),
                any(LocalDateTime.class), eq(inputData.getAuthor()),
                eq(inputData.getAnnouncementId()));
        verify(dataAccessObject).save("project name", mockAnnouncement);
        verify(presenter).prepareFailView(anyString());
    }

    @Test
    void testEmailSendingAfterSuccessfulAnnouncementCreation() throws MessagingException, IOException {
        String projectName = "TestProject";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com"));
        Project mockProject = mock(Project.class);
        when(mockProject.getMemberEmails()).thenReturn(memberEmails);

        CreateAnnouncementInputData inputData = new CreateAnnouncementInputData("project name","Title", "Message", "Author");
        CommonAnnouncement mockAnnouncement = new CommonAnnouncement("Title", "Message", LocalDateTime.now(), "Author", "ID");

        when(factory.create(eq(inputData.getAnnouncementTitle()), eq(inputData.getMessage()),
                any(LocalDateTime.class), eq(inputData.getAuthor()),
                eq(inputData.getAnnouncementId()))).thenReturn(mockAnnouncement);

        interactor.execute(inputData);

        verify(gmailDataAccessObject, times(memberEmails.size())).sendAnnouncementCreationEmail(
                eq(inputData.getAuthor()), anyString(), eq(inputData.getAnnouncementTitle()));
    }

}
