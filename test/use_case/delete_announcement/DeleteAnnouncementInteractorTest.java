package use_case.delete_announcement;

import entity.CommonAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class DeleteAnnouncementInteractorTest {

    @Mock
    private DeleteAnnouncementDataAccessInterface dataAccessObject;
    @Mock
    private DeleteAnnouncementOutputBoundary presenter;

    private DeleteAnnouncementInteractor interactor;

    private final String announcementId = "annId";
    private final String userId = "userId";
    private final CommonAnnouncement announcement = new CommonAnnouncement("title", "sample", LocalDateTime.now(), "author", "10");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new DeleteAnnouncementInteractor(dataAccessObject, presenter);
        when(dataAccessObject.getAnnouncementById(announcementId)).thenReturn(announcement);
    }

    @Test
    void testExecuteNotFound() {
        when(dataAccessObject.getAnnouncementById(announcementId)).thenReturn(null);

        interactor.execute(announcementId, userId);

        verify(presenter).prepareNotFoundView(announcementId);
    }

    @Test
    void testExecuteUnauthorized() {
        interactor.execute(announcementId, "differentUser");

        verify(presenter).prepareUnauthorizedView(announcementId);
    }

    @Test
    void testExecuteDeletionFailure() {
        when(dataAccessObject.deleteAnnouncement(announcementId)).thenReturn(false);

        interactor.execute(announcementId, announcement.getAuthor());

        verify(presenter).prepareFailureView(any(DeleteAnnouncementOutputData.class));
    }

    @Test
    void testExecuteDeletionSuccess() {
        when(dataAccessObject.deleteAnnouncement(announcementId)).thenReturn(true);

        interactor.execute(announcementId, announcement.getAuthor());

        verify(presenter).prepareSuccessView(any(DeleteAnnouncementOutputData.class));
    }

    @Test
    void testExecuteError() {
        RuntimeException exception = new RuntimeException("Error");
        when(dataAccessObject.getAnnouncementById(anyString())).thenThrow(exception);

        interactor.execute(announcementId, userId);

        verify(presenter).prepareErrorView(eq(announcementId), eq(exception.getMessage()));
    }
}

