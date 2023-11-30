package interface_adapter.complete_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for Complete Task Use Case.
 * It contains the current state of the task completion process and manages property change listeners.
 */
public class CompleteTaskViewModel extends ViewModel {
    public static final String COMPLETE_BUTTON_LABEL = "Complete Task";
    public static final String TASK_NAME_LABEL = "Enter Task Name";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TITLE_LABEL = "Complete Task";
    private CompleteTaskState state = new CompleteTaskState();

    public CompleteTaskViewModel() {
        super("Complete Task");
    }

    /**
     * Setter method that updates the current state of the completing task process.
     * @param state state that represents the new updated state for completing task process.
     */
    public void setState(CompleteTaskState state) {
        this.state = state;
    }

    /**
     * Getter method that returns the current state of the completing task process.
     * @return state that represents current state of the completing task process.
     */
    public CompleteTaskState getState() {
        return state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
