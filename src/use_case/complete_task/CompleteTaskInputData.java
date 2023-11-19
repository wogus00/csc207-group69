package use_case.complete_task;

public class CompleteTaskInputData {
    private final String projectName;
    private final String taskName;
    private final String userEmail;

    public CompleteTaskInputData(String projectName, String taskName, String userEmail) {
        this.userEmail = userEmail;
        this.projectName = projectName;
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
