package interface_adapter.create_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateProjectViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Project";
    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECTNAME_LABEL = "Enter project name";
    public static final String LEADER_EMAIL_LABEL = "Enter leader's email";
    public static final String MEMBER_EMAIL_LABEL = "Enter all members' email";



    private CreateProjectState state = new CreateProjectState();

    public CreateProjectViewModel() {
        super("create project");
    }

    public void setState(CreateProjectState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateProjectState getState() {
        return state;
    }
}
