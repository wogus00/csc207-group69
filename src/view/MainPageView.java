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

public class MainPageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Main Page";

    final ViewManagerModel viewManagerModel;

    final MainPageViewModel mainPageViewModel;

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

    public MainPageView(ViewManagerModel viewManagerModel, MainPageViewModel mainPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
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


        typeButtonPanel.add(taskButton);
        typeButtonPanel.add(meetingButton);
        typeButtonPanel.add(announcementButton);
        typeButtonPanel.add(projectButton);



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

        buttonT1.addActionListener(     // create task
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        buttonT2.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonT3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonM1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonA1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonP1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonP2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        buttonP3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(announcementPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame

        // Center the window and make it visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

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



    }
}