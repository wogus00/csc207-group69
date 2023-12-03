package use_case.remove_email;//package use_case.remove_email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RemoveEmailInteractorTest {

    private RemoveEmailInteractor interactor;
    @Mock
    private RemoveEmailDataAccessInterface mockRemoveEmailDataAccessObject;
    @Mock
    private RemoveEmailOutputBoundary mockRemoveEmailPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new RemoveEmailInteractor(mockRemoveEmailDataAccessObject, mockRemoveEmailPresenter);
    }

    @Test
    void removeEmailSuccessfully() {
        // Arrange
        RemoveEmailInputData inputData = new RemoveEmailInputData("TestProject", "test@example.com");

        // Act
        interactor.updateProjectDetails(inputData);

        // Assert
        verify(mockRemoveEmailDataAccessObject).removeMemberFromProject("TestProject", "test@example.com");
        verify(mockRemoveEmailPresenter).prepareSuccessView("test@example.com");
    }

    @Test
    void removeNonExistentProjectShouldThrowIllegalArgumentException() {
        // Arrange
        RemoveEmailInputData inputData = new RemoveEmailInputData("NonExistentProject", "test@example.com");

        // Simulate project not existing
        doThrow(new IllegalArgumentException("Project does not exist")).when(mockRemoveEmailDataAccessObject)
                .removeMemberFromProject("NonExistentProject", "test@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> interactor.updateProjectDetails(inputData));

        // Verify that `removeMemberFromProject` was called
        verify(mockRemoveEmailDataAccessObject).removeMemberFromProject("NonExistentProject", "test@example.com");

        // Verify that `prepareSuccessView` was never called since the project does not exist
        verify(mockRemoveEmailPresenter, never()).prepareSuccessView(anyString());
    }

    // Add more test cases based on your specific use cases
}