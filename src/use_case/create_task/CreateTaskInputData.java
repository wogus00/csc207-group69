package use_case.create_task;

/**
 * Input Data class representing the data inputted by user for creating a task.
 * This class encapsulates the necessary information to create a task.
 */
public class CreateTaskInputData {
    private final String projectName;
    private final String taskName;
    private final String supervisor;
    private final String workingMembersList;
    private final String deadline;
    private final String comments;
    private boolean status;

    /**
     * Constructor method that creates new CreateTaskInputData with specified information needed when creating task.
     *
     * @param projectName String that represents the name of the project that the task belongs to.
     * @param taskName String that represents the name of the task.
     * @param supervisor String email of the user that will supervise this task.
     * @param workingMembers String of emails of users responsible for this task.
     * @param deadline String of deadline that represents the time until the task should be completed.
     * @param comments String of comment if there are any additional comments that should be made.
     */
    public CreateTaskInputData(String projectName, String taskName, String supervisor, String workingMembers, String deadline, String comments) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.supervisor = supervisor;
        this.comments = comments;
        this.workingMembersList = workingMembers;
        this.deadline = deadline;
        this.status = false;
    }

    /**
     * Getter method to return name of the project from CreateTaskInputData.
     *
     * @return String of name of the project that task belongs to.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Getter method to return name of the task from CreateTaskInputData.
     *
     * @return String of name of the task that will be created.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter method to return email of the supervising user from CreateTaskInputData.

     * @return String of email of user that is supervising this task.
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Getter method to return additional comments from CreateTaskInputData.
     *
     * @return String of comments for the task.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Getter method to return deadline of the task from CreateTaskInputData.
     *
     * @return String of deadline formatted (YYYY-MM-DD).
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Getter method to return emails of responsible users from CreateTaskInputData.
     *
     * @return String of emails responsible for this task.
     */
    public String getWorkingMembersList() {
        return workingMembersList;
    }

    /**
     * Getter method to return completion status from CreateTaskInputData.
     *
     * @return boolean of whether the task is completed or not. True if completed, False otherwise.
     */
    public boolean getStatus() {
        return status;
    }
}
