package interface_adapter.create_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_meeting.CreateMeetingOutputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CreateMeetingPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    private CreateMeetingPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CreateMeetingPresenter(mockViewManagerModel, mockCreateMeetingViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        CreateMeetingOutputData response = new CreateMeetingOutputData("Name", emails, "11/11/1111", "11:11", "22:22", "Project");

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(mockCreateMeetingViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
