package interface_adapter.add_email;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code AddEmailViewModel} class extends {@code ViewModel} and represents the view model for the add email feature.
 * It maintains the state relevant to the UI and provides methods to notify observers of changes.
 * This class also defines static constants to be used as labels in the view.
 */
public class AddEmailViewModel extends ViewModel {

    public final String ADD_BUTTON_LABEL = "Add Member";
    public static final String TITLE_LABEL = "Create Project View";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String ADD_EMAIL_LABEL = "Enter email you want to add";

    private AddEmailState state = new AddEmailState();

    /**
     * Constructs an {@code AddEmailViewModel} object.
     * Initializes the view model with a default state and the specified context.
     */
    public AddEmailViewModel() {
        super("add email");
    }

    /**
     * Sets the state of the view model.
     *
     * @param state The new state of the view model.
     */
    public void setState(AddEmailState state) {
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
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the view model.
     *
     * @return The current {@code AddEmailState} of the view model.
     */
    public AddEmailState getState() {
        return state;
    }
}
