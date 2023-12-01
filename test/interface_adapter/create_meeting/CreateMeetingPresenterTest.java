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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateMeetingPresenterTest {

    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private CreateMeetingPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CreateMeetingPresenter(mockViewManagerModel, mockCreateMeetingViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CreateMeetingOutputData outputData = new CreateMeetingOutputData("MeetingName", new ArrayList<>(), "date", "start", "end", "project");

        // Mock the main page state
        MainPageState mockMainPageState = mock(MainPageState.class);
        when(mockMainPageState.getMeetingList()).thenReturn(new ArrayList<>());
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        verify(mockMainPageViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        assertEquals("MeetingName", mockMainPageViewModel.getState().getMeetingList().get(0));
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Mock the create meeting state
        CreateMeetingState mockCreateMeetingState = mock(CreateMeetingState.class);
        when(mockCreateMeetingViewModel.getState()).thenReturn(mockCreateMeetingState);
        when(mockCreateMeetingState.getMeetingNameError()).thenReturn(error);

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockCreateMeetingViewModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        assertEquals(error, mockCreateMeetingState.getMeetingNameError());
        // Add more assertions based on your actual implementation
    }
}
