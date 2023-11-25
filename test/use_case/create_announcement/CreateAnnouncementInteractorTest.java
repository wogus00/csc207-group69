package use_case.create_announcement;

import entity.AnnouncementFactory;
import entity.CommonAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CreateAnnouncementInteractorTest {

    @Mock
    private CreateAnnouncementDataAccessInterface dataAccessObject;
    @Mock
    private CreateAnnouncementOutputBoundary presenter;
    @Mock
    private AnnouncementFactory factory;

    private CreateAnnouncementInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new CreateAnnouncementInteractor(dataAccessObject, presenter, factory);
    }

    @Test
    void testSuccessfulAnnouncementCreation() {
        CreateAnnouncementInputData inputData = new CreateAnnouncementInputData("Title", "Message", "Author");
        CommonAnnouncement mockAnnouncement = new CommonAnnouncement("Title", "Message", LocalDateTime.now(), "Author", "ID");
        when(factory.create(anyString(), anyString(), any(LocalDateTime.class), anyString(), anyString())).thenReturn(mockAnnouncement);

        interactor.execute(inputData);

        verify(factory).create(inputData.getAnnouncementTitle(), inputData.getMessage(), any(LocalDateTime.class), inputData.getAuthor(), inputData.getAnnouncementId());
        verify(dataAccessObject).save(mockAnnouncement);
        verify(presenter).prepareSuccessView(any(CreateAnnouncementOutputData.class));
    }

    @Test
    void testFailedAnnouncementCreation() {
        CreateAnnouncementInputData inputData = new CreateAnnouncementInputData("Title", "Message", "Author");
        CommonAnnouncement mockAnnouncement = new CommonAnnouncement("Title", "Message", LocalDateTime.now(), "Author", "ID");
        when(factory.create(anyString(), anyString(), any(LocalDateTime.class), anyString(), anyString())).thenReturn(mockAnnouncement);
        doThrow(new RuntimeException("Database error")).when(dataAccessObject).save(mockAnnouncement);

        interactor.execute(inputData);

        verify(factory).create(inputData.getAnnouncementTitle(), inputData.getMessage(), any(LocalDateTime.class), inputData.getAuthor(), inputData.getAnnouncementId());
        verify(dataAccessObject).save(mockAnnouncement);
        verify(presenter).prepareFailView(anyString());
    }
}
