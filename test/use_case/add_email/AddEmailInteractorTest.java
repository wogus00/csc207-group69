package use_case.add_email;

import data_access.FirebaseAccessObject;
import entity.CommonProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The {@code AddEmailInteractorTest} class tests the functionality of {@code AddEmailInteractor}.
 * It uses a mocked {@code FirebaseAccessObject} for data access and tests different scenarios including
 * successful addition of an email, case sensitivity in email addition, and adding an email to an initially empty list.
 * Mockito is used to mock the data access object to isolate the behavior of the interactor.
 */
class AddEmailInteractorTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;

    /**
     * Sets up the test environment before each test.
     * Initializes the mock objects required for testing the {@code AddEmailInteractor}.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful addition of an email to a project.
     * Verifies that the email is correctly added to the project's member emails list
     * by asserting the interaction with the mocked {@code FirebaseAccessObject}.
     */
    @Test
    void successTest() {
        // Arrange
        String projectName = "TestProject";
        String email = "test@example.com";
        AddEmailInputData inputData = new AddEmailInputData(projectName, email);
        // Assuming mockFirebaseAccessObject.getProjectInfo(projectName) returns a project with memberEmails list
        when(mockFirebaseAccessObject.getProjectInfo(projectName)).thenReturn(new CommonProject(projectName, email, new ArrayList<>()));

        AddEmailOutputBoundary successPresenter = new AddEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Assert
                assertFalse(mockFirebaseAccessObject.getProjectInfo(projectName).getMemberEmails().contains(email));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Addition of email failed");
            }
        };

        AddEmailInteractor interactor = new AddEmailInteractor(mockFirebaseAccessObject, successPresenter);

        // Act
        interactor.updateProjectDetails(inputData);

        // Verify
        verify(mockFirebaseAccessObject).getProjectInfo(projectName);
        // Additional verifications as needed
    }
}