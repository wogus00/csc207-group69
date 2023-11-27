package use_case.create_task;

import entity.Task;
import entity.TaskFactory;

import com.google.cloud.Timestamp;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Public interactor class for "Create Task" use case.
 * It is responsible for executing the  business logic associated with creating the task.
 * Interacts with FirebaseDataAccessObject to create the task and save it to the database, GmailDataAccessObject to send emails, and communicates with the presenter
 * to update the view based on completion of the use case.
 */
public class CreateTaskInteractor implements CreateTaskInputBoundary {
    final CreateTaskDataAccessInterface createTaskDataAccessObject;
    final CreateTaskGmailDataAccessInterface createTaskGmailAccessObject;
    final CreateTaskOutputBoundary createTaskPresenter;
    final TaskFactory taskFactory;

    /**
     * Constructor class that creates new CreateTaskInteractor class with specified data access objects and output boundary.
     *
     * @param createTaskDataAccessInterface Interface implemented by FirebaseDataAccessObject responsible for communicating with Google Firebase.
     * @param createTaskGmailDataAccessInterface Interface implemented by GmailDataAccessObject responsible for communicating with Gmail API
     * @param createTaskOutputBoundary Output Boundary to present the result of the creating task
     * @param taskFactory TaskFactory entity responsible for creating the task.
     */
    public CreateTaskInteractor(CreateTaskDataAccessInterface createTaskDataAccessInterface,
                                CreateTaskGmailDataAccessInterface createTaskGmailDataAccessInterface,
                                CreateTaskOutputBoundary createTaskOutputBoundary,
                                TaskFactory taskFactory) {
        this.createTaskDataAccessObject = createTaskDataAccessInterface;
        this.createTaskGmailAccessObject = createTaskGmailDataAccessInterface;
        this.createTaskPresenter = createTaskOutputBoundary;
        this.taskFactory = taskFactory;
    }

    /**
     * Execution method that creates the task.
     * Determines if task can be created by check if taskName already exists and responsible member is part of the project.
     * If all requirements are made, it creates the new task and saves it to the firebase and send emails to the users.
     *
     * @param createTaskInputData required information from CreateTaskInputData class that will be used to determine if task can be created and create task by the CreateTaskInteractor class
     */
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
        if (createTaskDataAccessObject.taskNameExists(projectName, taskName)) {
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
            CreateTaskOutputData createTaskOutputData = new CreateTaskOutputData(taskName);
            createTaskPresenter.prepareSuccessView(createTaskOutputData);
        }

    }

    private ArrayList<String> stringToArrayList(String text) {
        // Split the string by commas
        String[] array = text.split(",");

        // Create an ArrayList from the array
        return new ArrayList<>(Arrays.asList(array));
    }
}
