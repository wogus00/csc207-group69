package app;

import entity.CommonTaskFactory;
import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_task.*;
import view.CreateTaskView;

import javax.swing.*;
import java.io.IOException;

public class CreateTaskUseCaseFactory {
    private CreateTaskUseCaseFactory() {}

    public static CreateTaskView createTaskView(ViewManagerModel viewManagerModel,
                                                CreateTaskViewModel createTaskViewModel,
                                                CreateTaskDataAccessInterface userDataAccessObject,
                                                CreateTaskGmailDataAccessInterface gmailDataAccessObject,
                                                MainPageViewModel mainPageViewModel) {
        try {
            CreateTaskController createTaskController = createTaskUseCase(viewManagerModel, createTaskViewModel, userDataAccessObject, gmailDataAccessObject, mainPageViewModel);
            return new CreateTaskView(viewManagerModel, createTaskController, createTaskViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static CreateTaskController createTaskUseCase(ViewManagerModel viewManagerModel,
                                                          CreateTaskViewModel createTaskViewModel,
                                                          CreateTaskDataAccessInterface userDataAccessObject,
                                                          CreateTaskGmailDataAccessInterface gmailDataAccessObject,
                                                          MainPageViewModel mainPageViewModel) throws IOException {

        CreateTaskOutputBoundary createTaskOutputBoundary = new CreateTaskPresenter(viewManagerModel, createTaskViewModel, mainPageViewModel);

        TaskFactory taskFactory = new CommonTaskFactory();

        CreateTaskInputBoundary createTaskInteractor = new CreateTaskInteractor(userDataAccessObject, gmailDataAccessObject, createTaskOutputBoundary, taskFactory);

        return new CreateTaskController(createTaskInteractor);
    }
}
