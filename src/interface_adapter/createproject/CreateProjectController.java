package interface_adapter.createproject;

import use_case.createproject.CreateProjectInputBoundary;
import use_case.createproject.CreateProjectInputData;

public class CreateProjectController {

    final CreateProjectInputBoundary userCreateProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary userCreateProjectUseCaseInteractor) {
        this.userCreateProjectUseCaseInteractor = userCreateProjectUseCaseInteractor;
    }

    public void execute(String projectName, String leaderEmail, String memberEmail) {
        CreateProjectInputData createProjectInputData = new CreateProjectController(
                projectName, leaderEmail, memberEmail);

        userCreateProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
