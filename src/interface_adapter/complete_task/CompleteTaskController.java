package interface_adapter.complete_task;

import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;

public class CompleteTaskController {

    final CompleteTaskInputBoundary completeTaskUseCaseInteractor;

    public CompleteTaskController(CompleteTaskInputBoundary completeTaskUseCaseInteractor) {
        this.completeTaskUseCaseInteractor = completeTaskUseCaseInteractor;
    }

    public void execute(String projectName, String taskName, String userEmail) {
        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(projectName, taskName, userEmail);
        completeTaskUseCaseInteractor.execute(completeTaskInputData);
    }
}
