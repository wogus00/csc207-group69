package interface_adapter.create_project;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_project.CreateProjectOutputBoundary;
import use_case.create_project.CreateProjectOutputData;

import java.awt.*;

/**
 * Presenter class for Create Project use case.
 * This class updates the view model and state based on whether the project was successfully created or not.
 */
public class CreateProjectPresenter implements CreateProjectOutputBoundary {


    private final CreateProjectViewModel createProjectViewModel;

    private final MainPageViewModel mainPageViewModel;


    private ViewManagerModel viewManagerModel;

    /**
     * Constructor method that creates CreateProjectPresenter class with the view manager model and view model.
     *
     * @param createProjectViewModel View model for the Create Project use case.
     * @param viewManagerModel View manager model that is responsible for managing the active view.
     * @param mainPageViewModel View model for the main page.
     */
    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                           CreateProjectViewModel createProjectViewModel,
                                  MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares a success view when a project has been created successfully.
     *
     * @param response The output data from the Create Meeting use case.
     */
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

    /**
     * Prepares a failure view when the creation of a project fails.
     *
     * @param error A string describing the error encountered during the creation of the meeting.
     */
    @Override
    public void prepareFailView(String error) {
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectNameError(error);
        createProjectViewModel.firePropertyChanged();
    }
}
