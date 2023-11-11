package use_case.remove_email;

import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.remove_email.RemoveEmailInputBoundary;
import use_case.remove_email.RemoveEmailInputData;
import use_case.remove_email.RemoveEmailOutputBoundary;

public class RemoveEmailInteractor implements RemoveEmailInputBoundary {
    final RemoveEmailDataAccessInterface removeEmailDataAccessObject;
    final RemoveEmailOutputBoundary removeEmailPresenter;

    public RemoveEmailInteractor(RemoveEmailDataAccessInterface projectDataAccessInterface,
                              RemoveEmailOutputBoundary removeEmailOutputBoundary) {
        this.removeEmailDataAccessObject = projectDataAccessInterface;
        this.removeEmailPresenter = removeEmailOutputBoundary;
    }


    @Override
    public void updateProjectDetails(RemoveEmailInputData removeEmailInputData) {
        String projectName = removeEmailInputData.getProjectName();
        String email = removeEmailInputData.getEmail();
        removeEmailDataAccessObject.removeMemberToProject(projectName, email);
        removeEmailPresenter.prepareSuccessView();
    }
}