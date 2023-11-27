package interface_adapter.create_announcement;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_announcement.CreateAnnouncementOutputData;

import static org.mockito.Mockito.*;

public class CreateAnnouncementPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateAnnouncementViewModel mockCreateAnnouncementViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    private CreateAnnouncementPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CreateAnnouncementPresenter(mockViewManagerModel, mockCreateAnnouncementViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CreateAnnouncementOutputData response = new CreateAnnouncementOutputData("Test Title", "Test Message", "2023-01-01T12:00:00", true, "Test Author", "TestId");

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(mockCreateAnnouncementViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockCreateAnnouncementViewModel).getState().setAnnouncementTitleError(error);
        verify(mockCreateAnnouncementViewModel).firePropertyChanged();
    }
}
