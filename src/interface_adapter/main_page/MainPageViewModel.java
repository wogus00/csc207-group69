package interface_adapter.main_page;


import interface_adapter.ViewModel;
import interface_adapter.main_page.MainPageState;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainPageViewModel extends ViewModel {
    private MainPageState state = new MainPageState();

    public MainPageViewModel() {
        super("Main Page");
    }

    public void setState(MainPageState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MainPageState getState() {
        return state;
    }


}
