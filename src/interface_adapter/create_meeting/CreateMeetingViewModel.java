package interface_adapter.create_meeting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Create Meeting use case.
 */
public class CreateMeetingViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Meeting";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TITLE_LABEL = "Create Meeting View";
    public static final String MEETING_NAME_LABEL = "Enter meeting name";
    public static final String PARTICIPANT_EMAIL_LABEL = "Enter all participants' emails separated by commas";
    public static final String MEETING_DATE_LABEL = "Enter meeting date (MM/DD/YYYY)";
    public static final String START_TIME_LABEL = "Enter start time (hh:mm in 24h format)";
    public static final String END_TIME_LABEL = "Enter end time (hh:mm in 24h format)";
    public static final String PROJECT_NAME_LABEL = "Enter project name";

    private CreateMeetingState state = new CreateMeetingState();

    /**
     * It constructs a new instance of CreateMeetingViewModel and sets the view name.
     */
    public CreateMeetingViewModel() {
        super("create meeting");
    }

    /**
     * Updates the current state of the Create Meeting use case.
     * @param state The new state to be set.
     */
    public void setState(CreateMeetingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Getter method that returns the current state of the create meeting process.
     * @return state that represents current state of the create meeting process.
     */
    public CreateMeetingState getState() {
        return state;
    }

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
}
