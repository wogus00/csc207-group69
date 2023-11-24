package interface_adapter.complete_task;

import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;

/**
 * Controller class responsible for completing the task.
 * This class takes the input from the user regarding which task is to be completed and send it to the appropriate interactor.
 */
public class CompleteTaskController {

    final CompleteTaskInputBoundary completeTaskUseCaseInteractor;

    /**
     * Constructors a CompleteTaskController with the specified interactor.
     *
     * @param completeTaskUseCaseInteractor Interactor class responsible for business logic of completing task.
     */
    public CompleteTaskController(CompleteTaskInputBoundary completeTaskUseCaseInteractor) {
        this.completeTaskUseCaseInteractor = completeTaskUseCaseInteractor;
    }

    /**
     * Executes the completion process of the task.
     *
     * @param projectName String of the project that task belongs to.
     * @param taskName String of task's name to be completed
     * @param userEmail String of user's email that will complete the task.
     */
    public void execute(String projectName, String taskName, String userEmail) {
        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(projectName, taskName, userEmail);
        completeTaskUseCaseInteractor.execute(completeTaskInputData);
    }
}
