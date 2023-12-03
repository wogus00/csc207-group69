package interface_adapter.create_announcement;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_announcement.CreateAnnouncementOutputData;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CreateAnnouncementPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateAnnouncementViewModel mockCreateAnnouncementViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
    @Mock
    private CreateAnnouncementState mockCreateAnnouncementState;
    @Mock
    private MainPageState mockMainPageState;

    private CreateAnnouncementPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CreateAnnouncementPresenter(mockViewManagerModel, mockCreateAnnouncementViewModel, mockMainPageViewModel);

        // Mocking the return of a non-null CreateAnnouncementState object
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(mockCreateAnnouncementState);

        // Mocking the return of a non-null MainPageState object
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CreateAnnouncementOutputData response = new CreateAnnouncementOutputData("Test Title", "Test Message", "2023-01-01T12:00:00", true, "Test Author", "TestId");
        ArrayList<String> mockAnnouncements = new ArrayList<>();
        when(mockMainPageState.getAnnouncements()).thenReturn(mockAnnouncements);

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockCreateAnnouncementViewModel).setState(any(CreateAnnouncementState.class));
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(anyString());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";
        CreateAnnouncementState mockCreateAnnouncementState = mock(CreateAnnouncementState.class);
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(mockCreateAnnouncementState);

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockCreateAnnouncementViewModel.getState()).setAnnouncementTitleError(error);
        verify(mockCreateAnnouncementViewModel).firePropertyChanged();
    }
}
