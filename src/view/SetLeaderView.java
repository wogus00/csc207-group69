package view;
import interface_adapter.set_leader.SetLeaderController;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;

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
 * The SetLeaderView class represents the graphical user interface for setting a leader.
 * It extends JPanel and implements ActionListener and PropertyChangeListener to handle user interactions and model updates.
 */
public class SetLeaderView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Set Leader";

    private final SetLeaderViewModel setLeaderViewModel;

    private final JTextField titleInputField = new JTextField(15);

    private final JTextField messageInputFiled = new JTextField(15);

    private final SetLeaderController setLeaderController;

    private final JButton cancel;
    private final JButton set;

    /**
     * Constructs a SetLeaderView with the specified controller and view model.
     * It sets up the UI components for the leader setting interface.
     *
     * @param controller          The controller that manages user actions.
     * @param setLeaderViewModel  The view model holding the state and logic for setting a leader.
     */
    public SetLeaderView(SetLeaderController controller,
                         SetLeaderViewModel setLeaderViewModel) {

        this.setLeaderController = controller;
        this.setLeaderViewModel = setLeaderViewModel;
        setLeaderViewModel.addPropertyChangeListener(this);

        LabelTextPanel setLeaderInfo = new LabelTextPanel(
                new JLabel(setLeaderViewModel.SET_LEADER_LABEL), titleInputField);

        JPanel buttons = new JPanel();
        cancel = new JButton(setLeaderViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.addActionListener(this);
        set = new JButton(setLeaderViewModel.SET_BUTTON_LABEL);
        buttons.add(set);
        set.addActionListener(this);

        titleInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SetLeaderState currentState = setLeaderViewModel.getState();
                        String text = titleInputField.getText() + e.getKeyChar();
                        setLeaderViewModel.setState(currentState);
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
                        SetLeaderState currentState = setLeaderViewModel.getState();
                        String text = messageInputFiled.getText() + e.getKeyChar();

                        setLeaderViewModel.setState(currentState);
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

        this.add(setLeaderInfo);
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
        SetLeaderState state = (SetLeaderState) evt.getNewValue();
        if (state.error() != null) {
            JOptionPane.showMessageDialog(this, state.error());
        }
    }
}