package interface_adapter.modify_meeting;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_meeting.ModifyMeetingOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ModifyMeetingPresenterTest {

    @Mock
    private ModifyMeetingViewModel mockModifyMeetingViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private ModifyMeetingPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ModifyMeetingPresenter(mockViewManagerModel, mockModifyMeetingViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        String futureDate = currentDateTime.plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String futureTime = currentDateTime.plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm"));

        ModifyMeetingOutputData outputData = new ModifyMeetingOutputData("Meeting", emails, futureDate, futureTime, futureTime, "Project");

        // Mock the main page state
        MainPageState mockMainPageState = mock(MainPageState.class);
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);
        when(mockMainPageState.getMeetingList()).thenReturn(new ArrayList<>());

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        verify(mockMainPageViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        assertEquals("Meeting", mockMainPageState.getMeetingList().get(0));
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Mock the modify meeting state
        ModifyMeetingState mockModifyMeetingState = mock(ModifyMeetingState.class);
        when(mockModifyMeetingViewModel.getState()).thenReturn(mockModifyMeetingState);
        when(mockModifyMeetingState.getMeetingNameError()).thenReturn(error);

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockModifyMeetingViewModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        assertEquals(error, mockModifyMeetingState.getMeetingNameError());
        // Add more assertions based on your actual implementation
    }
}
