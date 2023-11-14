package interface_adapter.create_task;

import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInputData;

public class CreateTaskController {

    final CreateTaskInputBoundary createTaskUseCaseInteractor;

    public CreateTaskController(CreateTaskInputBoundary createTaskUseCaseInteractor) {
        this.createTaskUseCaseInteractor = createTaskUseCaseInteractor;
    }

    public void execute(String projectName, String taskName, String supervisor, String workingMembers, String deadline, String comments) {
        CreateTaskInputData createTaskInputData = new CreateTaskInputData(projectName, taskName, supervisor, workingMembers, deadline, comments);
        createTaskUseCaseInteractor.execute(createTaskInputData);
    }
}