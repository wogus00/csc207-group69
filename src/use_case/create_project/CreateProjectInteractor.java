package use_case.create_project;

import entity.Project;
import entity.ProjectFactory;
import entity.ProjectName;

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
        Object createProjectInputData;
        String project = createProjectInputData.getProject_name();
        if (!projectDataAccessObject.existsByName(project)) {
            create_projectPresenter.prepareFailView(project + ": this project does not exist.");
        } else {
            projectPresenter.prepareSuccessView(new CreateProjectOutputData(projectName));
        }
    }
}