package app;

import entity.CommonProjectFactory;
import entity.ProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectPresenter;
import interface_adapter.create_project.CreateProjectViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_project.*;
import view.CreateProjectView;

import javax.swing.*;
import java.io.IOException;

/**
 * The {@code CreateProjectUseCaseFactory} class is a factory class responsible for constructing
 * and wiring together the components required for the create project use case.
 * It sets up the necessary controllers, presenters, view models, and data access objects.
 */
public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory() {}

    /**
     * Creates and initializes the {@code CreateProjectView} with all necessary components.
     * It builds and connects the controller, view model, and data access objects for the create project use case.
     *
     * @param viewManagerModel The view manager model for managing different views.
     * @param createProjectViewModel The view model for the create project view.
     * @param userDataAccessObject The data access object for user data.
     * @param gmailDataAccessObject The data access object for Gmail services.
     * @param mainPageViewModel The view model for the main page.
     * @return An instance of {@code CreateProjectView} initialized with a controller and view model.
     */
    public static CreateProjectView createProjectView(ViewManagerModel viewManagerModel,
                                                      CreateProjectViewModel createProjectViewModel,
                                                      CreateProjectDataAccessInterface userDataAccessObject,
                                                      CreateProjectGmailDataAccessInterface gmailDataAccessObject,
                                                      MainPageViewModel mainPageViewModel) {
        try {
            CreateProjectController createProjectController = createProjectUseCase(viewManagerModel, createProjectViewModel, userDataAccessObject, gmailDataAccessObject, mainPageViewModel);
            return new CreateProjectView(createProjectController, createProjectViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    /**
     * Private helper method to create a {@code CreateProjectController}.
     * It sets up and connects the create project interactor, output boundary, and project factory.
     *
     * @param viewManagerModel The view manager model.
     * @param createProjectViewModel The view model for the create project use case.
     * @param userDataAccessObject The data access object for user data.
     * @param gmailDataAccessObject The data access object for Gmail services.
     * @param mainPageViewModel The view model for the main page.
     * @return An instance of {@code CreateProjectController}.
     * @throws IOException If there is an issue with file handling.
     */
    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                CreateProjectDataAccessInterface userDataAccessObject,
                                                                CreateProjectGmailDataAccessInterface gmailDataAccessObject,
                                                                MainPageViewModel mainPageViewModel) throws IOException {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel, mainPageViewModel);

        ProjectFactory projectFactory = new CommonProjectFactory();

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(userDataAccessObject, gmailDataAccessObject, createProjectOutputBoundary, projectFactory);

        return new CreateProjectController(createProjectInteractor);
    }

}
