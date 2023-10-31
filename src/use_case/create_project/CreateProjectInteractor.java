package use_case.create_project;

import entity.Project;
import entity.ProjectFactory;
import entity.Project;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateProjectInteractor implements CreateProjectInputBoundary {
    final CreateProjectDataAccessInterface createProjectDataAccessObject;
    final CreateProjectOutputBoundary createProjectPresenter;
    final ProjectFactory projectFactory;

    public CreateProjectInteractor(CreateProjectDataAccessInterface projectDataAccessInterface,
                                   CreateProjectOutputBoundary create_projectOutputBoundary,
                                   ProjectFactory projectFactory) {
        this.createProjectDataAccessObject = projectDataAccessInterface;
        this.createProjectPresenter = create_projectOutputBoundary;
        this.projectFactory = projectFactory;
    }


    @Override
    public void execute(CreateProjectInputData createProjectInputData) {
        String projectName = createProjectInputData.getProjectName();
        String leaderEmail = createProjectInputData.getLeaderEmail();
        ArrayList<String> memberEmails = createProjectInputData.getMemberEmails();

        if (!createProjectDataAccessObject.existsByName(projectName)) {
            createProjectPresenter.prepareFailView("Project already exists.");
        } else {
            Project project = projectFactory.create(projectName, leaderEmail, memberEmails);
            createProjectDataAccessObject.save(project);

            CreateProjectOutputData createProjectOutputData = new CreateProjectOutputData(project.getProjectName(), project.getLeaderEmail(), project.getMemberEmails(), true);
            createProjectPresenter.prepareSuccessView(createProjectOutputData);
            }
        }
}