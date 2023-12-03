package use_case.set_leader;//package use_case.set_leader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SetLeaderInteractorTest {

    private SetLeaderInteractor interactor;
    @Mock
    private SetLeaderDataAccessInterface mockSetLeaderDataAccessObject;
    @Mock
    private SetLeaderOutputBoundary mockSetLeaderPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SetLeaderInteractor(mockSetLeaderDataAccessObject, mockSetLeaderPresenter);
    }

    @Test
    void setLeaderSuccessfully() {
        // Arrange
        SetLeaderInputData inputData = new SetLeaderInputData("TestProject", "newleader@example.com");

        // Act
        interactor.updateProjectDetails(inputData);

        // Assert
        verify(mockSetLeaderDataAccessObject).SetLeaderToNewLeader("TestProject", "newleader@example.com");
        verify(mockSetLeaderPresenter).prepareSuccessView("newleader@example.com");
    }

    @Test
    void setLeaderForNonExistentProjectShouldThrowIllegalArgumentException() {
        // Arrange
        SetLeaderInputData inputData = new SetLeaderInputData("NonExistentProject", "newleader@example.com");

        // Simulate project not existing
        doThrow(new IllegalArgumentException("Project does not exist")).when(mockSetLeaderDataAccessObject)
                .SetLeaderToNewLeader("NonExistentProject", "newleader@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> interactor.updateProjectDetails(inputData));

        // Verify that `SetLeaderToNewLeader` was called
        verify(mockSetLeaderDataAccessObject).SetLeaderToNewLeader("NonExistentProject", "newleader@example.com");

        // Verify that `prepareSuccessView` was never called since the project does not exist
        verify(mockSetLeaderPresenter, never()).prepareSuccessView(anyString());
    }

    // Add more test cases based on your specific use cases
}
