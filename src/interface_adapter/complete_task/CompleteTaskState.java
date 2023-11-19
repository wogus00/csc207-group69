package interface_adapter.complete_task;

public class CompleteTaskState {

    private String projectName = "";
    private String taskName = "";
    private String taskNameError = null;

    private String userEmail = "";
    public CompleteTaskState(CompleteTaskState copy) {
        projectName = copy.projectName;
        taskName = copy.taskName;
        taskNameError = copy.taskNameError;
        userEmail = copy.userEmail;
    }
    public CompleteTaskState() {}

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskNameError(String taskNameError) {
        this.taskNameError = taskNameError;
    }
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskNameError() {
        return taskNameError;
    }

    public String getUserEmail() { return userEmail;
    }
}
