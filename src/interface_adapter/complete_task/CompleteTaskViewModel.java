package interface_adapter.complete_task;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CompleteTaskViewModel {
    public static final String COMPLETE_BUTTON_LABEL = "Complete Task";
    public static final String TASK_NAME_LABEL = "Enter Task Name";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TITLE_LABEL = "Complete Task";
    private CompleteTaskState state = new CompleteTaskState();

    public void setState(CompleteTaskState state) {
        this.state = state;
    }
    public CompleteTaskState getState() {
        return state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


}
