package entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The {@code CommonTask} class represents a common task entity.
 * It implements the {@code Task} interface and provides methods to
 * manage task details such as projectName, taskName, supervisor, workingMembersList, deadline, comments, and deadlines.
 */
public class CommonTask implements Task {
    private final String projectName;
    private String taskName;
    private String supervisor;

    private ArrayList<String> workingMembersList;

    private LocalDate deadline;
    private String comments;

    private boolean status;

    /**
     * Constructor method that constructors a new CommonTask with the specified information for tasks.
     * @param projectName String of the name of the project
     * @param taskName String of the name of the task
     * @param supervisor String of supervisor's email
     * @param workingMembersList ArrayList of Strings of working members' emails
     * @param deadline LocalDate of the deadline in format (YYYY-MM-DD)
     * @param comments String of additional comments
     * @param status boolean of the completion status. True if the task is completed.
     */
    public CommonTask(String projectName, String taskName, String supervisor, ArrayList<String> workingMembersList, LocalDate deadline, String comments, boolean status) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.supervisor = supervisor;
        this.workingMembersList = workingMembersList;
        this.deadline = deadline;
        this.comments = comments;
        this.status = status;
    }

    /**
     * getter method that returns the name of the project
     *
     * @return String of the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * getter method that returns the name of the task
     * @return String of the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * getter method that returns the email of the supervisor
     * @return String of the supervisor's email
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * getter method that returns the array list of the emails of the members
     * @return ArrayList of the working member's emails
     */
    public ArrayList<String> getWorkingMembersList() {
        return workingMembersList;
    }

    /**
     * getter method that returns the deadline of the task
     * @return LocalDate of the deadline
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * getter method that returns the additional comment of the task
     * @return String of the comment
     */
    public String getComments() {
        return comments;
    }

    /**
     * getter method that returns the completion status of the task
     * @return boolean of the completion status. True if completed.
     */
    public boolean getStatus() {
        return status;
    }
}
