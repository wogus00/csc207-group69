package interface_adapter.create_project;

import use_case.create_project.CreateProjectInputBoundary;
import use_case.create_project.CreateProjectInputData;

import java.io.IOException;
import java.util.ArrayList;

public class CreateProjectController {

    final CreateProjectInputBoundary createProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary CreateProjectUseCaseInteractor) {
        this.createProjectUseCaseInteractor = CreateProjectUseCaseInteractor;
    }

    public void execute(String projectName, String leaderEmail, ArrayList<String> memberEmails) throws IOException {
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName, leaderEmail, memberEmails);

        createProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
