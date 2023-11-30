package use_case.complete_task;

/**
 * Output Data class representing the data that the user will receive for completing a task.
 * Since there isn't any information that user needs to receive after execution, this class will be empty.
 */
public class CompleteTaskOutputData {

    String taskName;

    /**
     * Constructor method that creates new CompleteTaskOutputData.
     */
    public CompleteTaskOutputData(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName(){return taskName;}
}