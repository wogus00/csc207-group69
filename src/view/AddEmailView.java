package view;
import interface_adapter.add_email.AddEmailController;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.add_email.AddEmailViewModel;

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
 * The AddEmailView class represents the graphical user interface for adding emails.
 * It extends JPanel and implements ActionListener and PropertyChangeListener to handle user interactions and model updates.
 */
public class AddEmailView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Add Email";

    /**
     * Constructs an AddEmailView with the specified controller and view model.
     * It sets up the UI components for the email addition interface.
     *
     * @param controller         The controller that manages user actions.
     * @param addEmailViewModel  The view model holding the state and logic for adding an email.
     */
    private final AddEmailViewModel addEmailViewModel;

    private final JTextField titleInputField = new JTextField(15);

    private final JTextField messageInputFiled = new JTextField(15);

    private final AddEmailController addEmailController;

    private final JButton cancel;
    private final JButton add;

    public AddEmailView(AddEmailController controller,
                        AddEmailViewModel addEmailViewModel) {

        this.addEmailController = controller;
        this.addEmailViewModel = addEmailViewModel;
        addEmailViewModel.addPropertyChangeListener(this);

        LabelTextPanel addEmailInfo = new LabelTextPanel(
                new JLabel(addEmailViewModel.ADD_EMAIL_LABEL), titleInputField);

        JPanel buttons = new JPanel();
        cancel = new JButton(addEmailViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.addActionListener(this);
        add = new JButton(addEmailViewModel.ADD_BUTTON_LABEL);
        buttons.add(add);
        add.addActionListener(this);

        titleInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddEmailState currentState = addEmailViewModel.getState();
                        String text = titleInputField.getText() + e.getKeyChar();
                        addEmailViewModel.setState(currentState);
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
                        AddEmailState currentState = addEmailViewModel.getState();
                        String text = messageInputFiled.getText() + e.getKeyChar();

                        addEmailViewModel.setState(currentState);
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

        this.add(addEmailInfo);
        this.add(buttons);
    }

    /**
     * Handles action events triggered in this view.
     * Currently, it prints a message to the console for any action.
     *
     * @param e The ActionEvent object containing details of the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cancel not implemented yet.");
    }

    /**
     * Responds to property changes in the view model.
     * It updates the view based on the changes in the model, such as displaying error messages.
     *
     * @param evt The PropertyChangeEvent object containing details of the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddEmailState state = (AddEmailState) evt.getNewValue();
        if (state.error() != null) {
            JOptionPane.showMessageDialog(this, state.error());
        }
    }
}