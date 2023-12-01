package interface_adapter.modify_task;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import use_case.modify_task.ModifyTaskOutputData;


class ModifyTaskPresenterTest {

    private ModifyTaskPresenter presenter;
    private ModifyTaskViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private ModifyTaskOutputData mockOutputData;

    @BeforeEach
    void setUp() {
        mockViewModel = mock(ModifyTaskViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        presenter = new ModifyTaskPresenter(mockViewManagerModel, mockViewModel);
        mockOutputData = mock(ModifyTaskOutputData.class);
    }

    @Test
    void testPrepareSuccessViewDoesNothing() {
        // Act
        presenter.prepareSuccessView(mockOutputData);

        // Assert
        verifyNoInteractions(mockViewModel);
        verifyNoInteractions(mockViewManagerModel);
    }

    @Test
    void testPrepareFailViewDoesNothing() {
        String error = "Error occurred";

        // Act
        presenter.prepareFailView(error);

        // Assert
        verifyNoInteractions(mockViewModel);
        verifyNoInteractions(mockViewManagerModel);
    }
}
