package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_task.CreateTaskOutputData;
import interface_adapter.create_task.CreateTaskViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

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
        Mockito.when(mockCreateTaskViewModel.getState()).thenReturn(mockCreateTaskState);

        String errorMessage = "Error occurred";

        // Act
        presenter.prepareFailView(errorMessage);

        // Assert
        Mockito.verify(mockCreateTaskViewModel).setState(Mockito.argThat(state -> state.getCreateTaskError().equals(errorMessage)));
        Mockito.verify(mockCreateTaskViewModel).firePropertyChanged();
    }


}

