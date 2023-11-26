package interface_adapter.create_announcement;

import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateAnnouncementViewModelTest {

    private CreateAnnouncementViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new CreateAnnouncementViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        CreateAnnouncementState newState = new CreateAnnouncementState();
        newState.setAnnouncementTitle("New Title");

        viewModel.setState(newState);

        assertEquals("New Title", viewModel.getState().getAnnouncementTitle());
    }

    @Test
    public void testPropertyChangeNotification() {
        CreateAnnouncementState newState = new CreateAnnouncementState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

    // Additional tests for other functionalities...
}

