package interface_adapter.create_project;

import use_case.createproject.CreateProjectInputBoundary;
import use_case.createproject.CreateProjectInputData;

public class CreateProjectController {

    final CreateProjectInputBoundary userCreateProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary userCreateProjectUseCaseInteractor) {
        this.userCreateProjectUseCaseInteractor = userCreateProjectUseCaseInteractor;
    }

    public void execute(String projectName, String leaderEmail, String memberEmail) {
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(
                projectName, leaderEmail, memberEmail);

        userCreateProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
