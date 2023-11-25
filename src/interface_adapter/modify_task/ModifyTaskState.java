package interface_adapter.modify_task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * State class for modify task use case
 * This class is responsible for storing data related to the task being modifyd and any errors that may have occurred during the process.
 */
public class ModifyTaskState {

    private String projectName ="";
    private String taskName = "";
    private String taskNameError = null;

    private String supervisor ="";

    private String workingMembersList = "";
    private String workingMembersError = null;

    private String deadline;

    private String comments = "";
    private boolean status = false;

    /**
     * Constructs a new state by copying data from another state instance.
     *
     * @param copy the state instance to copy the data from
     */
    public ModifyTaskState(ModifyTaskState copy) {
        projectName = copy.projectName;
        taskName = copy.taskName;
        taskNameError = copy.taskNameError;
        supervisor = copy.supervisor;
        workingMembersList = copy.workingMembersList;
        workingMembersError = copy.workingMembersError;
        deadline = copy.deadline;
        comments = copy.comments;
        status = copy.status;

    }

    /**
     * Default constructor method for creating initial state.
     */
    public ModifyTaskState() {}


    /**
     * Setter method that updates taskNameError for ModifyTaskState.
     * @param taskNameError String that indicates taskNameError
     */
    public void setTaskNameError(String taskNameError) {
        this.taskNameError = taskNameError;
    }

    /**
     * Setter method that updates workingMembersError for ModifyTaskState.
     * @param workingMembersError String that indicates workingMembersError
     */
    public void setWorkingMembersError(String workingMembersError) {
        this.workingMembersError = workingMembersError;
    }

    /**
     * Setter method that updates projectName for ModifyTaskState.
     *
     * @param projectName String of the project that task belongs to.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Setter method that updates taskName for ModifyTaskState.
     * @param taskName String of the task's name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Setter method that updates working members for ModifyTaskState.
     *
     * @param text String of working members' emails.
     */
    public void setWorkingMembersList(String text) {
        this.workingMembersList = text;
    }

    /**
     * Setter method that updates status for ModifyTaskState.
     * @param status boolean of completion status. True if task is complete, false otherwise.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Setter method that updates comments for ModifyTaskState.
     * @param text String of additional comments.
     */
    public void setComments(String text) {
        this.comments = text;
    }

    /**
     * Setter method that updates deadline for ModifyTaskState.
     * @param text String of deadline in format (YYYY-MM-DD)
     */
    public void setDeadline(String text) {
        this.deadline = text;
    }


    /**
     * Setter method that updates supervisorName for ModifyTaskState.
     * @param text String of supervisor's email.
     */
    public void setSupervisorName(String text) {
        this.supervisor = text;
    }

    private ArrayList<String> strToArrayList(String text) {
        String[] array = text.split(",");
        // Modify an ArrayList from the array
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Getter method that returns the name of the project that task belongs to from ModifyTaskState.
     * @return String of project's name.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Getter method that returns additional comments from ModifyTaskState.
     * @return String of additional comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Getter method that returns the supervisor's email from ModifyTaskState.
     * @return String of supervisor's email.
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Getter method that returns supervisorName from ModifyTaskState.
     * @return String of task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Getter method that returns taskNameError from ModifyTaskState.
     * @return String that indicates taskNameError
     */
    public String getTaskNameError() {
        return taskNameError;
    }

    /**
     * Getter method that returns WorkingMembersError from ModifyTaskState.
     * @return String that indicates WorkingMembersError
     */
    public String getWorkingMembersError() {
        return workingMembersError;
    }

    /**
     * Getter method that returns deadline of the task from ModifyTaskState.
     * @return String of deadline in format (YYYY-MM-DD)
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Getter method that returns emails of working members from ModifyTaskState.
     * @return String of working member's emails.
     */
    public String getWorkingMembersList() {
        return workingMembersList;
    }
}