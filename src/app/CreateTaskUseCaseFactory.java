package app;

import entity.CommonProjectFactory;
import entity.CommonTaskFactory;
import entity.ProjectFactory;
import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectPresenter;
import interface_adapter.create_project.CreateProjectViewModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.create_task.CreateTaskViewModel;
import use_case.create_project.*;
import use_case.create_task.*;
import view.CreateProjectView;
import view.CreateTaskView;

import javax.swing.*;
import java.io.IOException;

public class CreateTaskUseCaseFactory {
    private CreateTaskUseCaseFactory() {}

    public static CreateTaskView createTaskView(ViewManagerModel viewManagerModel,
                                                CreateTaskViewModel createTaskViewModel,
                                                CreateTaskDataAccessInterface userDataAccessObject,
                                                CreateTaskGmailDataAccessInterface gmailDataAccessObject) {
        try {
            CreateTaskController createTaskController = createTaskUseCase(viewManagerModel, createTaskViewModel, userDataAccessObject, gmailDataAccessObject);
            return new CreateTaskView(viewManagerModel, createTaskController, createTaskViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static CreateTaskController createTaskUseCase(ViewManagerModel viewManagerModel,
                                                          CreateTaskViewModel createTaskViewModel,
                                                          CreateTaskDataAccessInterface userDataAccessObject,
                                                          CreateTaskGmailDataAccessInterface gmailDataAccessObject) throws IOException {

        CreateTaskOutputBoundary createTaskOutputBoundary = new CreateTaskPresenter(viewManagerModel, createTaskViewModel);

        TaskFactory taskFactory = new CommonTaskFactory();

        CreateTaskInputBoundary createTaskInteractor = new CreateTaskInteractor(userDataAccessObject, gmailDataAccessObject, createTaskOutputBoundary, taskFactory);

        return new CreateTaskController(createTaskInteractor);
    }
}
