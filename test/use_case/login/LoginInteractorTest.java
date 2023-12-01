package use_case.login;

import data_access.*;
import entity.CommonProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    @Mock
    private LoginDataAccessInterface mockDataAccessObject;
    @Mock
    private LoginOutputBoundary mockPresenter;

    private LoginInteractor interactor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
        interactor = new LoginInteractor(mockDataAccessObject, mockPresenter);
    }


    @Test
    public void testExecute_Success() {
        // Test data setup
        String projectName = "Test Project";
        String userEmail = "user@example.com";
        String leaderEmail = "leader@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList(userEmail));
        ArrayList<String> taskList = new ArrayList<>(Arrays.asList("Test Task"));
        ArrayList<String> meetingList = new ArrayList<>(Arrays.asList("Test Meeting"));
        ArrayList<String> announcements = new ArrayList<>(Arrays.asList("Test Announcement Message"));

        when(mockDataAccessObject.existsByName(projectName)).thenReturn(true);
        when(mockDataAccessObject.getProjectInfo(projectName)).thenReturn(new CommonProject(projectName, leaderEmail, memberEmails));
        when(mockDataAccessObject.getInfoList(projectName, "task")).thenReturn(taskList);
        when(mockDataAccessObject.getInfoList(projectName, "meeting")).thenReturn(meetingList);
        when(mockDataAccessObject.getInfoList(projectName, "announcement")).thenReturn(announcements);

        LoginInputData inputData = new LoginInputData(projectName, userEmail);

        // Execute
        interactor.execute(inputData);

        // Verify
        verify(mockDataAccessObject).existsByName(eq(projectName));
        verify(mockDataAccessObject, times(3)).getInfoList(eq(projectName), anyString());
        verify(mockPresenter).prepareSuccessView(any(LoginOutputData.class));
    }

    @Test
    public void testExecute_ProjectNotFound() {
        // Test data setup
        String projectName = "Non-existing Project";
        String userEmail = "test@example.com";
        String errorMessage = "Project does not exist.";

        when(mockDataAccessObject.existsByName(projectName)).thenReturn(false);

        LoginInputData inputData = new LoginInputData(projectName, userEmail);

        // Execute
        interactor.execute(inputData);

        // Verify
        verify(mockDataAccessObject).existsByName(eq(projectName));
        verify(mockPresenter).prepareFailView(eq(errorMessage));
    }

    @Test
    public void testExecute_UserNotFound() {// Test data setup
        String projectName = "Test Project";
        String userEmail = "not_member@example.com";
        String leaderEmail = "leader@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("user1@example.com","user2@example.com"));
        String errorMessage = "User email not_member@example.com does not exist for the project Test Project.";

        when(mockDataAccessObject.existsByName(projectName)).thenReturn(true);
        when(mockDataAccessObject.getProjectInfo(projectName)).thenReturn(new CommonProject(projectName, leaderEmail, memberEmails));

        LoginInputData inputData = new LoginInputData(projectName, userEmail);

        // Execute
        interactor.execute(inputData);

        // Verify
        verify(mockDataAccessObject).existsByName(eq(projectName));
        verify(mockPresenter).prepareFailView(eq(errorMessage));}
}
