package use_case.complete_task;

public class CompleteTaskInputData {
    private final String projectName;
    private final String taskName;

    public CompleteTaskInputData(String projectName, String taskName) {
        this.projectName = projectName;
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }
}
