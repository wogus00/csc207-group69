package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.ViewManagerModel;
import use_case.create_task.CreateTaskOutputData;
import interface_adapter.create_task.CreateTaskViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

public class CreateTaskPresenterTest {

    @Test
    public void testPrepareFailView() {
        // Mocking dependencies
        ViewManagerModel mockViewManager = mock(ViewManagerModel.class);
        CreateTaskViewModel mockViewModel = mock(CreateTaskViewModel.class); // Assuming this is the correct type

        // Instantiating the presenter with mocks
        CreateTaskPresenter presenter = new CreateTaskPresenter(mockViewManager, mockViewModel);

        // Simulating a failure scenario
        String error = "Network Error";
        presenter.prepareFailView(error);

        // Verify interactions with mock objects
        verify(mockViewModel).firePropertyChanged(); // Assuming this is how the presenter communicates failure
        verify(mockViewManager).setActiveView("ErrorView"); // Assuming this is how the presenter updates the view
    }

    @Test
    public void testPrepareSuccessView() {
        // Mocking dependencies
        ViewManagerModel mockViewManager = mock(ViewManagerModel.class);
        CreateTaskViewModel mockViewModel = mock(CreateTaskViewModel.class); // Assuming this is the correct type

        // Instantiating the presenter with mocks
        CreateTaskPresenter presenter = new CreateTaskPresenter(mockViewManager, mockViewModel);

        // Simulating a success scenario
        CreateTaskOutputData outputData = new CreateTaskOutputData(); // Assuming default constructor
        presenter.prepareSuccessView(outputData);

        // Verify interactions with mock objects
        verify(mockViewModel).firePropertyChanged(); // Assuming this is how the presenter communicates success
        verify(mockViewManager).setActiveView("SuccessView"); // Assuming this is how the presenter updates the view
    }
}

