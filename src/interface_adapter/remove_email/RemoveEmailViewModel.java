package interface_adapter.remove_email;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code RemoveEmailViewModel} class extends {@code ViewModel} and represents the view model for the remove email feature.
 * It maintains the state relevant to the UI and provides methods to notify observers of changes.
 * This class also defines static constants to be used as labels in the view for consistency and ease of modification.
 */
public class RemoveEmailViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Project";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECTNAME_LABEL = "Enter project name";
    public static final String LEADER_EMAIL_LABEL = "Enter leader's email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String REMOVE_BUTTON_LABEL = "Remove";
    public final String REMOVE_EMAIL_LABEL = "Enter email you want to remove";

    private RemoveEmailState state = new RemoveEmailState();

    /**
     * Constructs an {@code RemoveEmailViewModel} object.
     * Initializes the view model with a default state and the specified context.
     */
    public RemoveEmailViewModel() {
        super("remove email");
    }

    /**
     * Sets the state of the view model.
     *
     * @param state The new state of the view model.
     */
    public void setState(RemoveEmailState state) {
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
     * @return The current {@code RemoveEmailState} of the view model.
     */
    public RemoveEmailState getState() {
        return state;
    }
}
