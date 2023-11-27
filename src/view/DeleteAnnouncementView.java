package view;

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
 * The DeleteAnnouncementView class represents the GUI view for deleting announcements.
 * This class extends JPanel and includes user interface elements for selecting and
 * deleting announcements.
 */
public class DeleteAnnouncementView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Delete announcement";

    private DeleteAnnouncementViewModel deleteAnnouncementViewModel;
    private final JTextField announcementIdInputField = new JTextField(15);

//    private final JTextField currentUserInputField = new JTextField(15);
    private DeleteAnnouncementController deleteAnnouncementController;
    private JButton deleteAnnouncementButton;
    private JButton cancel;

    /**
     * Constructs a new DeleteAnnouncementView with a given controller and view model.
     *
     * @param controller The controller associated with this view.
     * @param deleteAnnouncementViewModel The view model for delete announcement operations.
     */
    public DeleteAnnouncementView(DeleteAnnouncementController controller,
                                  DeleteAnnouncementViewModel deleteAnnouncementViewModel) {
        this.deleteAnnouncementController = controller;
        this.deleteAnnouncementViewModel = deleteAnnouncementViewModel;
        deleteAnnouncementViewModel.addPropertyChangeListener(this);

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
                            String currentUser = null; //TODO here, i would like to extract the current logged in user.

                            deleteAnnouncementController.execute(currentID, currentUser);
                            currentState = deleteAnnouncementViewModel.getState();
                            if(currentState.getAnnouncementError() == null) {
                                JOptionPane.showMessageDialog(DeleteAnnouncementView.this, "delete announcement successfully");

                            }
                        }
                    }
                }
        );
        cancel.addActionListener(this);

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

    /**
     * Invoked when an action occurs on the view components.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cancel not implemented yet.");
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteAnnouncementState state = (DeleteAnnouncementState) evt.getNewValue();
        if(state.getAnnouncementError() != null){
            JOptionPane.showMessageDialog(this, state.getAnnouncementError());
        }
    }
}

