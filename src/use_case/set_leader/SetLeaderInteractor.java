package use_case.set_leader;

import use_case.set_leader.SetLeaderDataAccessInterface;
import use_case.set_leader.SetLeaderInputData;
import use_case.set_leader.SetLeaderOutputBoundary;

/**
 * The {@code SetLeaderInteractor} class implements the {@code SetLeaderInputBoundary} interface.
 * It serves as an interactor in the use case of setting a new leader for a project, coordinating the data access
 * and the presentation logic.
 */
public class SetLeaderInteractor implements SetLeaderInputBoundary {
    final SetLeaderDataAccessInterface setLeaderDataAccessObject;
    final SetLeaderOutputBoundary setLeaderPresenter;

    /**
     * Constructs an instance of {@code SetLeaderInteractor}.
     *
     * This constructor initializes the {@code SetLeaderInteractor} with a data access object
     * and an output boundary object. These objects are used for setting a new leader for projects
     * and handling the presentation logic after the operation, respectively.
     *
     * @param projectDataAccessInterface The data access object used for setting a new project leader
     * @param setLeaderOutputBoundary The output boundary object used for presentation logic
     */
    public SetLeaderInteractor(SetLeaderDataAccessInterface projectDataAccessInterface,
                                 SetLeaderOutputBoundary setLeaderOutputBoundary) {
        this.setLeaderDataAccessObject = projectDataAccessInterface;
        this.setLeaderPresenter = setLeaderOutputBoundary;
    }

    /**
     * Processes the request to set a new leader for a project.
     * This method takes an instance of {@code SetLeaderInputData} which contains the project name
     * and the new leader's name, updates the project details using the data access object,
     * and then prepares the success view using the presenter.
     *
     * @param setLeaderInputData The data transfer object containing information about the project and the new leader to be set
     */
    @Override
    public void updateProjectDetails(SetLeaderInputData removeEmailInputData) {
        String projectName = removeEmailInputData.getProjectName();
        String email = removeEmailInputData.getEmail();
        setLeaderDataAccessObject.SetLeaderToNewLeader(projectName, email);
        setLeaderPresenter.prepareSuccessView(email);
    }
}
