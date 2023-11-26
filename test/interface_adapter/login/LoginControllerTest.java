package interface_adapter.login;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private LoginInputBoundary mockLoginInteractor;

    private LoginController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new LoginController(mockLoginInteractor);
    }

    @Test
    public void testExecute_Successful() {
        // Arrange
        String projectName = "Test Project";
        String userEmail = "test@example.com";

        // Act
        controller.execute(projectName, userEmail);

        // Assert
        verify(mockLoginInteractor).execute(any(LoginInputData.class));
    }

}
