package interface_adapter.modify_task;

import use_case.modify_task.ModifyTaskInputBoundary;
import use_case.modify_task.ModifyTaskInputData;

/**
 * Controller class responsible for modifying the task.
 * This class takes the input from the user regarding which task is to be modified and send it to the appropriate interactor.
 */
public class ModifyTaskController {

    final ModifyTaskInputBoundary modifyTaskUseCaseInteractor;

    /**
     * Constructs a ModifyTaskController with the specified interactor.
     * @param modifyTaskUseCaseInteractor Interactor class responsible for business logic of modifying the task.
     */
    public ModifyTaskController(ModifyTaskInputBoundary modifyTaskUseCaseInteractor) {
        this.modifyTaskUseCaseInteractor = modifyTaskUseCaseInteractor;
    }

    /**
     * Executes the modification process of the task.
     *
     * @param projectName String of the project's name that task will belong to.
     * @param taskName String of the task's name
     * @param supervisor String of the supervisor's email
     * @param workingMembers String of responsible users' emails
     * @param deadline String of deadline of the project
     * @param comments String of additional comments
     */
    public void execute(String projectName, String taskName, String supervisor, String workingMembers, String deadline, String comments) {
        ModifyTaskInputData modifyTaskInputData = new ModifyTaskInputData(projectName, taskName, supervisor, workingMembers, deadline, comments);
        modifyTaskUseCaseInteractor.execute(modifyTaskInputData);
    }
}