package use_case.complete_task;

/**
 * Input Data class representing the data inputted by user for completing a task.
 * This class encapsulates the information necessary to identify which task is to be completed by which user.
 */
public class CompleteTaskInputData {
    private final String projectName;
    private final String taskName;
    private final String userEmail;

    /**
     * Constructor method that creates new CompleteTaskInputData with specified taskName, projectName, and userEmail.
     *
     * @param projectName name of the project that taskName belongs to
     * @param taskName name of the task that the user with userEmail will complete
     * @param userEmail email of the user who is completing the task with taskName
     */
    public CompleteTaskInputData(String projectName, String taskName, String userEmail) {
        this.userEmail = userEmail;
        this.projectName = projectName;
        this.taskName = taskName;
    }

    /**
     * Getter method that returns the projectName that task to be completed belongs to.
     *
     * @return name of the project that the completed task belongs to
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     *  Getter method that returns the taskName to be completed.
     *
     * @return name of the task to be completed
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter method that returns the userEmail that will complete the specified task.
     *
     * @return email of the user that has completed the task
     */
    public String getUserEmail() {
        return userEmail;
    }
}
