package interface_adapter.create_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Create Meeting use case.
 */
public class CreateProjectViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Project";
    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECTNAME_LABEL = "Enter project name";
    public static final String LEADER_EMAIL_LABEL = "Enter leader's email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";



    private CreateProjectState state = new CreateProjectState();


    /**
     * It constructs a new instance of CreateProjectViewModel and sets the view name.
     */
    public CreateProjectViewModel() {
        super("create project");
    }

    /**
     * Updates the current state of the Create Project use case.
     * @param state The new state to be set.
     */
    public void setState(CreateProjectState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all listeners that the property has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter method that returns the current state of the create project process.
     * @return state that represents current state of the create project process.
     */
    public CreateProjectState getState() {
        return state;
    }
}
