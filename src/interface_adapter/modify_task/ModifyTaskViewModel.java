package interface_adapter.modify_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for Modify Task Use Case.
 * It contains the current state of the task creation process and manages property change listeners.
 */
public class ModifyTaskViewModel extends ViewModel {

    public static final String MODIFY_BUTTON_LABEL = "Modify Task";
    public static final String TITLE_LABEL = "Modify Task View";
    public static final String TASK_NAME_LABEL = "Enter Task Name";
    public static final String SUPERVISOR_EMAIL_LABEL = "Enter Supervisor Email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' emails";
    public static final String DEADLINE_LABEL = "Enter deadline (YYYY-MM-DD)";
    public static final String COMMENT_LABEL = "Additional Comments";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private ModifyTaskState state = new ModifyTaskState();

    public ModifyTaskViewModel() {
        super("Modify Task");
    }

    /**
     * Setter method that updates the current state of the creation task process.
     * @param state state that represents the new updated state for creation task process.
     */
    public void setState(ModifyTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    /**
     * Getter method that returns the current state of the creating task process.
     * @return state that represents current state of the creating task process.
     */
    public ModifyTaskState getState() {
        return state;
    }


    /**
     * Notifies all listeners that the property has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChange Listener to the listener list.
     * @param listener the PropertyChangeListener that will be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}