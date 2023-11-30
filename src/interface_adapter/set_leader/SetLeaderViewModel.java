package interface_adapter.set_leader;

import interface_adapter.ViewModel;
import interface_adapter.remove_email.RemoveEmailState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code SetLeaderViewModel} class extends {@code ViewModel} and represents the view model for the set leader feature.
 * It maintains the state relevant to the UI and provides methods to notify observers of changes.
 * This class also defines static constants to be used as labels in the view for consistency and ease of modification.
 */
public class SetLeaderViewModel extends ViewModel {
    public static final String SET_BUTTON_LABEL = "Set Leader";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String SET_LEADER_LABEL = "Enter new leader";

    private SetLeaderState state = new SetLeaderState();

    /**
     * Constructs an {@code SetLeaderViewModel} object.
     * Initializes the view model with a default state and the specified context.
     */
    public SetLeaderViewModel() {
        super("set new leader");
    }

    /**
     * Sets the state of the view model.
     *
     * @param state The new state of the view model.
     */
    public void setState(SetLeaderState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all listeners that the property 'state' has changed.
     * This method is typically called when the state changes in a way that should be reflected in the view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a {@code PropertyChangeListener} to the listener list.
     * The listener is registered for all properties and will be notified when a property change event is fired.
     *
     * @param listener The {@code PropertyChangeListener} to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    /**
     * Removes a {@code PropertyChangeListener} from the listener list.
     * This method allows for the dynamic management of listeners that should no longer receive property change events.
     *
     * @param listener The {@code PropertyChangeListener} to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the view model.
     *
     * @return The current {@code SetLeaderState} of the view model.
     */
    public SetLeaderState getState() {
        return state;
    }
}
