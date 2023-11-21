package interface_adapter.delete_announcement;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Delete Announcement view.
 * It contains the current state of the announcement deletion process and manages property change listeners.
 */
public class DeleteAnnouncementViewModel extends ViewModel {
    private DeleteAnnouncementState state = new DeleteAnnouncementState();

    /**
     * Constructs a DeleteAnnouncementViewModel with default settings.
     */
    public DeleteAnnouncementViewModel(){super("Delete");}

    /**
     * Updates the current state of the announcement deletion process.
     *
     * @param state The new state to be set.
     */
    public void setState(DeleteAnnouncementState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all listeners that the property has changed.
     */
    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the announcement deletion process.
     *
     * @return The current DeleteAnnouncementState.
     */
    public DeleteAnnouncementState getState(){return state;}

}
