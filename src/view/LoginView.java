package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View class representing the login view in the application's GUI.
 * This class is responsible for rendering the login interface, handling user inputs,
 * and responding to login-related events.
 * It extends JPanel to fit in the CardLayout of views, and implements ActionListener and
 * PropertyChangeListener to respond to the user actions and model changes.
 */
public class LoginView extends JPanel implements PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    final JTextField projectNameInputField = new JTextField(15);

    final JTextField userEmailInputField = new JTextField(15);
    private final JLabel userEmailErrorField = new JLabel();

    final JButton logIn;
    final JButton create;
    private final LoginController loginController;

    /**
     * Constructs a new LoginView with the specified models and controller.
     * Sets up the UI components for the login view, including input fields for project name and user email,
     * login and create buttons, and initializes action listeners for these components.
     *
     * @param loginViewModel The view model for the login view, manages the state and behavior of the login view
     * @param controller The login controller that handles login actions performed.
     * @param viewManagerModel The model responsible for managing different views in the application.
     */
    public LoginView(LoginViewModel loginViewModel, LoginController controller, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(loginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectNameInfo = new LabelTextPanel(
                new JLabel(loginViewModel.PROJECT_NAME_LABEL), projectNameInputField);
        LabelTextPanel userEmailInfo = new LabelTextPanel(
                new JLabel(loginViewModel.USER_EMAIL_LABEL), userEmailInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        create = new JButton(loginViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getProjectName(),
                                    currentState.getUserEmail()
                            );
                        }
                    }
                }
        );


        create.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            viewManagerModel.setActiveView("create project");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        projectNameInputField.addKeyListener(
                new KeyListener() {

            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setProjectName(projectNameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }


            public void keyPressed(KeyEvent e) {
            }


            public void keyReleased(KeyEvent e) {

            }
        }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userEmailInputField.addKeyListener(
                new KeyListener() {

                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setUserEmail(userEmailInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }


                    public void keyPressed(KeyEvent e) {
                    }


                    public void keyReleased(KeyEvent e) {
                    }
                }
            );

        this.add(title);
        this.add(projectNameInfo);
        this.add(userEmailInfo);
        this.add(userEmailErrorField);
        this.add(buttons);
    }



    /**
     * React to property changes in the view model.
     *
     * @param evt The PropertyChangeEvent object.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
        } if (state.isLogout()) {
            projectNameInputField.setText("");
            userEmailInputField.setText("");
        }

    }

}