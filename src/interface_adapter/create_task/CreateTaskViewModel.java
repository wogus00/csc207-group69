package interface_adapter.create_task;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateTaskViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Task";
    public static final String TITLE_LABEL = "Create Task View";
    public static final String TASK_NAME_LABEL = "Enter Task Name";
    public static final String SUPERVISOR_EMAIL_LABEL = "Enter Supervisor Email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' emails";
    public static final String DEADLINE_LABEL = "Enter deadline (YYYY-MM-DD)";
    public static final String COMMENT_LABEL = "Additional Comments";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private CreateTaskState state = new CreateTaskState();

    public void setState(CreateTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public CreateTaskState getState() {
        return state;
    }


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}