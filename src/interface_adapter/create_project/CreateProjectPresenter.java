package interface_adapter.create_project;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_project.CreateProjectOutputBoundary;
import use_case.create_project.CreateProjectOutputData;

import java.awt.*;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;

    private final MainPageViewModel mainPageViewModel;


    private ViewManagerModel viewManagerModel;

    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                           CreateProjectViewModel createProjectViewModel,
                                  MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    @Override
    public void prepareSuccessView(CreateProjectOutputData response) {
        // On success, switch to the project dashboard view.
        MainPageState mainPageState = new MainPageState();
        mainPageState.setProjectName(response.getProjectName());
        mainPageState.setLeaderEmail(response.getLeaderEmail());
        mainPageState.setMemberEmail(response.getMemberEmails());
        mainPageState.setUserEmail(response.getLeaderEmail());// Logs the leader in
        this.mainPageViewModel.setState(mainPageState);
        this.mainPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(this.mainPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectNameError(error);
        createProjectViewModel.firePropertyChanged();
    }
}
