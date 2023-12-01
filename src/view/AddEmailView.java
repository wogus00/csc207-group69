package view;
import interface_adapter.ViewManagerModel;
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
    final AddEmailViewModel addEmailViewModel;
    ViewManagerModel viewManagerModel;

    final JTextField addEmailInputField = new JTextField(15);
    private final AddEmailController addEmailController;

    final JButton cancel;
    final JButton add;

    public AddEmailView(AddEmailController controller,
                        AddEmailViewModel addEmailViewModel,
                        ViewManagerModel viewManagerModel) {

        this.addEmailController = controller;
        this.addEmailViewModel = addEmailViewModel;
        this.addEmailViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        LabelTextPanel addEmailInfo = new LabelTextPanel(
                new JLabel(addEmailViewModel.ADD_EMAIL_LABEL), addEmailInputField);

        JPanel buttons = new JPanel();
        add = new JButton(addEmailViewModel.ADD_BUTTON_LABEL);
        buttons.add(add);
        cancel = new JButton(addEmailViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        add.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addEmailController.addProjectDetails(addEmailViewModel.getState().getProjectName(),
                                addEmailInputField.getText());
                    }
                }
        );

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancel)) {
                    viewManagerModel.setActiveView("Main Page");
                    viewManagerModel.firePropertyChanged();
                    addEmailInputField.setText("");
                }
            }
        });

        addEmailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

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