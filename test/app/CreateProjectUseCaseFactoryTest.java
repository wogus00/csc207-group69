package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.create_project.CreateProjectGmailDataAccessInterface;
import view.CreateProjectView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CreateProjectUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateProjectViewModel mockCreateProjectViewModel;
    @Mock
    private CreateProjectDataAccessInterface mockUserDataAccessObject;
    @Mock
    private CreateProjectGmailDataAccessInterface mockGmailDataAccessObject;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProjectView_Success() {
        // Given the necessary mocked dependencies are provided,
        // the createProjectView method should create a non-null instance of CreateProjectView.

        // Act
        CreateProjectView result = CreateProjectUseCaseFactory.createProjectView(
                mockViewManagerModel,
                mockCreateProjectViewModel,
                mockUserDataAccessObject,
                mockGmailDataAccessObject,
                mockMainPageViewModel
        );

        // Assert
        assertNotNull(result, "The CreateProjectView should be instantiated");
        // If there's more behavior in the factory that needs to be verified, add those assertions here.
        // For instance, you might check if the factory sets up the controller with the right dependencies.
    }

    // If there are other methods in the factory, add tests for those as well.
}
