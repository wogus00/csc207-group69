package interface_adapter.create_announcement;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateAnnouncementViewModel extends ViewModel {

    public final String TITLE_LABEL = "Announcement Form";

    public final String ANNOUNCEMENT_LABEL = "Decide announcement title";

    public final String ANNOUNCEMENT_MESSAGE = "Write down your message to members";

    public final String CREATE_ANOUNCEMENT_BUTTON_LABEL = "Send Announcement";

    public final String CANSEL_BUTTON_LABEL = "Cansel";

    private CreateAnnouncementState state = new CreateAnnouncementState();

    public CreateAnnouncementViewModel(){
        super("Announcement Form");
    }

    public final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateAnnouncementState getState() {
        return state;
    }

    public void setState(CreateAnnouncementState state) {this.state = state;}
}
