package interface_adapter.main_page;


import interface_adapter.ViewModel;
import interface_adapter.main_page.MainPageState;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for Main Page.
 * This class manages the state and behavior of the main page view, providing a way to interact with the UI.
 */
public class MainPageViewModel extends ViewModel {
    private MainPageState state = new MainPageState();

    /**
     * Constructs a MainPageViewModel with initial settings.
     * Calls the superclass constructor of ViewModel to set its view name.
     */
    public MainPageViewModel() {
        super("Main Page");
    }

    /**
     * Sets the current state of the main page view model.
     *
     * @param state The new main page state to be set.
     */
    public void setState(MainPageState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the property change listeners with a change in state of the main page model
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
     * Retrieves the current state of the main page view.
     *
     * @return The current MainPageView instance.
     */
    public MainPageState getState() {
        return state;
    }


}
