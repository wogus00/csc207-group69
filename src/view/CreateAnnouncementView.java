package view;
import interface_adapter.create_announcement.CreateAnnouncementController;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;

import javax.mail.internet.AddressException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class CreateAnnouncementView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Create announcement";

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    private final JTextField titleInputField = new JTextField(15);

    private final JTextField messageInputFiled = new JTextField(15);

    private final CreateAnnouncementController createAnnouncementController;

    private final JButton announcementSent;

    private final JButton cancel;

    public CreateAnnouncementView(CreateAnnouncementController controller,
                                  CreateAnnouncementViewModel createAnnouncementViewModel) {

        this.createAnnouncementController = controller;
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        createAnnouncementViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(createAnnouncementViewModel.ANNOUNCEMENT_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel announcementTitleInfo = new LabelTextPanel(
                new JLabel(createAnnouncementViewModel.ANNOUNCEMENT_LABEL), titleInputField);
        LabelTextPanel announcementMessageInfo = new LabelTextPanel(
                new JLabel(createAnnouncementViewModel.ANNOUNCEMENT_MESSAGE), messageInputFiled);

        JPanel buttons = new JPanel();
        announcementSent = new JButton(createAnnouncementViewModel.CREATE_ANOUNCEMENT_BUTTON_LABEL);
        buttons.add(announcementSent);
        cancel = new JButton(createAnnouncementViewModel.CANSEL_BUTTON_LABEL);
        buttons.add(cancel);

        announcementSent.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(announcementSent)) {
                            CreateAnnouncementState currentState = createAnnouncementViewModel.getState();

                            try{
                                String currentTitle = currentState.getAnnouncementTitle();
                                String currentMessage = currentState.getMessage();
                                String author = currentState.getAuthor();
                                createAnnouncementController.execute(currentTitle, currentMessage, author);
                            } catch (IOException | AddressException e) {
                                throw new RuntimeException(e);
                            }
                            currentState = createAnnouncementViewModel.getState();
                            if (currentState.getAnnouncementTitleError() == null) {
                                JOptionPane.showMessageDialog(CreateAnnouncementView.this, "create announcement successfully");
                            }



                        }
                    }
                }
        );
        cancel.addActionListener(this);

        titleInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateAnnouncementState currentState = createAnnouncementViewModel.getState();
                        String text = titleInputField.getText() + e.getKeyChar();
                        currentState.setAnnouncementTitle(text);
                        createAnnouncementViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        messageInputFiled.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateAnnouncementState currentState = createAnnouncementViewModel.getState();
                        String text = messageInputFiled.getText() + e.getKeyChar();
                        currentState.setMessage(text);

                        createAnnouncementViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(announcementTitleInfo);
        this.add(announcementMessageInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateAnnouncementState state = (CreateAnnouncementState) evt.getNewValue();
        if (state.getAnnouncementTitleError() != null) {
            JOptionPane.showMessageDialog(this, state.getAnnouncementTitleError());
        }
    }
}
