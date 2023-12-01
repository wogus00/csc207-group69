package interface_adapter.modify_meeting;

import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ModifyMeetingViewModelTest {

    private ModifyMeetingViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new ModifyMeetingViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        ModifyMeetingState newState = new ModifyMeetingState();
        newState.setMeetingName("New Title");

        viewModel.setState(newState);

        assertEquals("New Title", viewModel.getState().getMeetingName());
    }

    @Test
    public void testPropertyChangeNotification() {
        ModifyMeetingState newState = new ModifyMeetingState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

}

