package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPageView extends JFrame implements ActionListener, PropertyChangeListener {

    final ViewManagerModel viewManagerModel;

    final MainPageViewModel mainPageViewModel;

    final CreateTaskViewModel createTaskViewModel;

    final ModifyTaskViewModel modifyTaskViewModel;

    final CompleteTaskViewModel completeTaskViewModel;

    final CreateMeetingViewModel createMeetingViewModel;

    final CreateAnnouncementViewModel createAnnouncementViewModel;

    final AddMemberViewModel addMemberViewModel;

    final RemoveMemberViewModel removeMemberViewModel;

    final ChangeSupervisorViewModel changeSupervisorViewModel;

    final

    public MainPageView(ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel, CreateTaskViewModel createTaskViewModel, ModifyTaskViewModel modifyTaskViewModel, CompleteTaskViewModel completeTaskViewModel, CreateMeetingViewModel createMeetingViewModel, CreateAnnouncementViewModel createAnnouncementViewModel, AddMemberViewModel addMemberViewModel, RemoveMemberViewModel removeMemberViewModel, ChangeSupervisorViewModel changeSupervisorViewModel) {
        super("Main Page");
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
        this.createTaskViewModel = createTaskViewModel;
        this.modifyTaskViewModel = modifyTaskViewModel;
        this.completeTaskViewModel = completeTaskViewModel;
        this.createMeetingViewModel = createMeetingViewModel;
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.addMemberViewModel = addMemberViewModel;
        this.removeMemberViewModel = removeMemberViewModel;
        this.changeSupervisorViewModel = changeSupervisorViewModel;

        MainPageState mainPageState = mainPageViewModel.getState();

        // Setting up the main window (JFrame)
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout to contain everything
        JPanel panel = new JPanel(new BorderLayout());

        // Label Panel at the top
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        String projectName = mainPageState.getProjectName();

        // Title label for project name
        JLabel titleLabel = new JLabel("Project Name: " + projectName);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPanel.add(titleLabel);

        String userEmail = mainPageState.getUserEmail();

        // User label for user email
        JLabel userLabel = new JLabel("User Email: " + userEmail);
        userLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPanel.add(userLabel);

        String leaderEmail = mainPageState.getLeaderEmail();

        // List of email addresses
        List<String> memberOnlyList = new ArrayList<>(mainPageState.getMemberEmail());

        List<String> memberList = new ArrayList<>();
        memberList.add(leaderEmail);
        memberList.addAll(memberOnlyList);


        // Member email list label
        JLabel memberListLabel = new JLabel();
        StringBuilder memberListBuilder = new StringBuilder("<html>"); // Use HTML to allow for text wrapping if needed

        memberListBuilder.append("Member List: <font color='blue'>");

        final int maxChars = 80; // Set a maximum character limit for the JLabel
        int charsCount = 0; // initialize count

        for (String item : memberList) {
            if (charsCount + item.length() > maxChars) {
                memberListBuilder.delete(memberListBuilder.length() - 9, memberListBuilder.length());
                memberListBuilder.append("...");
                break;
            } else {
                memberListBuilder.append(item).append(", ");
                memberListBuilder.append("</font>");
                charsCount += item.length() + 2; // Add 2 for the comma and space
            }
        }

        // Remove the following ", </font>" if the text length does not exceed the limit
        if (!memberListBuilder.isEmpty()) {
            char lastChar = memberListBuilder.charAt(memberListBuilder.length() - 1);
            if ('.' != lastChar) {
                memberListBuilder.delete(memberListBuilder.length() - 9, memberListBuilder.length());
            }
        }

        memberListBuilder.append("</html>");
        memberListLabel.setText(memberListBuilder.toString());
        memberListLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        labelPanel.add(memberListLabel);

        // Task list label
        JLabel taskListLabel = new JLabel();
        StringBuilder taskListBuilder = new StringBuilder("<html>"); // Use HTML to allow for text wrapping if needed

        taskListBuilder.append("Task List: ");

        // Tasks to show
        List<String> taskList = mainPageState.getTaskList();

        charsCount = 0; // reset count

        for (String item : taskList) {
            if (charsCount + item.length() > maxChars) {
                taskListBuilder.delete(taskListBuilder.length() - 9, taskListBuilder.length());
                taskListBuilder.append("...");
                break;
            } else {
                taskListBuilder.append(item).append(", ");
                taskListBuilder.append("</font>");
                charsCount += item.length() + 2;
            }
        }


        if (!taskListBuilder.isEmpty()) {
            char lastChar = taskListBuilder.charAt(taskListBuilder.length() - 1);
            if ('.' != lastChar) {
                taskListBuilder.delete(taskListBuilder.length() - 9, taskListBuilder.length());
            }
        }

        taskListBuilder.append("</html>");
        taskListLabel.setText(taskListBuilder.toString());
        taskListLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        labelPanel.add(taskListLabel);

        // Meeting list label
        JLabel meetingListLabel = new JLabel();
        StringBuilder meetingListBuilder = new StringBuilder("<html>"); // Use HTML to allow for text wrapping if needed

        meetingListBuilder.append("Meeting List");

        // Meetings to show
        List<String> meetingList = mainPageState.getMeetingList();

        charsCount = 0;

        for (String item : meetingList) {
            if (charsCount + item.length() > maxChars) {
                meetingListBuilder.delete(meetingListBuilder.length() - 9, meetingListBuilder.length());
                meetingListBuilder.append("...");
                break;
            } else {
                meetingListBuilder.append(item).append(", ");
                meetingListBuilder.append("</font>");
                charsCount += item.length() + 2;
            }
        }

        if (!meetingListBuilder.isEmpty()) {
            char lastChar = meetingListBuilder.charAt(meetingListBuilder.length() - 1);
            if ('.' != lastChar) {
                meetingListBuilder.delete(meetingListBuilder.length() - 9, meetingListBuilder.length());
            }
        }

        meetingListBuilder.append("</html>");
        meetingListLabel.setText(meetingListBuilder.toString());
        meetingListLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        labelPanel.add(meetingListLabel);

        JButton showAllButton = new JButton("show all");
        labelPanel.add(showAllButton);

        showAllButton.addActionListener(e -> {
            StringBuilder messageBuilder = new StringBuilder("<html>");
            List<List<String>> labelList = Arrays.asList(memberList,taskList,meetingList);
            for (List<String> list: labelList){
                if (list == memberList){
                    messageBuilder.append("Members:");
                }
                if (list == taskList){
                    messageBuilder.append("Tasks:");
                }
                if (list == meetingList){
                    messageBuilder.append("Meetings:");
                }
                for (String item : list) {
                    messageBuilder.append("<li>").append(item).append("</li>");
                }
                messageBuilder.append("<br/>");
            }
            messageBuilder.append("</html>");
            JOptionPane.showConfirmDialog(this, messageBuilder.toString(), "More information",JOptionPane.INFORMATION_MESSAGE);
        });

        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Add the label panel to the main panel at the top
        panel.add(labelPanel, BorderLayout.NORTH);


        // This creates a new panel for announcement and its buttons
        JPanel labelPanelA = new JPanel();
        labelPanelA.setLayout(new BoxLayout(labelPanelA, BoxLayout.Y_AXIS));

        // Creating the announcement label with HTML to allow multi-line text

        List<String> announcements = mainPageState.getAnnouncements();
        String recent1 = announcements.get(0);
        String recent2 = announcements.get(1);
        String recent3 = announcements.get(2);

        String text = recent1;
        final int maxLength = 146; // maximum length for two line, different from one for label due to font difference
        if(text.length() > maxLength) {
            text = text.substring(0, maxLength) + "...";
        }
        // Replace the first space after the 50th character with <br/> to create a new line
        int lineBreakIndex = text.indexOf(" ", maxLength / 2);
        if (lineBreakIndex != -1 && lineBreakIndex < maxLength) {
            text = text.substring(0, lineBreakIndex) + "<br/>" + text.substring(lineBreakIndex + 1);
        }
        String announcementText = "<html>Announcement:<br/>" + text + "</html>";
        JLabel announcementLabel = new JLabel(announcementText);

        announcementLabel.setFont(new Font("Serif", Font.PLAIN, 15));

        JButton recentAnnouncement = new JButton("Recent Announcements");

        recentAnnouncement.addActionListener(e -> {
            String message = String.format("<html>%s<br/>%s<br/>%s</html>", "- " + recent1, "- " + recent2, "- " + recent3);
            JOptionPane.showConfirmDialog(this, message, "Recent Announcements", JOptionPane.INFORMATION_MESSAGE);
        });

        labelPanelA.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 5));
        labelPanelA.add(announcementLabel);
        labelPanelA.add(recentAnnouncement);
        panel.add(labelPanelA);

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


        typeButtonPanel.add(taskButton);
        typeButtonPanel.add(meetingButton);
        typeButtonPanel.add(announcementButton);
        typeButtonPanel.add(projectButton);

        buttonPanel.add(typeButtonPanel);

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
        meetingPanelExtension.add(buttonM1);

        buttonPanel.add(meetingPanelExtension);
        buttonList.add(meetingPanelExtension);

        JPanel announcementPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        announcementPanelExtension.setName("Announcement Extension");
        announcementPanelExtension.setVisible(false);

        JButton buttonA1 = new JButton("create announcement");
        announcementPanelExtension.add(buttonA1);

        buttonPanel.add(announcementPanelExtension);
        buttonList.add(announcementPanelExtension);

        JPanel projectPanelExtension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectPanelExtension.setName("Project Extension");
        projectPanelExtension.setVisible(false);

        JButton buttonP1 = new JButton("add member");
        JButton buttonP2 = new JButton("remove member");
        JButton buttonP3 = new JButton("change supervisor");
        projectPanelExtension.add(buttonP1);
        projectPanelExtension.add(buttonP2);
        projectPanelExtension.add(buttonP3);

        buttonPanel.add(projectPanelExtension);
        buttonList.add(projectPanelExtension);

        // Add the main button panel to the main panel at the bottom
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // ActionListeners
        taskButton.addActionListener(e -> {
            // Hide every panel except the one we want to show
            for (JPanel extension: buttonList){
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
            MainPageView.this.pack();  // This will resize the frame to fit the content
        });

        meetingButton.addActionListener(e -> {
            for (JPanel extension: buttonList){
                if (!Objects.equals(extension.getName(), "Meeting Extension")) {
                    extension.setVisible(false);
                }
            }
            boolean isVisible = meetingPanelExtension.isVisible();
            meetingPanelExtension.setVisible(!isVisible);

            buttonPanel.revalidate();
            buttonPanel.repaint();

            MainPageView.this.pack();
        });

        announcementButton.addActionListener(e -> {
            for (JPanel extension: buttonList){
                if (!Objects.equals(extension.getName(), "Announcement Extension")) {
                    extension.setVisible(false);
                }
            }
            boolean isVisible = announcementPanelExtension.isVisible();
            announcementPanelExtension.setVisible(!isVisible);

            buttonPanel.revalidate();
            buttonPanel.repaint();

            MainPageView.this.pack();
        });

        projectButton.addActionListener(e -> {
            for (JPanel extension: buttonList){
                if (!Objects.equals(extension.getName(), "Project Extension")) {
                    extension.setVisible(false);
                }
            }
            if (userEmail.equals(leaderEmail)) {
                boolean isVisible = projectPanelExtension.isVisible();
                projectPanelExtension.setVisible(!isVisible);
            } if (!(userEmail.equals(leaderEmail))) {
                JOptionPane.showMessageDialog(this, "You are not the leader","Not authorized", JOptionPane.INFORMATION_MESSAGE);
            }

            buttonPanel.revalidate();
            buttonPanel.repaint();

            MainPageView.this.pack();
        });

        buttonT1.addActionListener(     // create task
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonT1)) {
                            CreateTaskState state = createTaskViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            createTaskViewModel.setState(state);
                            createTaskViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("create task");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonT2.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonT2)) {
                            ModifyTaskState state = modifyTaskViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            modifyTaskViewModel.setState(state);
                            modifyTaskViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("modify project");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonT3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonT3)) {
                            CompleteTaskState state = completeTaskViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            completeTaskViewModel.setState(state);
                            completeTaskViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("complete project");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonM1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonM1)) {
                            CreateMeetingState state = createMeetingViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            createMeetingViewModel.setState(state);
                            createMeetingViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("create meeting");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonA1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonA1)) {
                            CreateAnnouncementState state = createAnnouncementViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            createAnnouncementViewModel.setState(state);
                            createAnnouncementViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("Make an announcement");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonP1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonP1)) {
                            AddMemberState state = addMemberViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            addMemberViewModel.setState(state);
                            addMemberViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("add member");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonP2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonP2)) {
                            RemoveMemberState state = removeMemberViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            removeMemberViewModel.setState(state);
                            removeMemberViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("remove member");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        buttonP3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buttonP3)) {
                            ChangeSupervisorState state = changeSupervisorViewModel.getState();
                            state.setUserEmail(userEmail);
                            state.setProjectName(projectName);
                            changeSupervisorViewModel.setState(state);
                            changeSupervisorViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView("change supervisor");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );





        // Add the main panel to the frame
        add(panel);

        // Center the window and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}