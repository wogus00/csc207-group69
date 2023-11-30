package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskPresenter;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.complete_task.CompleteTaskOutputData;
import use_case.create_meeting.CreateMeetingOutputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CompleteTaskPresenterTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CompleteTaskViewModel mockCompleteTaskViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    private CompleteTaskPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CompleteTaskPresenter(mockViewManagerModel, mockCompleteTaskViewModel, mockMainPageViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        String taskName = "task";
        CompleteTaskOutputData response = new CompleteTaskOutputData(taskName);

        // Act
        presenter.prepareSuccessView(response);


        // Assert
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(mockCompleteTaskViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Act
        presenter.prepareFailView(error);

        // Assert
        verify(mockCompleteTaskViewModel).getState().setTaskNameError(error);
        verify(mockCompleteTaskViewModel).firePropertyChanged();
    }
}
