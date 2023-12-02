package interface_adapter.add_email;

import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddEmailViewModelTest {

    private AddEmailViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new AddEmailViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        AddEmailState newState = new AddEmailState();
        newState.setProjectName("projectName");

        viewModel.setState(newState);

        assertEquals("projectName", viewModel.getState().getProjectName());
    }

    @Test
    public void testPropertyChangeNotification() {
        AddEmailState newState = new AddEmailState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

}

