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
        presenter = new CompleteTaskPresenter(mockViewManagerModel, mockCompleteTaskViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        CompleteTaskOutputData response = new CompleteTaskOutputData();

        // Act
        presenter.prepareSuccessView(response);
    }
}
