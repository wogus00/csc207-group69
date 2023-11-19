package interface_adapter.set_leader;

import interface_adapter.ViewModel;
import interface_adapter.remove_email.RemoveEmailState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SetLeaderViewModel extends ViewModel {
    public static final String CREATE_BUTTON_LABEL = "Create Project";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECTNAME_LABEL = "Enter project name";
    public static final String LEADER_EMAIL_LABEL = "Enter leader's email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";



    private SetLeaderState state = new SetLeaderState();

    public SetLeaderViewModel() {
        super("set new leader");
    }

    public void setState(SetLeaderState state) {
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

    public SetLeaderState getState() {
        return state;
    }
}
