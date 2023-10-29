package interface_adapter.create_project;

import use_case.create_project.CreateProjectInputBoundary;
import use_case.create_project.CreateProjectInputData;

import java.util.ArrayList;

public class CreateProjectController {

    final CreateProjectInputBoundary userCreateProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary userCreateProjectUseCaseInteractor) {
        this.userCreateProjectUseCaseInteractor = userCreateProjectUseCaseInteractor;
    }

    public void execute(String projectName, String leaderEmail, ArrayList<String> memberEmail) {
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName, leaderEmail, memberEmail);

        userCreateProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
