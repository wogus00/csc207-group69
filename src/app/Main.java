package app;

import data_access.FirebaseAccessObject;
import data_access.GmailDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.create_project.CreateProjectViewModel;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import interface_adapter.modify_task.ModifyTaskViewModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.set_leader.SetLeaderViewModel;

import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.login.LoginDataAccessInterface;
import view.*;
import view.AddEmailView;
import view.RemoveEmailView;
import view.SetLeaderView;
import view.CreateProjectView;
import view.CreateTaskView;
import view.LoginView;
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
        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        CompleteTaskViewModel completeTaskViewModel = new CompleteTaskViewModel();
        ModifyTaskViewModel modifyTaskViewModel = new ModifyTaskViewModel();
        CreateMeetingViewModel createMeetingViewModel = new CreateMeetingViewModel();
        ModifyMeetingViewModel modifyMeetingViewModel = new ModifyMeetingViewModel();
        CreateAnnouncementViewModel createAnnouncementViewModel =  new CreateAnnouncementViewModel();
        DeleteAnnouncementViewModel deleteAnnouncementViewModel = new DeleteAnnouncementViewModel();

        FirebaseAccessObject firebaseAccessObject;
        GmailDataAccessObject gmailDataAccessObject = new GmailDataAccessObject();
        firebaseAccessObject = new FirebaseAccessObject();

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.createProjectView(viewManagerModel, createProjectViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(createProjectView, createProjectView.viewName);

        LoginView loginView = LoginUseCaseFactory.createLoginView(viewManagerModel,loginViewModel,mainPageViewModel, firebaseAccessObject);
        views.add(loginView, loginView.viewName);

        CreateTaskView createTaskView = CreateTaskUseCaseFactory.createTaskView(viewManagerModel,createTaskViewModel,firebaseAccessObject,gmailDataAccessObject, mainPageViewModel);
        views.add(createTaskView, createTaskView.viewName);

        ModifyTaskView modifyTaskView = ModifyTaskUseCaseFactory.modifyTaskView(viewManagerModel, modifyTaskViewModel,firebaseAccessObject, gmailDataAccessObject);
        views.add(modifyTaskView,modifyTaskView.viewName);

        CompleteTaskView completeTaskView = CompleteTaskUseCaseFactory.completeTaskView(viewManagerModel, completeTaskViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(completeTaskView, completeTaskView.viewName);

        AddEmailViewModel addEmailViewModel = new AddEmailViewModel();
        AddEmailView addEmailView = AddEmailUseCaseFactory.addEmailView(viewManagerModel,  addEmailViewModel, firebaseAccessObject, mainPageViewModel);
        views.add(addEmailView, addEmailView.viewName);

        RemoveEmailViewModel removeEmailViewModel = new RemoveEmailViewModel();
        RemoveEmailView removeEmailView = RemoveEmailUseCaseFactory.removeEmailView(viewManagerModel,  removeEmailViewModel, firebaseAccessObject, mainPageViewModel);
        views.add(removeEmailView, removeEmailView.viewName);

        SetLeaderViewModel setLeaderViewModel = new SetLeaderViewModel();
        SetLeaderView setLeaderView = SetLeaderUseCaseFactory.setLeaderView(viewManagerModel,  setLeaderViewModel, firebaseAccessObject, mainPageViewModel);
        views.add(setLeaderView, setLeaderView.viewName);

        CreateMeetingView createMeetingView =  CreateMeetingUseCaseFactory.createMeetingView(viewManagerModel, createMeetingViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(createMeetingView, createMeetingView.viewName);

        ModifyMeetingView modifyMeetingView = ModifyMeetingUseCaseFactory.modifyMeetingView(viewManagerModel, modifyMeetingViewModel, firebaseAccessObject, gmailDataAccessObject, mainPageViewModel);
        views.add(modifyMeetingView, modifyMeetingView.viewName);

        CreateAnnouncementView createAnnouncementView = CreateAnnouncementUseCaseFactory.createAnnouncementView(viewManagerModel, createAnnouncementViewModel, firebaseAccessObject, mainPageViewModel);
        views.add(createAnnouncementView, createAnnouncementView.viewName);

        DeleteAnnouncementView deleteAnnouncementView = DeleteAnnouncementUseCaseFactory.createDeleteAnnouncementView(viewManagerModel,deleteAnnouncementViewModel, firebaseAccessObject, createAnnouncementViewModel, mainPageViewModel);
        views.add(deleteAnnouncementView, deleteAnnouncementView.viewName);

        MainPageView mainPageView = new MainPageView(viewManagerModel, mainPageViewModel, loginViewModel, createTaskViewModel, completeTaskViewModel, modifyTaskViewModel, addEmailViewModel, removeEmailViewModel, setLeaderViewModel, createMeetingViewModel, modifyMeetingViewModel, createAnnouncementViewModel, deleteAnnouncementViewModel);
        views.add(mainPageView, mainPageView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
