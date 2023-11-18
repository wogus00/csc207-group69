package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskPresenter;
import interface_adapter.complete_task.CompleteTaskViewModel;
import use_case.complete_task.*;
import view.CompleteTaskView;

import javax.swing.*;
import java.io.IOException;

public class CompleteTaskUseCaseFactory {
    private CompleteTaskUseCaseFactory(){}
    public static CompleteTaskView completeTaskView(ViewManagerModel viewManagerModel,
                                                    CompleteTaskViewModel completeTaskViewModel,
                                                    CompleteTaskDataAccessInterface userDataAccessObject,
                                                    CompleteTaskGmailDataAccessInterface gmailDataAccessObject) {
        CompleteTaskController completeTaskController = completeTaskUseCase(viewManagerModel, completeTaskViewModel, userDataAccessObject, gmailDataAccessObject);
        return new CompleteTaskView(viewManagerModel, completeTaskController, completeTaskViewModel);


    }

    private static CompleteTaskController completeTaskUseCase(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel, CompleteTaskDataAccessInterface userDataAccessObject, CompleteTaskGmailDataAccessInterface gmailDataAccessObject) {
        CompleteTaskOutputBoundary completeTaskOutputBoundary = new CompleteTaskPresenter(viewManagerModel, completeTaskViewModel);

        CompleteTaskInputBoundary completeTaskInteractor = new CompleteTaskInteractor(userDataAccessObject, gmailDataAccessObject, completeTaskOutputBoundary);
        return new CompleteTaskController(completeTaskInteractor);
    }
}
