package interface_adapter.complete_task;

public class CompleteTaskState {

    private String projectName = "";
    private String taskName = "";
    private String taskNameError = null;
    public CompleteTaskState(CompleteTaskState copy) {
        projectName = copy.projectName;
        taskName = copy.taskName;
        taskNameError = copy.taskNameError;
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

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskNameError() {
        return taskNameError;
    }
}
