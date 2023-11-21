package interface_adapter.create_task;

import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInputData;

/**
 * Controller class responsible for creating the task.
 * This class takes the input from the user regarding which task is to be created and send it to the appropriate interactor.
 */
public class CreateTaskController {

    final CreateTaskInputBoundary createTaskUseCaseInteractor;

    /**
     * Constructors a CompleteTaskController with the specified interactor.
     * @param createTaskUseCaseInteractor Interactor class responsible for business logic of creating the task.
     */
    public CreateTaskController(CreateTaskInputBoundary createTaskUseCaseInteractor) {
        this.createTaskUseCaseInteractor = createTaskUseCaseInteractor;
    }

    /**
     * Executes the creation process of the task.
     *
     * @param projectName String of the project's name that task will belong to.
     * @param taskName String of the task's name
     * @param supervisor String of the supervisor's email
     * @param workingMembers String of responsible users' emails
     * @param deadline String of deadline of the project
     * @param comments String of additional comments
     */
    public void execute(String projectName, String taskName, String supervisor, String workingMembers, String deadline, String comments) {
        CreateTaskInputData createTaskInputData = new CreateTaskInputData(projectName, taskName, supervisor, workingMembers, deadline, comments);
        createTaskUseCaseInteractor.execute(createTaskInputData);
    }
}