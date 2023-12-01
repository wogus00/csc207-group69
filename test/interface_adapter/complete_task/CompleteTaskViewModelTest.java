package interface_adapter.complete_task;

import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CompleteTaskViewModelTest {

    private CompleteTaskViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new CompleteTaskViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        CompleteTaskState newState = new CompleteTaskState();
        newState.setTaskName("New Task");

        viewModel.setState(newState);

        assertEquals("New Task", viewModel.getState().getTaskName());
    }

    @Test
    public void testPropertyChangeNotification() {
        CompleteTaskState newState = new CompleteTaskState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

}

