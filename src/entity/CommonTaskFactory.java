package entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The {@code CommonTaskFactory} class implements the {@code TaskFactory} interface.
 * It provides a method to create a new instance of {@code CommonTask}.
 */
public class CommonTaskFactory implements TaskFactory {

    /**
     * Creates and returns a new CommonTask instance.
     *
     * This method takes the project name, task name, supervisor's email, list of working member's emails,
     * deadline, additional comments, and completion status to create a new instance with these details.
     * @param projectName String of the name of the project
     * @param taskName String of the task name
     * @param supervisor String of the supervisor's email
     * @param workingMembersList List of strings of the working member's emails
     * @param deadline LocalDate of the deadline
     * @param comments String of additional comments
     * @param status boolean status of the completion. True if completed.
     * @return a new instance of CommonTask
     */
    @Override
    public Task create(String projectName, String taskName, String supervisor, ArrayList<String> workingMembersList, LocalDate deadline, String comments, boolean status) {
        return new CommonTask(projectName, taskName, supervisor, workingMembersList, deadline, comments, false);
    }
}
