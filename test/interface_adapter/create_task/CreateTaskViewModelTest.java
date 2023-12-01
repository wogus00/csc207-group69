package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeListener;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskViewModelTest {

    @Test
    public void testSetAndGetState() {
        CreateTaskViewModel viewModel = new CreateTaskViewModel();
        CreateTaskState state = new CreateTaskState();
        state.setProjectName("Project X");

        viewModel.setState(state);

        assertEquals(state, viewModel.getState(), "State was not set or retrieved correctly");
    }

    @Test
    public void testFirePropertyChanged() {
        CreateTaskViewModel viewModel = new CreateTaskViewModel();
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        // Verify that the property change event is fired
        verify(listener).propertyChange(any());
    }

    @Test
    public void testAddPropertyChangeListener() {
        CreateTaskViewModel viewModel = new CreateTaskViewModel();
        PropertyChangeListener listener = mock(PropertyChangeListener.class);

        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        // Verify that the listener is notified
        verify(listener).propertyChange(any());
    }
}

