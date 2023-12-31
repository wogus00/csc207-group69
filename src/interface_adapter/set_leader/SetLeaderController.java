package interface_adapter.set_leader;

import use_case.remove_email.RemoveEmailInputData;
import use_case.set_leader.SetLeaderInputBoundary;
import use_case.set_leader.SetLeaderInputData;

/**
 * The {@code SetLeaderController} class acts as a controller within the Model-View-Controller (MVC) architecture
 * for handling requests related to setting a new leader for a project. It interacts with the use case interactor
 * to perform the operations related to setting a leader.
 */
public class SetLeaderController {
    private final SetLeaderInputBoundary setLeaderUseCaseInteractor;

    /**
     * Constructs a {@code SetLeaderController} with a given {@code SetLeaderInputBoundary}.
     * This controller will use the provided use case interactor to process requests
     * for setting a new leader for a project.
     *
     * @param setLeaderUseCaseInteractor The use case interactor to be used for setting a project leader.
     */
    public SetLeaderController(SetLeaderInputBoundary setLeaderUseCaseInteractor) {
        this.setLeaderUseCaseInteractor = setLeaderUseCaseInteractor;
    }

    public void updateProjectDetails(String projectName, String newLeader) {
        SetLeaderInputData setLeaderInputData = new SetLeaderInputData(projectName, newLeader);
        setLeaderUseCaseInteractor.updateProjectDetails(setLeaderInputData);
    }

    public void setLeaderDetails(String projectName, String email) {
        SetLeaderInputData setLeaderInputData = new SetLeaderInputData(projectName, email);
        setLeaderUseCaseInteractor.updateProjectDetails(setLeaderInputData);
    }
}
