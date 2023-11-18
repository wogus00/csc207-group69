package use_case.complete_task;

public class CompleteTaskInteractor implements CompleteTaskInputBoundary {

    final CompleteTaskDataAccessInterface completeTaskDataAccessObject;
    final CompleteTaskGmailDataAccessInterface completeTaskGmailDataAccessObject;
    final CompleteTaskOutputBoundary completeTaskPresenter;

    public CompleteTaskInteractor(CompleteTaskDataAccessInterface completeTaskDataAccessObject,
                                  CompleteTaskGmailDataAccessInterface completeTaskGmailDataAccessObject,
                                  CompleteTaskOutputBoundary completeTaskPresenter) {
        this.completeTaskDataAccessObject = completeTaskDataAccessObject;
        this.completeTaskGmailDataAccessObject = completeTaskGmailDataAccessObject;
        this.completeTaskPresenter = completeTaskPresenter;
    }

    @Override
    public void execute(CompleteTaskInputData completeTaskInputData) {
        String projectName = completeTaskInputData.getProjectName();
        String taskName = completeTaskInputData.getTaskName();

        // go look into firebase and check for the taskName
        if (!completeTaskDataAccessObject.taskNameExists(projectName, taskName)) {
            completeTaskPresenter.prepareFailView("Task name does not exist");
        } else {
            completeTaskDataAccessObject.completeTask(projectName, taskName);
        }

        CompleteTaskOutputData completeTaskOutputData = new CompleteTaskOutputData();
        completeTaskPresenter.prepareSuccessView(completeTaskOutputData);
    }
}