package interface_adapter.modify_meeting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Modify Meeting use case.
 */
public class ModifyMeetingViewModel extends ViewModel {

    public static final String MODIFY_BUTTON_LABEL = "Modify Meeting";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TITLE_LABEL = "Modify Meeting View";
    public static final String MEETING_NAME_LABEL = "Enter meeting name";
    public static final String PARTICIPANT_EMAIL_LABEL = "Enter all participants' email";
    public static final String MEETING_DATE_LABEL = "Enter meeting date";
    public static final String START_TIME_LABEL = "Enter start time";
    public static final String END_TIME_LABEL = "Enter end time";
    public static final String PROJECT_NAME_LABEL = "Enter project name";

    private ModifyMeetingState state = new ModifyMeetingState();

    /**
     * It constructs a new instance of ModifyMeetingViewModel and sets the view name.
     */
    public ModifyMeetingViewModel() {
        super("modify meeting");
    }

    /**
     * Updates the current state of the Modify Meeting use case.
     * @param state The new state to be set.
     */
    public void setState(ModifyMeetingState state) {
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
     * Getter method that returns the current state of the modify meeting process.
     * @return state that represents current state of the modify meeting process.
     */
    public ModifyMeetingState getState() {
        return state;
    }
}
