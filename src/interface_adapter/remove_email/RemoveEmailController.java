package interface_adapter.remove_email;

import use_case.remove_email.RemoveEmailInputBoundary;
import use_case.remove_email.RemoveEmailInputData;

public class RemoveEmailController {
    private final RemoveEmailInputBoundary removeProjectUseCaseInteractor;

    public RemoveEmailController(RemoveEmailInputBoundary removeProjectUseCaseInteractor) {
        this.removeProjectUseCaseInteractor = removeProjectUseCaseInteractor;
    }

    public void removeProjectDetails(String projectName, String email) {
        RemoveEmailInputData removeEmailInputData = new RemoveEmailInputData(projectName, email);
        removeProjectUseCaseInteractor.updateProjectDetails(removeEmailInputData);
    }
}
