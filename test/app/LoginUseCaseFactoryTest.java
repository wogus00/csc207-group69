package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LoginUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
    @Mock
    private LoginDataAccessInterface mockLoginDataAccessObject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateLoginView_Success() {
        // Setup
        // Configure mocks if necessary

        // Act
        LoginView result = LoginUseCaseFactory.createLoginView(
                mockViewManagerModel,
                mockLoginViewModel,
                mockMainPageViewModel,
                mockLoginDataAccessObject
        );

        // Assert
        assertNotNull(result);
        // Additional assertions or verifications can be added here
    }
}
