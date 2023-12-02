package interface_adapter.create_project;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProjectViewModelTest {

    @Test
    public void testInitialState() {
        CreateProjectViewModel viewModel = new CreateProjectViewModel();
        CreateProjectState initialState = viewModel.getState();

        assertNotNull(initialState);
        assertNotNull(initialState.getProjectName());
        assertNotNull(initialState.getLeaderEmail());
        assertTrue(initialState.getMemberEmail().isEmpty());
    }

    @Test
    public void testSetState() {
        CreateProjectViewModel viewModel = new CreateProjectViewModel();
        CreateProjectState newState = new CreateProjectState();
        newState.setProjectName("TestProject");
        newState.setLeaderEmail("leader@example.com");

        viewModel.setState(newState);

        assertEquals("TestProject", viewModel.getState().getProjectName());
        assertEquals("leader@example.com", viewModel.getState().getLeaderEmail());
    }

    @Test
    public void testFirePropertyChanged() {
        CreateProjectViewModel viewModel = new CreateProjectViewModel();
        PropertyChangeCounter listener = new PropertyChangeCounter();
        viewModel.addPropertyChangeListener((PropertyChangeListener) listener);

        viewModel.firePropertyChanged();

        assertEquals(1, listener.getPropertyChangeCount());
    }

    // Add more tests for other methods and cases as needed

    private static class PropertyChangeCounter implements PropertyChangeListener {
        private int propertyChangeCount = 0;

        @Override
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            propertyChangeCount++;
        }

        public int getPropertyChangeCount() {
            return propertyChangeCount;
        }
    }
}
