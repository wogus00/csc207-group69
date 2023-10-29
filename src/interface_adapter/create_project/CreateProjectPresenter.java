package interface_adapter.create_project;

import interface_adapter.ViewManagerModel;
import use_case.create_project.CreateProjectOutputBoundary;
import use_case.create_project.CreateProjectOutputData;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;
    private ViewManagerModel viewManagerModel;

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
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectNameError(error);
        createProjectViewModel.firePropertyChanged();
    }
}
