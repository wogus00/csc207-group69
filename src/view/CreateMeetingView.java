package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;

import java.beans.PropertyChangeEvent;
import java.sql.Time;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;


public class CreateMeetingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "create meeting";
    private ViewManagerModel viewManagerModel;
    private final CreateMeetingViewModel createMeetingViewModel;
    private final JTextField meetingNameInputField = new JTextField(15);
    private final JTextField participantEmailInputField = new JTextField(15);
    private final JTextField meetingDateInputField = new JTextField(15);
    private final JTextField startTimeInputField = new JTextField(15);
    private final JTextField endTimeInputField = new JTextField(15);
    private final JTextField projectNameInputField = new JTextField(15);
    private final CreateMeetingController createMeetingController;
    private final JButton create;
    private final JButton cancel;


    public CreateMeetingView(ViewManagerModel viewManagerModel,
                             CreateMeetingController createMeetingController,
                             CreateMeetingViewModel createMeetingViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.createMeetingController = createMeetingController;
        this.createMeetingViewModel = createMeetingViewModel;
        createMeetingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateMeetingViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel meetingNameInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.MEETING_NAME_LABEL), meetingNameInputField);
        LabelTextPanel participantEmailInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.PARTICIPANT_EMAIL_LABEL), participantEmailInputField);
        LabelTextPanel meetingDateInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.MEETING_DATE_LABEL), meetingDateInputField);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.START_TIME_LABEL), startTimeInputField);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.END_TIME_LABEL), endTimeInputField);
        LabelTextPanel projectNameInfo = new LabelTextPanel(
                new JLabel(CreateMeetingViewModel.PROJECT_NAME_LABEL), projectNameInputField);

        JPanel buttons = new JPanel();
        create = new JButton(CreateMeetingViewModel.CREATE_BUTTON_LABEL);
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


        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateMeetingState currentState = createMeetingViewModel.getState();
                            String meetingName = currentState.getMeetingName();
                            ArrayList<String> participantEmail = currentState.getParticipantEmail();
                            String meetingDate = currentState.getMeetingDate();
                            String startTime = currentState.getStartTime();
                            String endTime = currentState.getEndTime();
                            String projectName = currentState.getProjectName();
                            createMeetingController.execute(
                                    currentState.getMeetingName(),
                                    currentState.getParticipantEmail(),
                                    currentState.getMeetingDate(),
                                    currentState.getStartTime(),
                                    currentState.getEndTime(),
                                    currentState.getProjectName()
                            );
                            currentState = createMeetingViewModel.getState();
                            if (currentState.getMeetingNameError() == null) {
                                JOptionPane.showMessageDialog(CreateMeetingView.this,
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
                        CreateMeetingState currentState = createMeetingViewModel.getState();
                        String text = meetingNameInputField.getText() + e.getKeyChar();
                        currentState.setMeetingName(text);
                        createMeetingViewModel.setState(currentState);
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
                        CreateMeetingState currentState = createMeetingViewModel.getState();
                        String text = participantEmailInputField.getText() + e.getKeyChar();
                        currentState.setParticipantEmail(text);
                        createMeetingViewModel.setState(currentState);
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
                        CreateMeetingState currentState = createMeetingViewModel.getState();
                        String text = meetingDateInputField.getText() + e.getKeyChar();
                        currentState.setMeetingDate(text);
                        createMeetingViewModel.setState(currentState);
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
                        CreateMeetingState currentState = createMeetingViewModel.getState();
                        String text = startTimeInputField.getText() + e.getKeyChar();
                        currentState.setStartTime(text);
                        createMeetingViewModel.setState(currentState);
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
                        CreateMeetingState currentState = createMeetingViewModel.getState();
                        String text = endTimeInputField.getText() + e.getKeyChar();
                        currentState.setEndTime(text);
                        createMeetingViewModel.setState(currentState);
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
        CreateMeetingState state = (CreateMeetingState) evt.getNewValue();
        if (state.getMeetingNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getMeetingNameError());
        }
    }

}