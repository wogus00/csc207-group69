package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete_announcement.DeleteAnnouncementController;
import interface_adapter.delete_announcement.DeleteAnnouncementState;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;

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
/**
 * The view component for deleting announcements in the application.
 * It provides user interface elements for users to select and delete announcements.
 */
public class DeleteAnnouncementView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Delete announcement";

    private DeleteAnnouncementViewModel deleteAnnouncementViewModel;
    ViewManagerModel viewManagerModel;
    private final JTextField announcementIdInputField = new JTextField(15);

//    private final JTextField currentUserInputField = new JTextField(15);
    private DeleteAnnouncementController deleteAnnouncementController;
    private JButton deleteAnnouncementButton;
    private JButton cancel;

    public DeleteAnnouncementView(DeleteAnnouncementController controller,
                                  DeleteAnnouncementViewModel deleteAnnouncementViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.deleteAnnouncementController = controller;
        this.deleteAnnouncementViewModel = deleteAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.deleteAnnouncementViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(deleteAnnouncementViewModel.DELETE_ANNOUNCEMENT_BUTTON_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel announcementIdInfo = new LabelTextPanel(
                new JLabel(deleteAnnouncementViewModel.DELETE_ANNOUNCEMENT_BUTTON_LABEL), announcementIdInputField
        );


        JPanel buttons = new JPanel();
        deleteAnnouncementButton = new JButton(deleteAnnouncementViewModel.DELETE_ANNOUNCEMENT_BUTTON_LABEL);
        buttons.add(deleteAnnouncementButton);
        cancel = new JButton(deleteAnnouncementViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        deleteAnnouncementButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(deleteAnnouncementButton)) {
                            DeleteAnnouncementState currentState = deleteAnnouncementViewModel.getState();

                            String currentID = currentState.getAnnouncementID();
                            String currentUser = currentState.getUserEmail(); //TODO here, i would like to extract the current logged in user.

                            deleteAnnouncementController.execute(currentID, currentUser);
                            currentState = deleteAnnouncementViewModel.getState();
                            if(currentState.getAnnouncementError() == null) {
                                JOptionPane.showMessageDialog(DeleteAnnouncementView.this, "delete announcement successfully");
                                viewManagerModel.setActiveView("Main Page");
                                viewManagerModel.firePropertyChanged();
                                announcementIdInputField.setText("");
                            }
                        }
                    }
                }
        );
        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            viewManagerModel.setActiveView("Main Page");
                            viewManagerModel.firePropertyChanged();
                            announcementIdInputField.setText("");
                        }
                    }
                });

        announcementIdInputField.addKeyListener(
                new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        DeleteAnnouncementState currentState = deleteAnnouncementViewModel.getState();
                        String text = announcementIdInputField.getText() + e.getKeyChar();
                        currentState.setAnnouncementID(text);
                        deleteAnnouncementViewModel.setState(currentState);
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

        this.add(announcementIdInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteAnnouncementState state = (DeleteAnnouncementState) evt.getNewValue();
        if(state.getAnnouncementError() != null){
            JOptionPane.showMessageDialog(this, state.getAnnouncementError());
        }
    }
}

