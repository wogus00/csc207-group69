package interface_adapter.login;

import data_access.FirebaseAccessObject;
import entity.Project;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import view.MainPageView;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final MainPageViewModel mainPageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          MainPageViewModel mainPageViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
        this.loginViewModel = loginViewModel;
    }

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

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
