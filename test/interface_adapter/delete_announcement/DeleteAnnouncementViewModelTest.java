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
        DeleteAnnouncementState newState = new DeleteAnnouncementState();
        viewModel.setState(newState);

        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
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

