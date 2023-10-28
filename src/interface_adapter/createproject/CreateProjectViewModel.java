package interface_adapter.createproject;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateProjectViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String USERNAME_LABEL = "Choose Project Name";
    public static final String PASSWORD_LABEL = "Enter Leader Email";
    public static final String REPEAT_PASSWORD_LABEL = "Enter Member Emails (Separated by Commas)";

    public static final String SIGNUP_BUTTON_LABEL = "Create Project";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateProjectState state = new CreateProjectState();

    public CreateProjectViewModel() {
        super("create projet");
    }

    public void setState(CreateProjectState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateProjectState getState() {
        return state;
    }
}
