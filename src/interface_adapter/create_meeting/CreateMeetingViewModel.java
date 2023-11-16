package interface_adapter.create_meeting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateMeetingViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Meeting";
    public static final String TITLE_LABEL = "Create Meeting View";
    public static final String MEETING_NAME_LABEL = "Enter meeting name";
    public static final String PARTICIPANT_EMAIL_LABEL = "Enter all participants' email";
    public static final String MEETING_DATE_LABEL = "Enter meeting date";
    public static final String START_TIME_LABEL = "Enter start time";
    public static final String END_TIME_LABEL = "Enter end time";
    public static final String PROJECT_NAME_LABEL = "Enter project name";




    private CreateMeetingState state = new CreateMeetingState();

    public CreateMeetingViewModel() {
        super("create meeting");
    }

    public void setState(CreateMeetingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateMeetingState getState() {
        return state;
    }
}
