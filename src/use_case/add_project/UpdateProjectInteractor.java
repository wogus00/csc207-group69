package use_case.update_project;

import entity.ProjectFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class UpdateProjectInteractor implements UpdateProjectInputBoundary {
    final UpdateProjectDataAccessInterface updateProjectDataAccessObject;
    final UpdateProjectOutputBoundary updateProjectPresenter;
    final ProjectFactory projectFactory;

    public UpdateProjectInteractor(UpdateProjectDataAccessInterface projectDataAccessInterface,
                                   UpdateProjectOutputBoundary updateProjectOutputBoundary,
                                   ProjectFactory projectFactory) {
        this.updateProjectDataAccessObject = projectDataAccessInterface;
        this.updateProjectPresenter = updateProjectOutputBoundary;
        this.projectFactory = projectFactory;
    }


    @Override
    public void execute(UpdateProjectInputData createProjectInputData) {
        //I don't udnerstand this part.
        String leaderEmail = createProjectInputData.setLeaderEmail();
        ArrayList<String> memberEmails = updateProjectInputData.addMemberEmails();
        ArrayList<String> memberEmails = updateProjectInputData.removeMemberEmails();
        //How should I fix this?
        if (!updateProjectDataAccessObject.existsByName(projectName)) {
            createProjectPresenter.prepareFailView("Project already exists.");
        } else {
            Project project = projectFactory.create(projectName, leaderEmail, memberEmails);
            createProjectDataAccessObject.save(project);

            CreateProjectOutputData createProjectOutputData = new CreateProjectOutputData(project.getProjectName(), project.getLeaderEmail(), project.getMemberEmails(), true);
            createProjectPresenter.prepareSuccessView(createProjectOutputData);
        }
    }
}