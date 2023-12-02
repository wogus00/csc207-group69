package interface_adapter.set_leader;

import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SetLeaderViewModelTest {

    private SetLeaderViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new SetLeaderViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        SetLeaderState newState = new SetLeaderState();
        newState.setProjectName("projectName");

        viewModel.setState(newState);

        assertEquals("projectName", viewModel.getState().getProjectName());
    }

}

