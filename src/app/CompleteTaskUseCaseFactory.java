package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskPresenter;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.complete_task.*;
import view.CompleteTaskView;

import javax.swing.*;
import java.io.IOException;

public class CompleteTaskUseCaseFactory {
    private CompleteTaskUseCaseFactory(){}
    public static CompleteTaskView completeTaskView(ViewManagerModel viewManagerModel,
                                                    CompleteTaskViewModel completeTaskViewModel,
                                                    CompleteTaskDataAccessInterface userDataAccessObject,
                                                    MainPageViewModel mainPageViewModel) {
        CompleteTaskController completeTaskController = completeTaskUseCase(viewManagerModel, completeTaskViewModel, userDataAccessObject, mainPageViewModel);
        return new CompleteTaskView(viewManagerModel, completeTaskController, completeTaskViewModel);


    }

    private static CompleteTaskController completeTaskUseCase(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel, CompleteTaskDataAccessInterface userDataAccessObject, MainPageViewModel mainPageViewModel) {
        CompleteTaskOutputBoundary completeTaskOutputBoundary = new CompleteTaskPresenter(viewManagerModel, completeTaskViewModel, mainPageViewModel);

        CompleteTaskInputBoundary completeTaskInteractor = new CompleteTaskInteractor(userDataAccessObject, completeTaskOutputBoundary);
        return new CompleteTaskController(completeTaskInteractor);
    }
}
