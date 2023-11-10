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
    public void updateProjectDetails(AddEmailInputData createProjectInputData) {
        //I don't understand this part.
        String leaderEmail = createProjectInputData.setLeaderEmail();
        ArrayList<String> memberEmails = addEmailInputData.addMemberEmails();
        ArrayList<String> memberEmails = addEmailInputData.removeMemberEmails();
        //How should I fix this?
        if (!addEmailDataAccessObject.existsByName(projectName)) {
            createProjectPresenter.prepareFailView("Project already exists.");
        } else {
            Project project = projectFactory.create(projectName, leaderEmail, memberEmails);
            createProjectDataAccessObject.save(project);

            CreateProjectOutputData createProjectOutputData = new CreateProjectOutputData(project.getProjectName(), project.getLeaderEmail(), project.getMemberEmails(), true);
            createProjectPresenter.prepareSuccessView(createProjectOutputData);
        }
    }
}