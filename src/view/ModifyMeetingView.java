package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.modify_meeting.ModifyMeetingController;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ModifyMeetingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Modify Meeting";
    private ViewManagerModel viewManagerModel;
    private final CreateMeetingViewModel createMeetingViewModel;
    private final JTextField meetingNameInputField = new JTextField(15);
    private final JTextField participantEmailInputField = new JTextField(15);
    private final JTextField meetingDateInputField = new JTextField(15);
    private final JTextField startTimeInputField = new JTextField(15);
    private final JTextField endTimeInputField = new JTextField(15);
    private final JTextField projectNameInputField = new JTextField(15);
    private final CreateMeetingController createMeetingController;
    private final JButton modify;
    private final JButton cancel;
    public ModifyMeetingView(ViewManagerModel viewManagerModel, ModifyMeetingController modifyMeetingController, ModifyMeetingViewModel modifyMeetingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyMeetingController = modifyMeetingController;
        this.modifyMeetingViewModel = modifyMeetingViewModel;

        JLabel title = new JLabel(CreateMeetingViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel meetingNameInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.MEETING_NAME_LABEL), meetingNameInputField);
        LabelTextPanel participantEmailInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.PARTICIPANT_EMAIL_LABEL), participantEmailInputField);
        LabelTextPanel meetingDateInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.MEETING_DATE_LABEL), meetingDateInputField);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.START_TIME_LABEL), startTimeInputField);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.END_TIME_LABEL), endTimeInputField);
        LabelTextPanel projectNameInfo = new LabelTextPanel(
                new JLabel(ModifyMeetingViewModel.PROJECT_NAME_LABEL), projectNameInputField);

        JPanel buttons = new JPanel();
        modify = new JButton(CreateMeetingViewModel.MODIFY_BUTTON_LABEL);
        cancel = new JButton(CreateMeetingViewModel.CANCEL_BUTTON_LABEL);
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

        modify.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                            String meetingName = currentState.getMeetingName();
                            ArrayList<String> participantEmail = currentState.getParticipantEmail();
                            String meetingDate = currentState.getMeetingDate();
                            String startTime = currentState.getStartTime();
                            String endTime = currentState.getEndTime();
                            String projectName = currentState.getProjectName();
                            modifyMeetingController.execute(
                                    currentState.getMeetingName(),
                                    currentState.getParticipantEmail(),
                                    currentState.getMeetingDate(),
                                    currentState.getStartTime(),
                                    currentState.getEndTime(),
                                    currentState.getProjectName()
                            );
                            currentState = modifyMeetingViewModel.getState();
                            if (currentState.getMeetingNameError() == null) {
                                JOptionPane.showMessageDialog(ModifyMeetingView.this,
                                        "created meeting successfully");
                                viewManagerModel.setActiveView("Main Page");
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                });


        meetingNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                        String text = meetingNameInputField.getText() + e.getKeyChar();
                        currentState.setMeetingName(text);
                        modifyMeetingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        participantEmailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                        String text = participantEmailInputField.getText() + e.getKeyChar();
                        currentState.setParticipantEmail(text);
                        modifyMeetingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        meetingDateInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                        String text = meetingDateInputField.getText() + e.getKeyChar();
                        currentState.setMeetingDate(text);
                        modifyMeetingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        startTimeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                        String text = startTimeInputField.getText() + e.getKeyChar();
                        currentState.setStartTime(text);
                        modifyMeetingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        endTimeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ModifyMeetingState currentState = modifyMeetingViewModel.getState();
                        String text = endTimeInputField.getText() + e.getKeyChar();
                        currentState.setEndTime(text);
                        modifyMeetingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(meetingNameInfo);
        this.add(participantEmailInfo);
        this.add(meetingDateInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(projectNameInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ModifyMeetingState state = (ModifyMeetingState) evt.getNewValue();
        if (state.getMeetingNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getMeetingNameError());
        }
    }

}