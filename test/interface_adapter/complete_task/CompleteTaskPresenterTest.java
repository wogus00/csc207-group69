package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import use_case.complete_task.CompleteTaskOutputData;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockCompleteTaskViewModel = mock(CompleteTaskViewModel.class);
        mockMainPageViewModel = mock(MainPageViewModel.class);
        presenter = new CompleteTaskPresenter(mockViewManagerModel, mockCompleteTaskViewModel, mockMainPageViewModel);

        // Ensure that the state is initialized
        when(mockCompleteTaskViewModel.getState()).thenReturn(new CompleteTaskState());
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String errorMessage = "Error message";

        // Act
        presenter.prepareFailView(errorMessage);

        // Assert
        assertEquals(errorMessage, mockCompleteTaskViewModel.getState().getTaskNameError());
        verify(mockCompleteTaskViewModel).firePropertyChanged();
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CompleteTaskOutputData outputData = new CompleteTaskOutputData("TaskName");

        // Mock the main page state
        MainPageState mockMainPageState = mock(MainPageState.class);
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);
        when(mockMainPageState.getTaskList()).thenReturn(new ArrayList<>());

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        verify(mockMainPageViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockMainPageViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();

        // Additional assertions depending on your implementation
        verify(mockMainPageState).getTaskList();
        // Add more assertions based on your actual implementation
    }
}