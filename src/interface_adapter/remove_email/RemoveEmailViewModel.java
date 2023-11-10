package interface_adapter.remove_email;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RemoveEmailViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Project";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECTNAME_LABEL = "Enter project name";
    public static final String LEADER_EMAIL_LABEL = "Enter leader's email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";



    private RemoveEmailState state = new RemoveEmailState();

    public RemoveEmailViewModel() {
        super("remove email");
    }

    public void setState(RemoveEmailState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public RemoveEmailState getState() {
        return state;
    }
}
