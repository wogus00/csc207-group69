package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import interface_adapter.modify_task.ModifyTaskState;
import interface_adapter.modify_task.ModifyTaskViewModel;
import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
/**
 * View class for main page view in the application's GUI.
 * It presents information about the project and handles user actions with clicking specific buttons
 * on the main page
 * It extends JPanel and implements ActionListener and PropertyChangeListener to interact with
 * the user actions and model changes.
 */
public class MainPageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Main Page";

    final ViewManagerModel viewManagerModel;

    final MainPageViewModel mainPageViewModel;

    final LoginViewModel loginViewModel;
    CreateTaskViewModel createTaskViewModel;
    CompleteTaskViewModel completeTaskViewModel;
    ModifyTaskViewModel modifyTaskViewModel;
    RemoveEmailViewModel removeEmailViewModel;
    SetLeaderViewModel setLeaderViewModel;
    CreateMeetingViewModel createMeetingViewModel;
    AddEmailViewModel addEmailViewModel;
    ModifyMeetingViewModel modifyMeetingViewModel;
    CreateAnnouncementViewModel createAnnouncementViewModel;

    JLabel titleLabel;
    JLabel leaderEmailInfo = new JLabel();
    JLabel userEmailInfo = new JLabel();
    JLabel userLabel;
    JLabel memberLabel;
    JLabel taskLabel;
    JLabel meetingLabel;
    JButton showAllButton;
    JLabel showAllInfo = new JLabel();
    JLabel announcementLabel;
    JButton recentAnnouncement;
    JLabel recentAnnouncementInfo = new JLabel();
    private JPanel projectPanelExtension;

    /**
     * Constructs a new MainPageView class with specific models
     * Sets up the UI components for the main page view, including function buttons with their action
     * listeners.
     *
     * @param viewManagerModel The model responsible for managing different views in the application.
     * @param mainPageViewModel The view model for the main page view, manages the state and behavior of the main page view
     * @param loginViewModel The view model for the login view, manages the state and behavior of the login view
     */
    public MainPageView(ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel, LoginViewModel loginViewModel,
                        CreateTaskViewModel createTaskViewModel, CompleteTaskViewModel completeTaskViewModel,
                        ModifyTaskViewModel modifyTaskViewModel, AddEmailViewModel addEmailViewModel,
                        RemoveEmailViewModel removeEmailViewModel, SetLeaderViewModel setLeaderViewModel,
                        CreateMeetingViewModel createMeetingViewModel, ModifyMeetingViewModel modifyMeetingViewModel,
                        CreateAnnouncementViewModel createAnnouncementViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
        this.loginViewModel = loginViewModel;
        this.createTaskViewModel = createTaskViewModel;
        this.completeTaskViewModel = completeTaskViewModel;
        this.modifyTaskViewModel = modifyTaskViewModel;
        this.addEmailViewModel = addEmailViewModel;
        this.removeEmailViewModel = removeEmailViewModel;
        this.setLeaderViewModel = setLeaderViewModel;
        this.createMeetingViewModel = createMeetingViewModel;
        this.modifyMeetingViewModel = modifyMeetingViewModel;
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.mainPageViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());


        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        this.setPreferredSize(new Dimension(500, 310));


        // Title label for project name
        titleLabel = new JLabel("Project Name: ");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(titleLabel);


        // User label for user email
        userLabel = new JLabel("User Email: ");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        userLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(userLabel);


        // Member email list label
        memberLabel = new JLabel();
        memberLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        memberLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(memberLabel);

        // Task list label
        taskLabel = new JLabel();
        taskLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        taskLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(taskLabel);

        // Meeting list label
        meetingLabel = new JLabel();
        meetingLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        meetingLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(meetingLabel);

        showAllButton = new JButton("show all");

        showAllButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JOptionPane.showConfirmDialog(MainPageView.this, showAllInfo.getText(), "More information", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
        );

        labelPanel.add(showAllButton);

        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));



        JPanel announcementPanel = new JPanel();
        announcementPanel.setLayout(new BoxLayout(announcementPanel, BoxLayout.Y_AXIS));

        announcementLabel = new JLabel();
        announcementLabel.setFont(new Font("Serif", Font.PLAIN, 15));

        recentAnnouncement = new JButton("Recent Announcements");

        recentAnnouncement.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JOptionPane.showConfirmDialog(MainPageView.this, recentAnnouncementInfo.getText(), "Recent Announcements", JOptionPane.INFORMATION_MESSAGE);

                    }
                }

        );


        announcementPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 5));

        announcementPanel.add(announcementLabel);
        announcementPanel.add(recentAnnouncement);





        // Main Button Panel that lies on the bottom of the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Sub-panels for each type of management
        JPanel typeButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // List for different extension panels
        ArrayList<JPanel> buttonList = new ArrayList<>();

        JButton taskButton = new JButton("Task");
        JButton meetingButton = new JButton("Meeting");
        JButton announcementButton = new JButton("Announcement");
        JButton projectButton = new JButton("Project");
        JButton logoutButton = new JButton("Log out");


        typeButtonPanel.add(taskButton);
        typeButtonPanel.add(meetingButton);
        typeButtonPanel.add(announcementButton);
        typeButtonPanel.add(projectButton);
        typeButtonPanel.add(logoutButton);


        // Additional button panels for extension, initially not visible
        JPanel taskPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskPanelExtension.setName("Task Extension");
        taskPanelExtension.setVisible(false);

        JButton buttonT1 = new JButton("create task");
        JButton buttonT2 = new JButton("modify task");
        JButton buttonT3 = new JButton("complete task");
        taskPanelExtension.add(buttonT1);
        taskPanelExtension.add(buttonT2);
        taskPanelExtension.add(buttonT3);

        // Add the additional buttons panel to the main button panel below the sub-panel and the button list
        buttonPanel.add(taskPanelExtension);
        buttonList.add(taskPanelExtension);

        JPanel meetingPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        meetingPanelExtension.setName("Meeting Extension");
        meetingPanelExtension.setVisible(false);

        JButton buttonM1 = new JButton("create meeting");
        JButton buttonM2 = new JButton("modify meeting");
        meetingPanelExtension.add(buttonM1);
        meetingPanelExtension.add(buttonM2);
        buttonPanel.add(meetingPanelExtension);
        buttonList.add(meetingPanelExtension);

        JPanel announcementPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        announcementPanelExtension.setName("Announcement Extension");
        announcementPanelExtension.setVisible(false);

        JButton buttonA1 = new JButton("create announcement");
        announcementPanelExtension.add(buttonA1);
        buttonPanel.add(announcementPanelExtension);
        buttonList.add(announcementPanelExtension);

        projectPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectPanelExtension.setName("Project Extension");
        projectPanelExtension.setVisible(false);

        JButton buttonP1 = new JButton("add member");
        JButton buttonP2 = new JButton("remove member");
        JButton buttonP3 = new JButton("change leader");
        projectPanelExtension.add(buttonP1);
        projectPanelExtension.add(buttonP2);
        projectPanelExtension.add(buttonP3);

        buttonPanel.add(projectPanelExtension);
        buttonList.add(projectPanelExtension);

        buttonPanel.add(typeButtonPanel);

        // Add the main button panel to the main panel at the bottom


        // ActionListeners
        taskButton.addActionListener(e -> {
            // Hide every panel except the one we want to show
            for (JPanel extension : buttonList) {
                if (!Objects.equals(extension.getName(), "Task Extension")) {
                    extension.setVisible(false);
                }
            }
            // Toggle the visibility of the task panel extension
            boolean isVisible = taskPanelExtension.isVisible();
            taskPanelExtension.setVisible(!isVisible);

            // Revalidate and repaint the button panel to reflect changes
            buttonPanel.revalidate();
            buttonPanel.repaint();

            // Adjust the frame size if necessary
            this.repaint();
            this.revalidate();  // This will resize the frame to fit the content
        });

        meetingButton.addActionListener(e -> {
            for (JPanel extension : buttonList) {
                if (!Objects.equals(extension.getName(), "Meeting Extension")) {
                    extension.setVisible(false);
                }
            }
            boolean isVisible = meetingPanelExtension.isVisible();
            meetingPanelExtension.setVisible(!isVisible);

            buttonPanel.revalidate();
            buttonPanel.repaint();

            this.revalidate();
            this.repaint();
        });

        announcementButton.addActionListener(e -> {
            for (JPanel extension : buttonList) {
                if (!Objects.equals(extension.getName(), "Announcement Extension")) {
                    extension.setVisible(false);
                }
            }
            boolean isVisible = announcementPanelExtension.isVisible();
            announcementPanelExtension.setVisible(!isVisible);

            buttonPanel.revalidate();
            buttonPanel.repaint();

            this.revalidate();
            this.repaint();
        });

        projectButton.addActionListener(e -> {
            for (JPanel extension : buttonList) {
                if (!Objects.equals(extension.getName(), "Project Extension")) {
                    extension.setVisible(false);
                }
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();

            if (userEmailInfo.getText().equals(leaderEmailInfo.getText())) {
                boolean isVisible = projectPanelExtension.isVisible();
                projectPanelExtension.setVisible(!isVisible);
            }
            if (!(userEmailInfo.getText().equals(leaderEmailInfo.getText()))) {
                JOptionPane.showMessageDialog(this, "You are not the leader", "Not authorized", JOptionPane.INFORMATION_MESSAGE);
            }

            buttonPanel.revalidate();
            buttonPanel.repaint();

            this.revalidate();
            this.repaint();
        });

        logoutButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LoginState loginState = new LoginState();
                        loginState.setLogout(true);
                        loginViewModel.setState(loginState);
                        loginViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("log in");
                        viewManagerModel.firePropertyChanged();
                        MainPageState state = new MainPageState();
                        mainPageViewModel.setState(state);
                        mainPageViewModel.firePropertyChanged();
                    }
                }
        );

        buttonT1.addActionListener(     // create task
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        CreateTaskState createTaskState = createTaskViewModel.getState();
                        createTaskState.setProjectName(mainPageState.getProjectName());
                        createTaskViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Create Task");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonT2.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        ModifyTaskState modifyTaskState = modifyTaskViewModel.getState();
                        modifyTaskState.setProjectName(mainPageState.getProjectName());
                        modifyTaskViewModel.setState(modifyTaskState);
                        modifyTaskViewModel .firePropertyChanged();
                        viewManagerModel.setActiveView("Modify Task");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonT3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        CompleteTaskState completeTaskState = completeTaskViewModel.getState();
                        completeTaskState.setProjectName(mainPageState.getProjectName());
                        completeTaskState.setUserEmail(mainPageState.getUserEmail());
                        completeTaskViewModel.setState(completeTaskState);
                        completeTaskViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Complete Task");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonM1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        CreateMeetingState createMeetingState = createMeetingViewModel.getState();
                        createMeetingState.setProjectName(mainPageState.getProjectName());
                        createMeetingViewModel.setState(createMeetingState);
                        createMeetingViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("create meeting");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonM2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        ModifyMeetingState modifyMeetingState = modifyMeetingViewModel.getState();
                        modifyMeetingState.setProjectName(mainPageState.getProjectName());
                        modifyMeetingViewModel.setState(modifyMeetingState);
                        modifyMeetingViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Modify Meeting");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonA1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
                        createAnnouncementState.setAuthor(mainPageState.getUserEmail());
                        createAnnouncementState.setProject(mainPageState.getProjectName());
                        createAnnouncementViewModel.setState(createAnnouncementState);
                        createAnnouncementViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Create announcement");
                        viewManagerModel.firePropertyChanged();

                    }
                }
        );

        buttonP1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        AddEmailState addEmailState = addEmailViewModel.getState();
                        addEmailState.setProjectName(mainPageState.getProjectName());
                        addEmailViewModel.setState(addEmailState);
                        addEmailViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Add Email");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonP2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        RemoveEmailState removeEmailState = removeEmailViewModel.getState();
                        removeEmailState.setProjectName(mainPageState.getProjectName());
                        removeEmailViewModel.setState(removeEmailState);
                        removeEmailViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Remove Email");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        buttonP3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        MainPageState mainPageState = mainPageViewModel.getState();
                        SetLeaderState setLeaderState = setLeaderViewModel.getState();
                        setLeaderState.setProjectName(mainPageState.getProjectName());
                        setLeaderViewModel.setState(setLeaderState);
                        setLeaderViewModel.firePropertyChanged();
                        viewManagerModel.setActiveView("Set Leader");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(announcementPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame

        // Center the window and make it visible
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    /**
     * React to property changes in the view model.
     *
     * @param evt The PropertyChangeEvent object.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MainPageState state = (MainPageState) evt.getNewValue();
        titleLabel.setText("Project Name: " + state.getProjectName());
        userLabel.setText("User Email: " + state.getUserEmail());
        userEmailInfo.setText(state.getUserEmail());
        leaderEmailInfo.setText(state.getLeaderEmail());
        memberLabel.setText(state.getLabel("member"));
        taskLabel.setText(state.getLabel("task") );
        meetingLabel.setText(state.getLabel("meeting"));
        showAllInfo.setText(state.getShowAllMessage());
        announcementLabel.setText(state.getAnnouncementLabel());
        recentAnnouncementInfo.setText(state.getRecentAnnouncements());
        if (!userEmailInfo.getText().equals(leaderEmailInfo.getText())) {
            projectPanelExtension.setVisible(false);
        }



    }
}