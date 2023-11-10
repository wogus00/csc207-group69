package use_case.add_email;

import entity.ProjectFactory;

import java.util.ArrayList;

public class AddEmailInteractor implements AddEmailInputBoundary {
    final AddEmailDataAccessInterface addEmailDataAccessObject;
    final AddEmailOutputBoundary addEmailPresenter;

    public AddEmailInteractor(AddEmailDataAccessInterface projectDataAccessInterface,
                                   AddEmailOutputBoundary addEmailOutputBoundary) {
        this.addEmailDataAccessObject = projectDataAccessInterface;
        this.addEmailPresenter = addEmailOutputBoundary;
    }


    @Override
    public void updateProjectDetails(AddEmailInputData addEmailInputData) {
        String projectName = addEmailInputData.getProjectName();
        String email = addEmailInputData.getEmail();
        addEmailDataAccessObject.addMemberToProject(projectName, email);
        addEmailPresenter.prepareSuccessView();
    }
}