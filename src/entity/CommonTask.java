package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class CommonTask implements Task {
    private final String projectName;
    private String taskName;
    private String supervisor;

    private ArrayList<String> workingMembersList;

    private LocalDate deadline;
    private String comments;

    private boolean status;
    public CommonTask(String projectName, String taskName, String supervisor, ArrayList<String> workingMembersList, LocalDate deadline, String comments, boolean status) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.supervisor = supervisor;
        this.workingMembersList = workingMembersList;
        this.deadline = deadline;
        this.comments = comments;
        this.status = status;
    }
    public String getProjectName() {
        return projectName;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getSupervisor() {
        return supervisor;
    }
    public ArrayList<String> getWorkingMembersList() {
        return workingMembersList;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public String getComments() {
        return comments;
    }
    public boolean getStatus() {
        return status;
    }
}
