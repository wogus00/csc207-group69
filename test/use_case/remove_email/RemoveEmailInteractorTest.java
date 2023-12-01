package use_case.remove_email;

import data_access.FirebaseAccessObject;
import entity.CommonProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemoveEmailInteractorTest {

    private RemoveEmailInteractor interactor;
    private RemoveEmailDataAccessInterface repository;
    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = mock(RemoveEmailDataAccessInterface.class);
        interactor = new RemoveEmailInteractor(repository);
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
    void removeNonExistentEmailShouldFailTest() {
        // Arrange
        String projectName = "TestProject";
        String email = "test@example.com";
        when(mockFirebaseAccessObject.getProjectInfo(projectName)).thenReturn(null); // Simulate project not existing

        // Act & Assert
        RemoveEmailInputBoundary removeEmailInteractor = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                removeEmailInteractor.updateProjectDetails(new RemoveEmailInputData(projectName, email))
        );

        // Assert the expected exception type
        assertThat(exception, instanceOf(IllegalArgumentException.class));

        // Verify that `removeMemberFromProject` was never called since the project does not exist
        verify(mockFirebaseAccessObject, never()).removeMemberFromProject(anyString(), anyString());

        // Verify that `getProjectInfo` was called to check if the project exists
        verify(mockFirebaseAccessObject).getProjectInfo(projectName);
    }

    @Test
    public void whenEmailIsRemoved_thenRepositoryShouldUpdate() {
        // Given
        RemoveEmailInputData inputData = new RemoveEmailInputData("example@example.com");

        // When
        interactor.removeEmail(inputData);

        // Then
        verify(repository).removeEmail("example@example.com");
    }
}