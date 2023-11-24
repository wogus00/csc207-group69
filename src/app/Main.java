package app;

import data_access.FirebaseAccessObject;
import data_access.GmailDataAccessObject;
import entity.CommonProjectFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectViewModel;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.set_leader.SetLeaderViewModel;

import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageViewModel;
import view.CreateProjectView;
import view.LoginView;
import view.MainPageView;
import view.ViewManager;
import view.AddEmailView;
import view.RemoveEmailView;
import view.SetLeaderView;

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

        FirebaseAccessObject firebaseAccessObject;
        GmailDataAccessObject gmailDataAccessObject = new GmailDataAccessObject();
        firebaseAccessObject = new FirebaseAccessObject();

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.createProjectView(viewManagerModel, createProjectViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(createProjectView, createProjectView.viewName);

        LoginView loginView = LoginUseCaseFactory.createLoginView(viewManagerModel,loginViewModel,mainPageViewModel, firebaseAccessObject);
        views.add(loginView, loginView.viewName);

        MainPageView mainPageView = new MainPageView(viewManagerModel, mainPageViewModel);
        views.add(mainPageView, mainPageView.viewName);

        AddEmailViewModel addEmailViewModel = new AddEmailViewModel();
        AddEmailView addEmailView = AddEmailUseCaseFactory.addEmailView(viewManagerModel,  addEmailViewModel, firebaseAccessObject);
        views.add(addEmailView, addEmailView.viewName);

        RemoveEmailViewModel removeEmailViewModel = new RemoveEmailViewModel();
        RemoveEmailView removeEmailView = RemoveEmailUseCaseFactory.removeEmailView(viewManagerModel,  removeEmailViewModel, firebaseAccessObject);
        views.add(removeEmailView, removeEmailView.viewName);

        SetLeaderViewModel setLeaderViewModel = new SetLeaderViewModel();
        SetLeaderView setLeaderView = SetLeaderUseCaseFactory.setLeaderView(viewManagerModel,  setLeaderViewModel, firebaseAccessObject);
        views.add(setLeaderView, setLeaderView.viewName);

        viewManagerModel.setActiveView(removeEmailView.viewName);
        viewManagerModel.firePropertyChanged();
        application.pack();
        application.setVisible(true);

    }

}
