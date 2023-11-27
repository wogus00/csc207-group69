package view;

import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailController;
import interface_adapter.remove_email.RemoveEmailViewModel;

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
 * The RemoveEmailView class represents the graphical user interface for removing emails.
 * It extends JPanel and implements ActionListener and PropertyChangeListener to handle user interactions and model updates.
 */
public class RemoveEmailView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Remove Email";

    private final RemoveEmailViewModel removeEmailViewModel;

    private final JTextField titleInputField = new JTextField(15);

    private final JTextField messageInputFiled = new JTextField(15);

    private final RemoveEmailController removeEmailController;

    private final JButton cancel;

    /**
     * Constructs a RemoveEmailView with the specified controller and view model.
     * It sets up the UI components for the email removal interface.
     *
     * @param controller          The controller that manages user actions.
     * @param removeEmailViewModel The view model holding the state and logic for removing an email.
     */
    public RemoveEmailView(RemoveEmailController controller,
                           RemoveEmailViewModel removeEmailViewModel) {

        this.removeEmailController = controller;
        this.removeEmailViewModel = removeEmailViewModel;
        removeEmailViewModel.addPropertyChangeListener(this);

        LabelTextPanel removeEmailInfo = new LabelTextPanel(
                new JLabel(removeEmailViewModel.REMOVE_EMAIL_LABEL), titleInputField);

        JPanel buttons = new JPanel();
        cancel = new JButton(removeEmailViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.addActionListener(this);

        titleInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RemoveEmailState currentState = removeEmailViewModel.getState();
                        String text = titleInputField.getText() + e.getKeyChar();
                        removeEmailViewModel.setState(currentState);
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
                        RemoveEmailState currentState = removeEmailViewModel.getState();
                        String text = messageInputFiled.getText() + e.getKeyChar();

                        removeEmailViewModel.setState(currentState);
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

        this.add(removeEmailInfo);
        this.add(buttons);
    }

    /**
     * Handles action events triggered in this view.
     * Currently, it prints a message to the console for any action, indicating that the action is not yet implemented.
     *
     * @param e The ActionEvent object containing details of the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cancel not implemented yet.");
    }

    /**
     * Responds to property changes in the view model.
     * It updates the view based on the changes in the model, such as displaying error messages when they occur.
     *
     * @param evt The PropertyChangeEvent object containing details of the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RemoveEmailState state = (RemoveEmailState) evt.getNewValue();
        if (state.error() != null) {
            JOptionPane.showMessageDialog(this, state.error());
        }
    }
}