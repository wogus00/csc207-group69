package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateTaskView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Task";
    private ViewManagerModel viewManagerModel;
    private final CreateTaskViewModel createTaskViewModel;
    private final JTextField taskNameInputField = new JTextField(15);
    private final JTextField supervisorInputField = new JTextField(15);
    private final JTextField memberEmailsInputField = new JTextField(15);
    private final JTextField deadlineInputField = new JTextField(15);
    private final JTextField commentsInputField = new JTextField(15);
    private final CreateTaskController createTaskController;
    private final JButton create;
    private final JButton cancel;
    public CreateTaskView(ViewManagerModel viewManagerModel, CreateTaskController createTaskController, CreateTaskViewModel createTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createTaskController = createTaskController;
        this.createTaskViewModel = createTaskViewModel;

        JLabel title = new JLabel(CreateTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.TASK_NAME_LABEL), taskNameInputField);
        LabelTextPanel supervisorEmailInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.SUPERVISOR_EMAIL_LABEL), supervisorInputField);
        LabelTextPanel membersEmailInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.MEMBER_EMAIL_LABEL), memberEmailsInputField);
        LabelTextPanel deadlineInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.DEADLINE_LABEL), deadlineInputField);
        LabelTextPanel commentsInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.COMMENT_LABEL), commentsInputField);
        JPanel buttons = new JPanel();
        create = new JButton(CreateTaskViewModel.CREATE_BUTTON_LABEL);
        cancel = new JButton(CreateTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(create);
        buttons.add(cancel);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancel)) {
                    viewManagerModel.setActiveView("Main Page");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateTaskState currentState = createTaskViewModel.getState();
                            String projectName = currentState.getProjectName();
                            String taskName = currentState.getTaskName();
                            String supervisor = currentState.getSupervisor();
                            String workingMembers = currentState.getWorkingMembersList();
                            String deadline = currentState.getDeadline();
                            String comments = currentState.getComments();
                            createTaskController.execute(projectName, taskName, supervisor, workingMembers, deadline, comments);
                            currentState = createTaskViewModel.getState();
                            if (currentState.getTaskNameError() == null && currentState.getWorkingMembersError() == null) {
                                JOptionPane.showMessageDialog(CreateTaskView.this, "created task successfully");
                                viewManagerModel.setActiveView("Main Page");
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                });

        taskNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = taskNameInputField.getText() + e.getKeyChar();
                        currentState.setTaskName(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        supervisorInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = supervisorInputField.getText() + e.getKeyChar();
                        currentState.setSupervisorName(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        memberEmailsInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = memberEmailsInputField.getText() + e.getKeyChar();
                        currentState.setWorkingMembersList(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        deadlineInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = deadlineInputField.getText() + e.getKeyChar();
                        currentState.setDeadline(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        commentsInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = commentsInputField.getText() + e.getKeyChar();
                        currentState.setComments(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(taskNameInfo);
        this.add(supervisorEmailInfo);
        this.add(membersEmailInfo);
        this.add(deadlineInfo);
        this.add(commentsInfo);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}