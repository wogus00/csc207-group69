package interface_adapter.main_page;

import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MainPageViewModelTest {

    private MainPageViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new MainPageViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        MainPageState newState = new MainPageState();
        newState.setProjectName("Test Project");

        viewModel.setState(newState);

        assertEquals("Test Project", viewModel.getState().getProjectName());
    }

    @Test
    public void testPropertyChangeNotification() {
        MainPageState oldState = viewModel.getState();
        MainPageState newState = new MainPageState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

    // Additional tests for other functionalities...
}
