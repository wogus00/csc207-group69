package view;

import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;

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

    private final CreateMeetingViewModel createMeetingViewModel;
    private final JTextField meetingNameInputField = new JTextField(15);
    private final JTextField participantEmailInputField = new JTextField(15);
    private final Scanner meetingDateInputField = new Scanner(System.in);
    private final Time startTimeInputField = new Time(15);
    private final Time endTimeInputField = new Time(15);
    private final CreateMeetingController createMeetingController;

    private final JButton create;


    public CreateMeetingView(CreateMeetingController createMeetingController,
                             CreateMeetingViewModel createMeetingViewModel) {

        this.createMeetingController = createMeetingController;
        this.createMeetingViewModel = createMeetingViewModel;
        createMeetingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel meetingNameInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.MEETING_NAME_LABEL), meetingNameInputField);
        LabelTextPanel participantEmailInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.PARTICIPANT_EMAIL_LABEL), participantEmailInputField);
        LabelTextPanel meetingDateInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.MEETING_DATE_LABEL), meetingDateInputField);
        LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.START_TIME_LABEL), startTimeInputField);
        LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel(CreateProjectViewModel.END_TIME_LABEL), endTimeInputField);

        JPanel buttons = new JPanel();
        create = new JButton(CreateProjectViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);


        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateProjectState currentState = createMeetingViewModel.getState();

                            createMeetingController.execute(
                                    currentState.getMeetingtName(),
                                    currentState.getParticipantEmail(),
                                    currentState.getMeetingDate(),
                                    currentState.getStartTime(),
                                    currentState.getEndTime()
                            );
                        }
                    }
                }
        );


        meetingNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateMeetingState currentState = createMeetingtViewModel.getState();
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
        this.add(projectNameInfo);
        this.add(participantEmailInfo);
        this.add(meetingDatelInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(buttons);
    }

}