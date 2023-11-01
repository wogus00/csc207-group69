package interface_adapter.login;

import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
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
        MainPageState.setProjectName(response.getProjectName());
        MainPageState.setUserEmail(response.getUserEmail());
        this.mainPageViewModel.setState(mainPageState);
        this.mainPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(mainPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setNotMatchError(error);
        loginViewModel.firePropertyChanged();
    }
}
