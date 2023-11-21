package interface_adapter.complete_task;

/**
 * State class for complete task use case
 * This class is responsible for storing data related to the task being completed and any errors that may have occurred during the process.
 */
public class CompleteTaskState {

    private String projectName = "";
    private String taskName = "";
    private String taskNameError = null;

    private String userEmail = "";

    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy the state instance to copy the data from
     */
    public CompleteTaskState(CompleteTaskState copy) {
        projectName = copy.projectName;
        taskName = copy.taskName;
        taskNameError = copy.taskNameError;
        userEmail = copy.userEmail;
    }

    /**
     * Default constructor method for creating initial state.
     */
    public CompleteTaskState() {}

    /**
     * Setter method to set taskName that will be completed.
     * @param taskName String of the task's name.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Setter method to set projectName that task belongs to.
     *
     * @param projectName String of the project's name that task belongs to.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Setter method to set taskNameError if taskName does not exist.
     * @param taskNameError String of error indicating that task does not exist.
     */
    public void setTaskNameError(String taskNameError) {
        this.taskNameError = taskNameError;
    }

    /**
     * Setter method to set email of the user that will complete the task.
     * @param userEmail String of user's email that will complete the task.
     */
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    /**
     * Getter method that returns name of the project that task belongs to.
     *
     * @return String of the project's name that task belongs to.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Getter method that returns the name of the task to be completed.
     * @return String of the task's name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter method that returns the error when task does not exist.
     *
     * @return String of error indicating that task does not exist.
     */
    public String getTaskNameError() {
        return taskNameError;
    }

    /**
     * Getter method that returns the email of the user that is completing the task.
     *
     * @return String of user's email that will complete the task.
     */
    public String getUserEmail() { return userEmail;
    }
}
