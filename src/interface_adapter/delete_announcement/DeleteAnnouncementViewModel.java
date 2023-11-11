package interface_adapter.delete_announcement;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteAnnouncementViewModel extends ViewModel {
    private DeleteAnnouncementState state = new DeleteAnnouncementState();

    public DeleteAnnouncementViewModel(){super("Delete");}

    public void setState(DeleteAnnouncementState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public DeleteAnnouncementState getState(){return state;}

}
