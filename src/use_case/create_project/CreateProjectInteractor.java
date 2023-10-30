package use_case.create_project;

import entity.Project;
import entity.ProjectFactory;
import entity.Project;

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
        String projectName = createProjectInputData.getProject_name();

        if (!createProjectDataAccessObject.existsByName(projectName)) {
            createProjectPresenter.prepareFailView(projectName + ": this project does not exist.");
        } else {
            CreateProjectOutputData outputData = new CreateProjectOutputData() {
                @Override
                public void prepareSuccessView(CreateProjectOutputData project) {
                    System.out.println("Project creation was successful!");
                }

                @Override
                public void prepareFailView(String error) {
                    System.out.println("Error: " + error);
                }
            };
            createProjectPresenter.prepareSuccessView(outputData);

        }
    }
}