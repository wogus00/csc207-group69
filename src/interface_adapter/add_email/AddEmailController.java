package interface_adapter.add_email;

import use_case.add_email.AddEmailInputBoundary;
import use_case.add_email.AddEmailInputData;

public class AddEmailController {
    private final AddEmailInputBoundary addProjectUseCaseInteractor;

    public AddEmailController(AddEmailInputBoundary addProjectUseCaseInteractor) {
        this.addProjectUseCaseInteractor = addProjectUseCaseInteractor;
    }

    public void addProjectDetails(String projectName, String email) {
        AddEmailInputData addEmailInputData = new AddEmailInputData(projectName, email);
        addProjectUseCaseInteractor.updateProjectDetails(addEmailInputData);
    }
}
