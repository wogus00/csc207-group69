package use_case.create_project;

import entity.CommonProject;
import entity.ProjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link CreateProjectInteractor}.
 * This class contains unit tests for CreateProjectInteractor, ensuring that it behaves correctly
 * under various scenarios such as project creation, project existence checks, and error handling.
 */
class CreateProjectInteractorTest {

    @Mock
    private CreateProjectDataAccessInterface mockProjectDataAccessObject;
    @Mock
    private CreateProjectGmailDataAccessInterface mockGmailDataAccessObject;
    @Mock
    private CreateProjectOutputBoundary mockCreateProjectPresenter;
    @Mock
    private ProjectFactory mockProjectFactory;

    private CreateProjectInteractor createProjectInteractor;

    /**
     * Sets up the testing environment before each test.
     * This method initializes the mocks and the CreateProjectInteractor instance with these mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createProjectInteractor = new CreateProjectInteractor(
                mockProjectDataAccessObject,
                mockGmailDataAccessObject,
                mockCreateProjectPresenter,
                mockProjectFactory
        );
    }

    /**
     * Tests the scenario where a project already exists in the system.
     * The method should verify that the interactor correctly identifies the existing project
     * and calls the presenter's prepareFailView method with the appropriate message.
     * @throws IOException If an I/O error occurs
     */
    @Test
    void testProjectAlreadyExists() throws IOException, AddressException {
        // Arrange
        String existingProjectName = "Existing Project";
        CreateProjectInputData inputData = new CreateProjectInputData(existingProjectName, "leader@example.com", new ArrayList<>());
        when(mockProjectDataAccessObject.existsByName(existingProjectName)).thenReturn(false);

        // Act
        createProjectInteractor.execute(inputData);

        // Assert
        verify(mockCreateProjectPresenter).prepareFailView("Project already exists.");
    }

    /**
     * Tests the successful creation of a new project.
     * This method checks if the interactor correctly handles the project creation process,
     * including saving the project and notifying the members via email.
     * @throws IOException If an I/O error occurs
     * @throws AddressException If there is an issue with the email addresses
     */
    @Test
    void testSuccessfulProjectCreation() throws IOException, AddressException {
        // Arrange
        String newProjectName = "New Project";
        CreateProjectInputData inputData = new CreateProjectInputData(newProjectName, "leader@example.com", new ArrayList<>());
        when(mockProjectDataAccessObject.existsByName(newProjectName)).thenReturn(true);
        when(mockProjectFactory.create(newProjectName, "leader@example.com", new ArrayList<>()))
                .thenReturn(new CommonProject(newProjectName, "leader@example.com", new ArrayList<>()));

        // Act
        createProjectInteractor.execute(inputData);

        // Assert
        verify(mockProjectDataAccessObject).save(any(CommonProject.class));
        verify(mockCreateProjectPresenter).prepareSuccessView(any(CreateProjectOutputData.class));
    }
}
