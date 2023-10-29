package interface_adapter.create_project;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.createproject.CreateProjectOutputBoundary;
import use_case.createproject.CreateProjectOutputData;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;

    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                           CreateProjectViewModel createProjectViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
    }

    @Override
    public void prepareSuccessView(CreateProjectOutputData response) {
        // On success, switch to the project dashboard view.
    }

    @Override
    public void prepareFailView(String error) {
        createProjectState createProjectState = CreateProjectViewModel.getState();
        createProjectState.setProjectNameError(error);
        createProjectViewModel.firePropertyChanged();
    }
}
