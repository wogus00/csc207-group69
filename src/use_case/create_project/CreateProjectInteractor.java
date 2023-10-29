package use_case.create_project;

import entity.ProjectName;

public class CreateProjectInteractor implements CreateProjectBoundary {
    final CreateProjectDataAccessInterface projectDataAccessObject;
    final CreateProjectOutputBoundary create_projectPresenter;

    public CreateProjectInteractor(CreateProjectDataAccessInterface projectDataAccessInterface,
                                   CreateProjectOutputBoundary create_projectOutputBoundary) {
        this.projectDataAccessObject = projectDataAccessInterface;
        this.projectPresenter = create_projectOutputBoundary;
    }

    @Override
    public void execute(CreateProjectInputData CreateProjectInputData) {
        Object create_projectInputData;
        String project = create_projectInputData.getProject_name();
        if (!projectDataAccessObject.existsByName(project)) {
            create_projectPresenter.prepareFailView(project + ": this project does not exist.");
        } else {
            projectPresenter.prepareSuccessView(new CreateProjectOutputData(projectName))
        }
    }
}