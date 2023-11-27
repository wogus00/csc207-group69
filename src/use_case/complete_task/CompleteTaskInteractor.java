package use_case.complete_task;

/**
 * Public interactor class for "Complete Task" use case.
 * It is responsible for executing the  business logic associated with completing the task.
 * Interacts with FirebaseDataAccessObject to complete the task, GmailDataAccessObject to send email, and communicates with the presenter
 * to update the view based on completion of the use case.
 */
public class CompleteTaskInteractor implements CompleteTaskInputBoundary {

    final CompleteTaskDataAccessInterface completeTaskDataAccessObject;
    final CompleteTaskGmailDataAccessInterface completeTaskGmailDataAccessObject;
    final CompleteTaskOutputBoundary completeTaskPresenter;

    /**
     * Constructor class that creates new CompleteTaskInteractor class with specified data access objects and output boundary.
     *
     * @param completeTaskDataAccessObject Interface implemented by FirebaseDataAccessObject responsible for communicating with Google Firebase.
     * @param completeTaskGmailDataAccessObject Interface implemented by GmailDataAccessObject responsible for communicating with Gmail API.
     * @param completeTaskPresenter Output Boundary to present the result of the completing task.
     */
    public CompleteTaskInteractor(CompleteTaskDataAccessInterface completeTaskDataAccessObject,
                                  CompleteTaskGmailDataAccessInterface completeTaskGmailDataAccessObject,
                                  CompleteTaskOutputBoundary completeTaskPresenter) {
        this.completeTaskDataAccessObject = completeTaskDataAccessObject;
        this.completeTaskGmailDataAccessObject = completeTaskGmailDataAccessObject;
        this.completeTaskPresenter = completeTaskPresenter;
    }

    /**
     * Execution method that completes the task.
     * Determines if task exists and can be completed by the specified user.
     * Performs the completion if requirements are met.
     *
     * @param completeTaskInputData required information from CompleteTaskInputData class that will be used to determine if task can be completed and complete task by the CompleteTaskInteractor class.
     */
    @Override
    public void execute(CompleteTaskInputData completeTaskInputData) {
        String projectName = completeTaskInputData.getProjectName();
        String taskName = completeTaskInputData.getTaskName();
        String userEmail = completeTaskInputData.getUserEmail();

        // go look into firebase and check for the taskName
        if (!completeTaskDataAccessObject.taskNameExists(projectName, taskName)) {
            completeTaskPresenter.prepareFailView("Task name does not exist");
        } else if (!completeTaskDataAccessObject.userHasAccessToTask(projectName, taskName, userEmail)) {
            completeTaskPresenter.prepareFailView("User does not have access");

        } else {
            completeTaskDataAccessObject.completeTask(projectName, taskName);
            CompleteTaskOutputData completeTaskOutputData = new CompleteTaskOutputData(taskName);
            completeTaskPresenter.prepareSuccessView(completeTaskOutputData);
        }
    }
}
