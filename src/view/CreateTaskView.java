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
import java.util.Objects;

/**
 * The CreateTaskView class represents the GUI view for creating tasks.
 * This class extends JPanel and includes user interface elements for creating tasks.
 */
public class CreateTaskView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Task";
    private ViewManagerModel viewManagerModel;
    private final CreateTaskViewModel createTaskViewModel;
    final JTextField taskNameInputField = new JTextField(15);
    final JTextField supervisorInputField = new JTextField(15);
    final JTextField memberEmailsInputField = new JTextField(15);
    final JTextField deadlineInputField = new JTextField(15);
    final JTextField commentsInputField = new JTextField(15);
    private final CreateTaskController createTaskController;
    final JButton create;
    final JButton cancel;

    /**
     * Constructor method to create CreateTaskView object.
     * @param viewManagerModel ViewManagerModel that updates the view
     * @param createTaskController Controller class associated with creating task
     * @param createTaskViewModel view model class associated with creating task
     */
    public CreateTaskView(ViewManagerModel viewManagerModel, CreateTaskController createTaskController, CreateTaskViewModel createTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createTaskController = createTaskController;
        this.createTaskViewModel = createTaskViewModel;
        this.createTaskViewModel.addPropertyChangeListener(this);


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
                    taskNameInputField.setText("");
                    supervisorInputField.setText("");
                    memberEmailsInputField.setText("");
                    deadlineInputField.setText("");
                    commentsInputField.setText("");
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
                            currentState.setCreateTaskError(null);
                            createTaskViewModel.setState(currentState);
                            createTaskController.execute(projectName, taskName, supervisor, workingMembers, deadline, comments);
                            if (createTaskViewModel.getState().getCreateTaskError() == null) {
                                JOptionPane.showMessageDialog(CreateTaskView.this, "created task successfully");
                                taskNameInputField.setText("");
                                supervisorInputField.setText("");
                                memberEmailsInputField.setText("");
                                deadlineInputField.setText("");
                                commentsInputField.setText("");
                            } else {
                                JOptionPane.showMessageDialog(CreateTaskView.this, createTaskViewModel.getState().getCreateTaskError());
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


    /**
     * Invoked when an action occurs on the view components.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {


    }
}