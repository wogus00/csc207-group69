package interface_adapter.login;

import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * Presenter class responsible for presenting the result of user login action.
 * Updates the view model of login and main page according to the success and fail case of a login action
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final MainPageViewModel mainPageViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new LoginPresenter class with the specific models
     *
     * @param viewManagerModel Model responsible for managing view activation.
     * @param mainPageViewModel ViewModel containing data and state for the main page view of the application
     * @param loginViewModel ViewModel containing data and state for the log in view of the application
     */
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          MainPageViewModel mainPageViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the view when the creation process succeeded.
     *
     * @param response Output data produced after the execution of the login action
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the main page view.


        MainPageState mainPageState = mainPageViewModel.getState();
        mainPageState.setProjectName(response.getProjectName());
        mainPageState.setUserEmail(response.getUserEmail());
        mainPageState.setLeaderEmail(response.getLeaderEmail());
        mainPageState.setMemberEmail(response.getMemberEmails());
        mainPageState.setTaskList(response.getTaskList());
        mainPageState.setMeetingList(response.getMeetingList());
        mainPageState.setAnnouncements(response.getAnnouncements());
        this.mainPageViewModel.setState(mainPageState);
        this.mainPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(mainPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view when the creation process failed.
     * @param error String for error message indicating the reason of failure
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }
}
