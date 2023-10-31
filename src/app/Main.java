package app;

import entity.CommonProjectFactory;
import entity.ProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectViewModel;

import data_access.FileProjectDataAccessObject;

import view.CreateProjectView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // main application window
        JFrame application = new JFrame("Create Project");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // ViewMangerModel keeps track of which view is currently showing
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // ViewModels
        CreateProjectViewModel createProjectViewModel = new CreateProjectViewModel();

        FileProjectDataAccessObject projectDataAccessObject;

        try {
            projectDataAccessObject = new FileProjectDataAccessObject("./projects.csv", new CommonProjectFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CreateProjectView createProjectView = CreateProjectUseCaseFactory.createProjectView(viewManagerModel, createProjectViewModel, projectDataAccessObject);
        views.add(createProjectView, createProjectView.viewName);

        viewManagerModel.setActiveView(createProjectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
