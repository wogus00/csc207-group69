package interface_adapter.create_meeting;

import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateMeetingViewModelTest {

    private CreateMeetingViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new CreateMeetingViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        CreateMeetingState newState = new CreateMeetingState();
        newState.setMeetingName("New Title");

        viewModel.setState(newState);

        assertEquals("New Title", viewModel.getState().getMeetingName());
    }

    @Test
    public void testPropertyChangeNotification() {
        CreateMeetingState newState = new CreateMeetingState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

}

