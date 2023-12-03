package interface_adapter.modify_task;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.modify_meeting.ModifyMeetingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_meeting.ModifyMeetingOutputData;
import use_case.modify_task.ModifyTaskOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


class ModifyTaskPresenterTest {

    private ModifyTaskPresenter presenter;
    private ModifyTaskViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private ModifyTaskOutputData mockOutputData;
    private MainPageViewModel mockMainPageViewModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockViewModel = mock(ModifyTaskViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        presenter = new ModifyTaskPresenter(mockViewManagerModel, mockViewModel);
        mockOutputData = mock(ModifyTaskOutputData.class);
        mockMainPageViewModel = mock(MainPageViewModel.class);
    }

    @Test
    void testPrepareSuccess() {
        // Arrange
        ModifyTaskOutputData mockOutputData = mock(ModifyTaskOutputData.class);

        // Act
        presenter.prepareSuccessView(mockOutputData);

        // Assert
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Task Does not Exist";

        // Mock the modify meeting state
        ModifyTaskState mockModifyTaskState = mock(ModifyTaskState.class);
        when(mockViewModel.getState()).thenReturn(mockModifyTaskState);
        when(mockModifyTaskState.getTaskNameError()).thenReturn(error);

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockViewModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        assertEquals(error, mockModifyTaskState.getTaskNameError());
        // Add more assertions based on your actual implementation
    }
}
