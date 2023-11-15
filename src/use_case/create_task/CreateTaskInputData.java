package use_case.create_task;

public class CreateTaskInputData {
    private final String projectName;
    private final String taskName;
    private final String supervisor;
    private final String workingMembersList;
    private final String deadline;
    private final String comments;
    private boolean status;

    public CreateTaskInputData(String projectName, String taskName, String supervisor, String workingMembers, String deadline, String comments) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.supervisor = supervisor;
        this.comments = comments;
        this.workingMembersList = workingMembers;
        this.deadline = deadline;
        this.status = false;
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
    public String getComments() {
        return comments;
    }
    public String getDeadline() {
        return deadline;
    }
    public String getWorkingMembersList() {
        return workingMembersList;
    }
    public boolean getStatus() {
        return status;
    }
}
