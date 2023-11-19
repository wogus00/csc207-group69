package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
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

public class CompleteTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Complete Task";
    private ViewManagerModel viewManagerModel;
    private final CompleteTaskViewModel completeTaskViewModel;
    private final JTextField taskNameInputField = new JTextField(15);

    private final CompleteTaskController completeTaskController;

    private final JButton complete;
    private final JButton cancel;


    public CompleteTaskView(ViewManagerModel viewManagerModel, CompleteTaskController completeTaskController, CompleteTaskViewModel completeTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.completeTaskController = completeTaskController;
        this.completeTaskViewModel = completeTaskViewModel;

        JLabel title = new JLabel(CompleteTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel(CompleteTaskViewModel.TASK_NAME_LABEL), taskNameInputField);

        JPanel buttons = new JPanel();
        complete = new JButton(CompleteTaskViewModel.COMPLETE_BUTTON_LABEL);
        cancel = new JButton(CompleteTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(complete);
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

        taskNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CompleteTaskState currentState = completeTaskViewModel.getState();
                        String taskNameInfo = taskNameInputField.getText() + e.getKeyChar();
                        currentState.setTaskName(taskNameInfo);
                        completeTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        complete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(complete)) {
                            CompleteTaskState currentState = completeTaskViewModel.getState();
                            String projectName = currentState.getProjectName();
                            String taskName = currentState.getTaskName();
                            completeTaskController.execute(projectName, taskName);
                            currentState = completeTaskViewModel.getState();
                            if (currentState.getTaskNameError() == null) {
                                JOptionPane.showMessageDialog(CompleteTaskView.this, "completed task successfully");
                                viewManagerModel.setActiveView("Main Page");
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(taskNameInfo);
        this.add(buttons);

    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
