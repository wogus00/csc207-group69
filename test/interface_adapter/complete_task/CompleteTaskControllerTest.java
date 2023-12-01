package interface_adapter.complete_task;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;


import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CompleteTaskControllerTest {

    @Mock
    private CompleteTaskInputBoundary mockInteractor;

    private CompleteTaskController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CompleteTaskController(mockInteractor);
    }

    @Test
    public void testExecute_Successful() throws Exception {
        // Arrange
        String projectName = "Project";
        String taskName = "Task";
        String userEmail = "test@example.com";

        // Act
        controller.execute(projectName, taskName, userEmail);

        // Assert
        verify(mockInteractor).execute(any(CompleteTaskInputData.class));
    }
}
