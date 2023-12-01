package interface_adapter.modify_task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeListener;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ModifyTaskViewModelTest {

    private ModifyTaskViewModel viewModel;
    private ModifyTaskState state;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new ModifyTaskViewModel();
        state = new ModifyTaskState();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetAndGetState() {
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void testFirePropertyChanged() {
        // Set a new state to trigger property change
        viewModel.setState(state);
        // Fire property change
        viewModel.firePropertyChanged();
        // Verify that the listener was notified of the change
        verify(mockListener, times(1)).propertyChange(any());
    }

    @Test
    void testAddPropertyChangeListener() {
        // A mock listener is added in setUp()
        // Trigger a change and verify the listener gets notified
        viewModel.setState(state);
        viewModel.firePropertyChanged();
        verify(mockListener, times(1)).propertyChange(any());
    }
}

