package interface_adapter.update_project;

import use_case.add_project.UpdateProjectInputBoundary;
import use_case.add_project.UpdateProjectInputData;

import java.util.ArrayList;

public class UpdateProjectController {
    private final UpdateProjectInputBoundary updateProjectUseCaseInteractor;

    public UpdateProjectController(UpdateProjectInputBoundary updateProjectUseCaseInteractor) {
        this.updateProjectUseCaseInteractor = updateProjectUseCaseInteractor;
    }

    public void updateProjectDetails(String ProjectName, ArrayList<String> memberEmails) {
        UpdateProjectInputData updateProjectInputData = new UpdateProjectInputData();
        updateProjectUseCaseInteractor.updateProjectDetails(updateProjectInputData);
    }
}
