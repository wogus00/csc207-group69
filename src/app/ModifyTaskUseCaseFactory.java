package app;

import entity.CommonTaskFactory;
import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.modify_task.ModifyTaskController;
import interface_adapter.modify_task.ModifyTaskController;
import interface_adapter.modify_task.ModifyTaskPresenter;
import interface_adapter.modify_task.ModifyTaskViewModel;
import interface_adapter.modify_task.ModifyTaskViewModel;
import use_case.modify_task.*;
import use_case.modify_task.ModifyTaskDataAccessInterface;
import use_case.modify_task.ModifyTaskGmailDataAccessInterface;
import view.ModifyTaskView;

import javax.swing.*;
import java.io.IOException;

public class ModifyTaskUseCaseFactory {

    private ModifyTaskUseCaseFactory() {}

    public static ModifyTaskView modifyTaskView(ViewManagerModel viewManagerModel,
                                                ModifyTaskViewModel modifyTaskViewModel,
                                                ModifyTaskDataAccessInterface userDataAccessObject,
                                                ModifyTaskGmailDataAccessInterface gmailDataAccessObject) {
        try {
            ModifyTaskController modifyTaskController = modifyTaskUseCase(viewManagerModel, modifyTaskViewModel, userDataAccessObject, gmailDataAccessObject);
            return new ModifyTaskView(viewManagerModel, modifyTaskController, modifyTaskViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static ModifyTaskController modifyTaskUseCase(ViewManagerModel viewManagerModel,
                                                          ModifyTaskViewModel modifyTaskViewModel,
                                                          ModifyTaskDataAccessInterface userDataAccessObject,
                                                          ModifyTaskGmailDataAccessInterface gmailDataAccessObject) throws IOException {

        ModifyTaskOutputBoundary modifyTaskOutputBoundary = new ModifyTaskPresenter(viewManagerModel, modifyTaskViewModel);

        TaskFactory taskFactory = new CommonTaskFactory();

        ModifyTaskInputBoundary modifyTaskInteractor = new ModifyTaskInteractor(userDataAccessObject, gmailDataAccessObject, modifyTaskOutputBoundary, taskFactory);

        return new ModifyTaskController(modifyTaskInteractor);
    }

}
