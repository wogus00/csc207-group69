package use_case.modify_task;

import entity.Task;
import entity.TaskFactory;
import use_case.modify_task.ModifyTaskDataAccessInterface;
import use_case.modify_task.ModifyTaskGmailDataAccessInterface;
import use_case.modify_task.ModifyTaskOutputBoundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ModifyTaskInteractor implements ModifyTaskInputBoundary {
    final ModifyTaskDataAccessInterface modifyTaskDataAccessObject;
    final ModifyTaskGmailDataAccessInterface modifyTaskGmailAccessObject;
    final ModifyTaskOutputBoundary modifyTaskPresenter;
    final TaskFactory taskFactory;
    public ModifyTaskInteractor(ModifyTaskDataAccessInterface userDataAccessObject, ModifyTaskGmailDataAccessInterface gmailDataAccessObject, ModifyTaskOutputBoundary modifyTaskOutputBoundary, TaskFactory taskFactory) {
        this.modifyTaskDataAccessObject = userDataAccessObject;
        this.modifyTaskGmailAccessObject = gmailDataAccessObject;
        this.modifyTaskPresenter = modifyTaskOutputBoundary;
        this.taskFactory = taskFactory;
    }

    @Override
    public void execute(ModifyTaskInputData modifyTaskInputData) {
        String projectName = modifyTaskInputData.getProjectName();
        String taskName = modifyTaskInputData.getTaskName();
        String comments = modifyTaskInputData.getComments();
        String supervisor = modifyTaskInputData.getSupervisor();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse the string into a LocalDate object
        LocalDate deadline = LocalDate.parse(modifyTaskInputData.getDeadline(), formatter);

        ArrayList<String> workingMembersList = stringToArrayList(modifyTaskInputData.getWorkingMembersList());
        boolean status = modifyTaskInputData.getStatus();

        if (!modifyTaskDataAccessObject.taskNameExists(projectName, taskName)) {
            modifyTaskPresenter.prepareFailView("Task Does not Exist");
        } else if (!modifyTaskDataAccessObject.memberExists(projectName, workingMembersList)){
            modifyTaskPresenter.prepareFailView("Member does not Exist");

        } else {
            Task newTask = taskFactory.create(projectName, taskName, supervisor, workingMembersList, deadline, comments, status);
            modifyTaskDataAccessObject.deleteOldTask(projectName, taskName);
            modifyTaskDataAccessObject.saveTask(projectName, newTask);

            ModifyTaskOutputData modifyTaskOutputData = new ModifyTaskOutputData();
            modifyTaskPresenter.prepareSuccessView(modifyTaskOutputData);
        }



    }

    private ArrayList<String> stringToArrayList(String text) {
        // Split the string by commas
        String[] array = text.split(",");

        // Create an ArrayList from the array
        return new ArrayList<>(Arrays.asList(array));
    }
}
