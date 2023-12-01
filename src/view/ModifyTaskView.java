package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.modify_task.ModifyTaskController;
import interface_adapter.modify_task.ModifyTaskState;
import interface_adapter.modify_task.ModifyTaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ModifyTaskView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Modify Task";
    private ViewManagerModel viewManagerModel;
    private final ModifyTaskViewModel modifyTaskViewModel;
    final JTextField taskNameInputField = new JTextField(15);
    final JTextField supervisorInputField = new JTextField(15);
    final JTextField memberEmailsInputField = new JTextField(15);
    final JTextField deadlineInputField = new JTextField(15);
    final JTextField commentsInputField = new JTextField(15);
    private final ModifyTaskController modifyTaskController;
    final JButton modify;
    final JButton cancel;
    public ModifyTaskView(ViewManagerModel viewManagerModel, ModifyTaskController modifyTaskController, ModifyTaskViewModel modifyTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyTaskController = modifyTaskController;
        this.modifyTaskViewModel = modifyTaskViewModel;

        JLabel title = new JLabel(ModifyTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel(ModifyTaskViewModel.TASK_NAME_LABEL), taskNameInputField);
        LabelTextPanel supervisorEmailInfo = new LabelTextPanel(
                new JLabel(ModifyTaskViewModel.SUPERVISOR_EMAIL_LABEL), supervisorInputField);
        LabelTextPanel membersEmailInfo = new LabelTextPanel(
                new JLabel(ModifyTaskViewModel.MEMBER_EMAIL_LABEL), memberEmailsInputField);
        LabelTextPanel deadlineInfo = new LabelTextPanel(
                new JLabel(ModifyTaskViewModel.DEADLINE_LABEL), deadlineInputField);
        LabelTextPanel commentsInfo = new LabelTextPanel(
                new JLabel(ModifyTaskViewModel.COMMENT_LABEL), commentsInputField);
        JPanel buttons = new JPanel();
        modify = new JButton(ModifyTaskViewModel.MODIFY_BUTTON_LABEL);
        cancel = new JButton(ModifyTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(modify);
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

        modify.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(modify)) {
                            ModifyTaskState currentState = modifyTaskViewModel.getState();
                            String projectName = currentState.getProjectName();
                            String taskName = currentState.getTaskName();
                            String supervisor = currentState.getSupervisor();
                            String workingMembers = currentState.getWorkingMembersList();
                            String deadline = currentState.getDeadline();
                            String comments = currentState.getComments();
                            modifyTaskController.execute(projectName, taskName, supervisor, workingMembers, deadline, comments);
                            currentState = modifyTaskViewModel.getState();
                            if (currentState.getTaskNameError() == null && currentState.getWorkingMembersError() == null) {
                                JOptionPane.showMessageDialog(ModifyTaskView.this, "modified task successfully");
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
                        ModifyTaskState currentState = modifyTaskViewModel.getState();
                        String text = taskNameInputField.getText() + e.getKeyChar();
                        currentState.setTaskName(text);
                        modifyTaskViewModel.setState(currentState);
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
                        ModifyTaskState currentState = modifyTaskViewModel.getState();
                        String text = supervisorInputField.getText() + e.getKeyChar();
                        currentState.setSupervisorName(text);
                        modifyTaskViewModel.setState(currentState);
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
                        ModifyTaskState currentState = modifyTaskViewModel.getState();
                        String text = memberEmailsInputField.getText() + e.getKeyChar();
                        currentState.setWorkingMembersList(text);
                        modifyTaskViewModel.setState(currentState);
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
                        ModifyTaskState currentState = modifyTaskViewModel.getState();
                        String text = deadlineInputField.getText() + e.getKeyChar();
                        currentState.setDeadline(text);
                        modifyTaskViewModel.setState(currentState);
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
                        ModifyTaskState currentState = modifyTaskViewModel.getState();
                        String text = commentsInputField.getText() + e.getKeyChar();
                        currentState.setComments(text);
                        modifyTaskViewModel.setState(currentState);
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