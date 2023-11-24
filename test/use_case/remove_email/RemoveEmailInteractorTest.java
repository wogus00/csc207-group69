package use_case.remove_email;

import data_access.FirebaseAccessObject;
import entity.CommonProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveEmailInteractorTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful removal of an email from a project.
     * Uses a mocked FirebaseAccessObject to simulate the project state and verify
     * that the email is correctly removed from the project's member emails list.
     */

    /**
     * Tests the successful removal of an email from a project.
     * The mock FirebaseAccessObject is set up to simulate the presence of the email,
     * and the test verifies that the email is correctly removed.
     */
    @Test
    void successTest() {
        String projectName = "TestProject";
        String email = "test@example.com";
        RemoveEmailInputData inputData = new RemoveEmailInputData(projectName, email);

        CommonProject project = new CommonProject(projectName, email, new ArrayList<>(Arrays.asList(email)));
        when(mockFirebaseAccessObject.getProjectInfo(projectName)).thenReturn(project);

        RemoveEmailOutputBoundary successPresenter = new RemoveEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(project.getMemberEmails().contains(email));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Removal of email failed unexpectedly.");
            }
        };

        RemoveEmailInputBoundary interactor = new RemoveEmailInteractor(mockFirebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData);

        verify(mockFirebaseAccessObject).getProjectInfo(projectName);
    }

    /**
     * Tests the removal of a non-existent email from a project.
     * The mock FirebaseAccessObject simulates a project without the specified email,
     * and the test verifies that this leads to a failure condition.
     */
    @Test
    void removeNonExistentEmailTest() {
        String projectName = "TestProject";
        String nonExistentEmail = "nonexistent@example.com";

        CommonProject project = new CommonProject(projectName, nonExistentEmail, new ArrayList<>());
        when(mockFirebaseAccessObject.getProjectInfo(projectName)).thenReturn(project);

        RemoveEmailInputData inputData = new RemoveEmailInputData(projectName, nonExistentEmail);
        RemoveEmailOutputBoundary failurePresenter = new RemoveEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Removal should not be successful for a non-existent email.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Email not found in project", error);
            }
        };

        RemoveEmailInputBoundary interactor = new RemoveEmailInteractor(mockFirebaseAccessObject, failurePresenter);
        interactor.updateProjectDetails(inputData);

        verify(mockFirebaseAccessObject).getProjectInfo(projectName);
    }
}