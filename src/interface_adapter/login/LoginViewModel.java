package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for Login Use Case.
 * This class manages the state and behavior of the login view, providing a way to interact with the UI.
 */
public class LoginViewModel extends ViewModel {

    public final String TITLE_LABEL = "Log In Screen";
    public final String PROJECT_NAME_LABEL = "Enter the project name";
    public final String USER_EMAIL_LABEL = "Enter your user email";

    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CREATE_BUTTON_LABEL = "Create Project";

    private LoginState state = new LoginState();

    /**
     * Constructs a LoginViewModel with initial settings.
     * Calls the superclass constructor of ViewModel to set its view name.
     */
    public LoginViewModel() {
        super("log in");
    }

    /**
     * Sets the current state of the login view model.
     *
     * @param state The new login state to be set.
     */
    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the property change listeners with a change in state of the login view model
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the login view.
     *
     * @return The current LoginState instance.
     */
    public LoginState getState() {
        return state;
    }
}
