package interface_adapter.create_task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTaskState {

    private String projectName ="";
    private String taskName = "";
    private String taskNameError = null;

    private String supervisor ="";

    private String workingMembersList = "";
    private String workingMembersError = null;

    private String deadline;

    private String comments = "";
    private boolean status = false;

    public CreateTaskState(CreateTaskState copy) {
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

    public CreateTaskState() {}

    public void setTaskNameError(String taskNameError) {
        this.taskNameError = taskNameError;
    }
    public void setWorkingMembersError(String workingMembersError) {
        this.workingMembersError = workingMembersError;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setWorkingMembersList(String text) {
        this.workingMembersList = text;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setComments(String text) {
        this.comments = text;
    }

    public void setDeadline(String text) {
        this.deadline = text;
    }


    public void setSupervisorName(String text) {
        this.supervisor = text;
    }

    private ArrayList<String> strToArrayList(String text) {
        String[] array = text.split(",");
        // Create an ArrayList from the array
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));
        return arrayList;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getComments() {
        return comments;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskNameError() {
        return taskNameError;
    }

    public String getWorkingMembersError() {
        return workingMembersError;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getWorkingMembersList() {
        return workingMembersList;
    }
}