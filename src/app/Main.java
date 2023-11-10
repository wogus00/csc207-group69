package app;

import data_access.FirebaseAccessObject;
import data_access.GmailDataAccessObject;
import entity.CommonProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectViewModel;

import view.CreateProjectView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException {

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

        FirebaseAccessObject firebaseAccessObject;
        GmailDataAccessObject gmailDataAccessObject = new GmailDataAccessObject();
        firebaseAccessObject = new FirebaseAccessObject();

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.createProjectView(viewManagerModel, createProjectViewModel, firebaseAccessObject, gmailDataAccessObject);
        views.add(createProjectView, createProjectView.viewName);

        viewManagerModel.setActiveView(createProjectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
