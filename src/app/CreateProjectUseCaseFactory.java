package app;

import entity.CommonProjectFactory;
import entity.ProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectPresenter;
import interface_adapter.create_project.CreateProjectViewModel;
import use_case.create_project.*;
import view.CreateProjectView;

import javax.swing.*;
import java.io.IOException;

public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory() {}

    public static CreateProjectView createProjectView(ViewManagerModel viewManagerModel,
                                                      CreateProjectViewModel createProjectViewModel,
                                                      CreateProjectDataAccessInterface userDataAccessObject,
                                                      CreateProjectGmailDataAccessInterface gmailDataAccessObject) {
        try {
            CreateProjectController createProjectController = createProjectUseCase(viewManagerModel, createProjectViewModel, userDataAccessObject, gmailDataAccessObject);
            return new CreateProjectView(createProjectController, createProjectViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                CreateProjectDataAccessInterface userDataAccessObject,
                                                                CreateProjectGmailDataAccessInterface gmailDataAccessObject) throws IOException {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel);

        ProjectFactory projectFactory = new CommonProjectFactory();

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(userDataAccessObject, gmailDataAccessObject, createProjectOutputBoundary, projectFactory);

        return new CreateProjectController(createProjectInteractor);
    }

}
