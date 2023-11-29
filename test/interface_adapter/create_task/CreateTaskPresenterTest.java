package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_task.CreateTaskOutputData;
import interface_adapter.create_task.CreateTaskViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CreateTaskPresenterTest {
    @Test
    public void testPrepareFailViewUpdatesViewModelWithError() {
        // Arrange
        ViewManagerModel mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        CreateTaskViewModel mockCreateTaskViewModel = Mockito.mock(CreateTaskViewModel.class);
        MainPageViewModel mockMainPageViewModel = Mockito.mock(MainPageViewModel.class);
        CreateTaskPresenter presenter = new CreateTaskPresenter(mockViewManagerModel, mockCreateTaskViewModel, mockMainPageViewModel);

        // Setup mockCreateTaskViewModel to return a non-null CreateTaskState
        CreateTaskState mockCreateTaskState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(mockCreateTaskState);

        String errorMessage = "Error occurred";

        // Act
        presenter.prepareFailView(errorMessage);

        // Assert
        Mockito.verify(mockCreateTaskViewModel).setState(Mockito.argThat(state -> state.getCreateTaskError().equals(errorMessage)));
        Mockito.verify(mockCreateTaskViewModel).firePropertyChanged();
    }

    @Test
    public void testPrepareSuccessViewUpdatesViewModelsAndActiveView() {
        // Arrange
        ViewManagerModel mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        CreateTaskViewModel mockCreateTaskViewModel = Mockito.mock(CreateTaskViewModel.class);
        MainPageViewModel mockMainPageViewModel = Mockito.mock(MainPageViewModel.class);
        CreateTaskPresenter presenter = new CreateTaskPresenter(mockViewManagerModel, mockCreateTaskViewModel, mockMainPageViewModel);

        MainPageState mockMainPageState = new MainPageState();
        ArrayList<String> mockTaskList = new ArrayList<>();
        mockMainPageState.setTaskList(mockTaskList);
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);

        CreateTaskOutputData mockOutputData = Mockito.mock(CreateTaskOutputData.class);
        String taskName = "New Task";
        when(mockOutputData.getTaskName()).thenReturn(taskName);

        // Act
        presenter.prepareSuccessView(mockOutputData);

        // Assert
        verify(mockMainPageViewModel).setState(Mockito.argThat(state -> state.getTaskList().contains(taskName)));
        verify(mockMainPageViewModel).firePropertyChanged();

        verify(mockViewManagerModel).setActiveView(anyString());
        verify(mockViewManagerModel).firePropertyChanged();
    }


}

