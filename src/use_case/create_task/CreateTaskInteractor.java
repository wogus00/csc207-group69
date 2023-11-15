package use_case.create_task;

import entity.Project;
import entity.Task;
import entity.TaskFactory;
import entity.TaskFactory;
import use_case.create_project.CreateProjectOutputBoundary;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTaskInteractor implements CreateTaskInputBoundary {
    final CreateTaskDataAccessInterface createTaskDataAccessObject;
    final CreateTaskGmailDataAccessInterface createTaskGmailAccessObject;
    final CreateTaskOutputBoundary createTaskPresenter;
    final TaskFactory taskFactory;

    public CreateTaskInteractor(CreateTaskDataAccessInterface createTaskDataAccessInterface,
                                CreateTaskGmailDataAccessInterface createTaskGmailDataAccessInterface,
                                CreateTaskOutputBoundary createTaskOutputBoundary,
                                TaskFactory taskFactory) {
        this.createTaskDataAccessObject = createTaskDataAccessInterface;
        this.createTaskGmailAccessObject = createTaskGmailDataAccessInterface;
        this.createTaskPresenter = createTaskOutputBoundary;
        this.taskFactory = taskFactory;
    }

    @Override
    public void execute(CreateTaskInputData createTaskInputData) {
        String projectName = createTaskInputData.getProjectName();
        String taskName = createTaskInputData.getTaskName();
        String comments = createTaskInputData.getComments();
        String supervisor = createTaskInputData.getSupervisor();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse the string into a LocalDate object
        LocalDate deadline = LocalDate.parse(createTaskInputData.getDeadline(), formatter);

        ArrayList<String> workingMembersList = stringToArrayList(createTaskInputData.getWorkingMembersList());
        boolean status = createTaskInputData.getStatus();
        // check if taskName already exists
        // check if member exists in project name
        // then send the email to the people and prepare successView
        if (!createTaskDataAccessObject.taskNameExists(projectName, taskName)) {
            createTaskPresenter.prepareFailView("Task name already Exists");
        } else if (!createTaskDataAccessObject.memberExists(projectName, workingMembersList)) {
            createTaskPresenter.prepareFailView("Member does not exist");
        } else {
            Task newTask = taskFactory.create(projectName, taskName, supervisor, workingMembersList, deadline, comments, status);
            createTaskDataAccessObject.saveTask(projectName, newTask);

            for (String email: workingMembersList) {
                try {
                    createTaskGmailAccessObject.sendTaskCreationEmail(supervisor, email, taskName);
                } catch (MessagingException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            CreateTaskOutputData createTaskOutputData = new CreateTaskOutputData();
            createTaskPresenter.prepareSuccessView(createTaskOutputData);
        }

    }

    private ArrayList<String> stringToArrayList(String text) {
        // Split the string by commas
        String[] array = text.split(",");

        // Create an ArrayList from the array
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));
        return arrayList;
    }
}
