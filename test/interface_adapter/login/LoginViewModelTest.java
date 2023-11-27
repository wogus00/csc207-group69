package interface_adapter.login;

import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginViewModelTest {

    private LoginViewModel viewModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new LoginViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testStateManagement() {
        LoginState newState = new LoginState();
        newState.setProjectName("Test Project");

        viewModel.setState(newState);

        assertEquals("Test Project", viewModel.getState().getProjectName());
    }

    @Test
    public void testPropertyChangeNotification() {
        LoginState newState = new LoginState();
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any());
    }

    // Additional tests for other functionalities...
}

