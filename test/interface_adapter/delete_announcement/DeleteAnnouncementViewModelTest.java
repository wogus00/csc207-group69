package interface_adapter.delete_announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeleteAnnouncementViewModelTest {

    private DeleteAnnouncementViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new DeleteAnnouncementViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetStateAndFirePropertyChanged() {
        // Mock PropertyChangeListener
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener); // Add the listener to the viewModel

        DeleteAnnouncementState oldState = viewModel.getState(); // Get the current state before setting a new one
        DeleteAnnouncementState newState = new DeleteAnnouncementState();
        viewModel.setState(newState);

        // Construct an expected PropertyChangeEvent, but only match propertyName and newValue
        PropertyChangeEvent expectedEvent = new PropertyChangeEvent(
                viewModel, "state", oldState, newState);

        // Verify that propertyChange was called with the expected event, ignoring oldValue
        verify(mockListener, times(1)).propertyChange(
                argThat(event -> event.getPropertyName().equals(expectedEvent.getPropertyName()) &&
                        event.getNewValue().equals(expectedEvent.getNewValue())));

        assertEquals(newState, viewModel.getState(), "The state should match the newly set value.");
    }




    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);
        viewModel.firePropertyChanged();

        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testGetState() {
        DeleteAnnouncementState expectedState = new DeleteAnnouncementState();
        viewModel.setState(expectedState);

        DeleteAnnouncementState actualState = viewModel.getState();
        assertEquals(expectedState, actualState, "The state retrieved should be the one that was set.");
    }
}

