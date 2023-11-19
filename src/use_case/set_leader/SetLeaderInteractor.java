package use_case.set_leader;

import use_case.set_leader.SetLeaderDataAccessInterface;
import use_case.set_leader.SetLeaderInputData;
import use_case.set_leader.SetLeaderOutputBoundary;

public class SetLeaderInteractor implements SetLeaderInputBoundary {
    final SetLeaderDataAccessInterface setLeaderDataAccessObject;
    final SetLeaderOutputBoundary setLeaderPresenter;

    public SetLeaderInteractor(SetLeaderDataAccessInterface projectDataAccessInterface,
                                 SetLeaderOutputBoundary setLeaderOutputBoundary) {
        this.setLeaderDataAccessObject = projectDataAccessInterface;
        this.setLeaderPresenter = setLeaderOutputBoundary;
    }


    @Override
    public void updateProjectDetails(SetLeaderInputData removeEmailInputData) {
        String projectName = removeEmailInputData.getProjectName();
        String email = removeEmailInputData.getEmail();
        setLeaderDataAccessObject.SetLeaderToNewLeader(projectName, email);
        setLeaderPresenter.prepareSuccessView();
    }
}
