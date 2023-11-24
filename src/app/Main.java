package app;

import data_access.FirebaseAccessObject;
import data_access.GmailDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectViewModel;

import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_meeting.CreateMeetingDataAccessInterface;
import use_case.create_meeting.CreateMeetingGmailDataAccessInterface;
import view.*;
import interface_adapter.create_meeting.CreateMeetingViewModel;


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
        MainPageViewModel mainPageViewModel = new MainPageViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();

        FirebaseAccessObject firebaseAccessObject;
        GmailDataAccessObject gmailDataAccessObject = new GmailDataAccessObject();
        firebaseAccessObject = new FirebaseAccessObject();

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.createProjectView(viewManagerModel, createProjectViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(createProjectView, createProjectView.viewName);

        LoginView loginView = LoginUseCaseFactory.createLoginView(viewManagerModel,loginViewModel,mainPageViewModel, firebaseAccessObject);
        views.add(loginView, loginView.viewName);


        MainPageView mainPageView = new MainPageView(viewManagerModel, mainPageViewModel, loginViewModel);
        views.add(mainPageView, mainPageView.viewName);



        CreateMeetingViewModel createMeetingViewModel = new CreateMeetingViewModel();
        CreateMeetingView createMeetingView = CreateMeetingUseCaseFactory.createMeetingView(viewManagerModel, createMeetingViewModel, (CreateMeetingDataAccessInterface) firebaseAccessObject, (CreateMeetingGmailDataAccessInterface) gmailDataAccessObject, mainPageViewModel);
        views.add(createMeetingView, createMeetingView.viewName);

        application.pack();
        application.setVisible(true);

    }

}
