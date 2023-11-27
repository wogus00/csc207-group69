package interface_adapter.delete_announcement;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.delete_announcement.DeleteAnnouncementOutputData;

import static org.mockito.Mockito.*;

class DeleteAnnouncementPresenterTest {

    @Mock
    private DeleteAnnouncementViewModel deleteAnnouncementViewModel;
    @Mock
    private CreateAnnouncementViewModel createAnnouncementViewModel;
    @Mock
    private ViewManagerModel viewManagerModel;

    private DeleteAnnouncementPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new DeleteAnnouncementPresenter(deleteAnnouncementViewModel, createAnnouncementViewModel, viewManagerModel);
    }

    @Test
    void testPrepareNotFoundView() {
        String announcementId = "testId";
        presenter.prepareNotFoundView(announcementId);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareUnauthorizedView() {
        String announcementId = "testId";
        presenter.prepareUnauthorizedView(announcementId);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareErrorView() {
        String announcementId = "testId";
        String errorMessage = "Error Message";
        presenter.prepareErrorView(announcementId, errorMessage);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailureView() {
        DeleteAnnouncementOutputData data = mock(DeleteAnnouncementOutputData.class);
        when(data.getIsDeleted()).thenReturn(false);
        when(data.getAnnouncement()).thenReturn(null);

        presenter.prepareFailureView(data);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessView() {
        DeleteAnnouncementOutputData data = mock(DeleteAnnouncementOutputData.class);

        presenter.prepareSuccessView(data);

        verify(deleteAnnouncementViewModel, times(2)).firePropertyChanged();
        verify(viewManagerModel).setActiveView(anyString());
    }

    @Test
    void testPrepareFailView() {
        String error = "Error Message";
        presenter.prepareFailView(error);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }
}

