package interface_adapter.delete_announcement;

import entity.Announcement;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementState;
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
        DeleteAnnouncementState mockState = mock(DeleteAnnouncementState.class);
        when(deleteAnnouncementViewModel.getState()).thenReturn(mockState);

        presenter.prepareNotFoundView(announcementId);

        verify(mockState).setAnnouncementError("Announcement with ID " + announcementId + " not found.");
        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }


    @Test
    void testPrepareUnauthorizedView() {
        String announcementId = "testId";
        DeleteAnnouncementState mockState = mock(DeleteAnnouncementState.class);
        when(deleteAnnouncementViewModel.getState()).thenReturn(mockState);

        presenter.prepareUnauthorizedView(announcementId);

        verify(mockState).setAnnouncementError("Unauthorized to delete announcement with ID " + announcementId + ".");
        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }



    @Test
    void testPrepareErrorView() {
        String announcementId = "testId";
        String errorMessage = "Error Message";
        DeleteAnnouncementState mockState = mock(DeleteAnnouncementState.class);
        when(deleteAnnouncementViewModel.getState()).thenReturn(mockState);

        presenter.prepareErrorView(announcementId, errorMessage);

        verify(mockState).setAnnouncementError("Error deleting announcement with ID " + announcementId + ": " + errorMessage);
        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailureView() {
        DeleteAnnouncementOutputData data = mock(DeleteAnnouncementOutputData.class);
        when(data.getIsDeleted()).thenReturn(false);

        // Mocking an Announcement object to avoid null
        Announcement mockAnnouncement = mock(Announcement.class);
        when(data.getAnnouncement()).thenReturn(mockAnnouncement);

        // Ensuring getState() returns a non-null DeleteAnnouncementState
        DeleteAnnouncementState mockState = mock(DeleteAnnouncementState.class);
        when(deleteAnnouncementViewModel.getState()).thenReturn(mockState);

        presenter.prepareFailureView(data);

        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessView() {
        DeleteAnnouncementOutputData data = mock(DeleteAnnouncementOutputData.class);

        DeleteAnnouncementState mockDeleteState = mock(DeleteAnnouncementState.class);
        CreateAnnouncementState mockCreateState = mock(CreateAnnouncementState.class);

        // Mocking getViewName to return a non-null string
        String expectedViewName = "ExpectedViewName";
        when(createAnnouncementViewModel.getViewName()).thenReturn(expectedViewName);

        when(deleteAnnouncementViewModel.getState()).thenReturn(mockDeleteState);
        when(createAnnouncementViewModel.getState()).thenReturn(mockCreateState);

        presenter.prepareSuccessView(data);

        verify(mockDeleteState).setAnnouncement(data.getAnnouncement());
        verify(createAnnouncementViewModel).setState(mockCreateState);
        verify(viewManagerModel).setActiveView(expectedViewName); // Now we are expecting a specific string
        verify(deleteAnnouncementViewModel, times(2)).firePropertyChanged();
    }


    @Test
    void testPrepareFailView() {
        String error = "Error Message";
        DeleteAnnouncementState mockState = mock(DeleteAnnouncementState.class);
        when(deleteAnnouncementViewModel.getState()).thenReturn(mockState);

        presenter.prepareFailView(error);

        verify(mockState).setAnnouncementError(error);
        verify(deleteAnnouncementViewModel).firePropertyChanged();
    }

}

