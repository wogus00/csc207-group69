package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.login.LoginOutputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentCaptor.forClass;
import static org.junit.Assert.assertEquals;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;


public class LoginPresenterTest {
    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
        
    private LoginPresenter presenter;

    @Captor
    private ArgumentCaptor<LoginState> loginStateCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(mockViewManagerModel, mockMainPageViewModel, mockLoginViewModel);

        // Create a non-null LoginState object
        MainPageState mockMainPageState = new MainPageState();

        // Configure the mockLoginViewModel to return the non-null LoginState
        when(mockMainPageViewModel.getState()).thenReturn(mockMainPageState);

        // Create a non-null LoginState object
        LoginState mockLoginState = new LoginState();

        // Configure the mockLoginViewModel to return the non-null LoginState
        when(mockLoginViewModel.getState()).thenReturn(mockLoginState);

        loginStateCaptor = forClass(LoginState.class);
    }

    @Test
    public void testPrepareSuccessView() {

        String projectName = "Test Project";
        String userEmail = "test@example.com";
        String leaderEmail = "leader@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("test@example.com","test2@example.com"));
        ArrayList<String> taskList = new ArrayList<>(Arrays.asList("testTask"));
        ArrayList<String> meetingList = new ArrayList<>(Arrays.asList("testMeeting"));
        ArrayList<String> announcements = new ArrayList<>(Arrays.asList("testAnnouncementMessage"));
        // Arrange
        LoginOutputData response = new LoginOutputData(projectName, userEmail, leaderEmail, memberEmails, taskList, meetingList, announcements, false);

        // Act
        presenter.prepareSuccessView(response);

        // Assert
        verify(mockMainPageViewModel).setState(any(MainPageState.class));
        verify(mockViewManagerModel).setActiveView(mockLoginViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        String error = "Failure case error message";

        // Act
        presenter.prepareFailView(error);

        // Capture the LoginState passed to setState
        verify(mockLoginViewModel).setState(loginStateCaptor.capture());

        // Retrieve the captured LoginState
        LoginState capturedState = loginStateCaptor.getValue();

        // Assert that the error message is correctly set
        assertEquals(error, capturedState.getLoginError());
    }


}
