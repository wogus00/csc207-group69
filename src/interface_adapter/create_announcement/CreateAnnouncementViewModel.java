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

    /**
     * Constructs a CreateAnnouncementViewModel and sets the view name.
     */
    public CreateAnnouncementViewModel(){
        super("Announcement Form");
    }

    public final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all listeners that the property has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }


    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the announcement creation form.
     *
     * @return The current CreateAnnouncementState.
     */
    public CreateAnnouncementState getState() {
        return state;
    }

    /**
     * Updates the current state of the announcement creation form.
     *
     * @param state The new state to be set.
     */
    public void setState(CreateAnnouncementState state) {this.state = state;}
}
