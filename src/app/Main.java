package app;

import view.CreateProjectView;
import data_access.FileProjectDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void Main(String[] args) {

        // main application window
        JFrame application = new JFrame("Create Project");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // ViewMangerModel keeps track of which view is currently showing
        ViewManagerModel viewManagerModel = new ViewMangerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // ViewModels
        CreateProjectViewModel createProjectViewModel = new CreateProjectViewModel();

        FileProjectDataAccessObject projectDataAcessObject;

        try {
            projectDataAcessObject = new FileUserDataAccessObject("./projects.csv", new ProjectFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CreateProjectView createProjectView = createProjectUseCaseFactory.create(viewManagerModel, createProjectViewModel, projectDataAcessObject);
        views.add(createProjectView, createProjectView.viewName);

        viewManagerModel.setActiveView(createProjectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
