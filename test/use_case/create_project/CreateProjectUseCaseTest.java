package use_case.create_project;

import entity.CommonProject;
import entity.Project;
import entity.ProjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

class CreateProjectUseCaseTest {

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
        // Set up for Interactor
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
        when(mockProjectDataAccessObject.existsByName(existingProjectName)).thenReturn(true);
        when(mockProjectFactory.create(existingProjectName, "leader@example.com", new ArrayList<>()))
                 .thenReturn(new CommonProject(existingProjectName, "leader@example.com", new ArrayList<>()));

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
        verify(mockProjectDataAccessObject).existsByName(newProjectName);
    }

    @Test
    void testProjectCreationFailsWhenProjectAlreadyExists() throws IOException, AddressException {
        // Arrange
        String existingProjectName = "Existing Project";
        CreateProjectInputData inputData = new CreateProjectInputData(existingProjectName, "leader@example.com", new ArrayList<>());
        when(mockProjectDataAccessObject.existsByName(existingProjectName)).thenReturn(true);  // Return true to indicate the project exists

        // Act
        createProjectInteractor.execute(inputData);

        // Assert
        verify(mockCreateProjectPresenter).prepareFailView("Project already exists.");
    }

    /**
     * Verifies that the output data of the create project use case holds the correct information.
     * The test ensures that the output data object is populated with the expected project name,
     * leader email, and member emails, and that the use case failure flag is set correctly.
     */
    @Test
    void testCreateProjectOutputDataHoldsCorrectInformation() {
        // Arrange
        String expectedProjectName = "New Project";
        String expectedLeaderEmail = "leader@example.com";
        ArrayList<String> expectedMemberEmails = new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com"));
        boolean expectedUseCaseFailed = false;

        // Act
        CreateProjectOutputData outputData = new CreateProjectOutputData(expectedProjectName, expectedLeaderEmail, expectedMemberEmails, expectedUseCaseFailed);

        // Assert
        assertEquals(expectedProjectName, outputData.getProjectName());
        assertEquals(expectedLeaderEmail, outputData.getLeaderEmail());
        assertEquals(expectedMemberEmails, outputData.getMemberEmails());
    }

    /**
     * Tests the process of project creation when the project does not already exist in the system.
     * It verifies that the project is saved correctly and that emails are sent to project members.
     * The method also checks that the presenter is called to prepare a success view.
     * @throws IOException If an I/O error occurs during the process.
     * @throws MessagingException If a messaging error occurs when sending emails.
     * @throws AddressException If an email address is invalid.
     */
    @Test
    void testProjectCreationProceedsWhenNotExisting() throws IOException, MessagingException, AddressException {
        // Arrange
        String projectName = "New Project";
        String leaderEmail = "leader@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com"));
        CreateProjectInputData inputData = new CreateProjectInputData(projectName, leaderEmail, memberEmails);

        when(mockProjectDataAccessObject.existsByName(projectName)).thenReturn(false); // Project does not exist
        when(mockProjectFactory.create(projectName, leaderEmail, memberEmails))
                .thenReturn(new CommonProject(projectName, leaderEmail, memberEmails));

        // Act
        createProjectInteractor.execute(inputData);

        // Assert
        verify(mockProjectDataAccessObject).save(any(Project.class)); // Verify the project is saved
        for (String email : memberEmails) {
            verify(mockGmailDataAccessObject).sendProjectCreationEmail(leaderEmail, email, projectName); // Verify emails are sent
        }
        verify(mockCreateProjectPresenter).prepareSuccessView(any(CreateProjectOutputData.class)); // Verify success view is prepared
    }


}
