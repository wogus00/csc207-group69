package interface_adapter.remove_email;

import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RemoveEmailViewModelTest {

    private RemoveEmailViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new RemoveEmailViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        RemoveEmailState newState = new RemoveEmailState();
        newState.setProjectName("projectName");

        viewModel.setState(newState);

        assertEquals("projectName", viewModel.getState().getProjectName());
    }

}

