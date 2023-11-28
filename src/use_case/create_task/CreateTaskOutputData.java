package use_case.create_task;

/**
 * Output Data class representing the data that the user will receive for creating a task.
 * Since there isn't any information that user needs to receive after execution, this class will be empty.
 */
public class CreateTaskOutputData {

    private final String taskName;
    /**
     * Constructor method that creates new CompleteTaskOutputData.
     */
    public CreateTaskOutputData(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName(){return taskName;}
}