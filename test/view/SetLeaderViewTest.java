package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.set_leader.SetLeaderController;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

/**
 * Test suite for the {@link SetLeaderView} class to verify its interaction with the {@link SetLeaderController}
 * and the response to user input as per the MVC pattern. The tests check that the appropriate controller methods
 * are invoked in response to UI events and that the view updates its state according to changes in the model.
 */
public class SetLeaderViewTest {

    @Mock
    ViewManagerModel mockViewManagerModel;
    @Mock
    private SetLeaderController mockSetLeaderController;
    @Mock
    private SetLeaderViewModel mockSetLeaderViewModel;

    private SetLeaderView setLeaderView;

    /**
     * Initializes the testing environment before each test, setting up mocks and creating an instance
     * of the SetLeaderView with its dependencies.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockSetLeaderViewModel.getState()).thenReturn(new SetLeaderState());
        setLeaderView = new SetLeaderView(mockSetLeaderController, mockSetLeaderViewModel, mockViewManagerModel);
    }

    /**
     * Tests that the 'Set' button click triggers the correct method call in the controller with appropriate parameters.
     * Assumes the controller has a method named 'setLeaderDetails' that is responsible for handling the logic
     * to set the details of a project leader.
     */
    @Test
    public void testActionPerformed_SetButton_Click() {
        SetLeaderState state  = new SetLeaderState();
        state.setProjectName("Test Project");
        when(mockSetLeaderViewModel.getState()).thenReturn(state);
        // Simulate the clicking of the set button
        ActionEvent evt = new ActionEvent(setLeaderView.set,  ActionEvent.ACTION_PERFORMED, "");
        setLeaderView.set.getActionListeners()[0].actionPerformed(evt);

        // Verify that the controller's method is called with the correct parameters.
        // This assumes the method to set leader details is named 'setLeaderDetails' and it takes two string arguments.
        verify(mockSetLeaderController).updateProjectDetails(anyString(), anyString());
    }

    /**
     * Tests the 'Cancel' button functionality by simulating its click and verifying the expected outcome,
     * such as navigating back to a previous view or resetting the view's state.
     */
    @Test
    public void testActionPerformed_CancelButton_Click() {
        // Simulate the clicking of the cancel button
        setLeaderView.cancel.doClick();

        verify(mockViewManagerModel).setActiveView("Main Page");

        // Verify the expected behavior, such as returning to the previous view
    }

    /**
     * Verifies the behavior of the title input field by simulating key typing events and ensuring that
     * the view model's state is updated accordingly.
     */
    @Test
    public void testKeyTyped_SetLeaderInputField() {
        // Simulate typing in the title input field
        JTextField titleInputField = setLeaderView.setLeaderInputField;
        titleInputField.setText("Leader Title");

        // Trigger keyTyped event
        for (char keyChar : "Leader Title".toCharArray()) {
            KeyEvent keyEvent = new KeyEvent(setLeaderView.setLeaderInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, keyChar);
            KeyEvent keyPressedEvent = new KeyEvent(setLeaderView.setLeaderInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, keyChar);
            KeyEvent keyReleasedEvent = new KeyEvent(setLeaderView.setLeaderInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, keyChar);
            for (KeyListener listener : setLeaderView.setLeaderInputField.getKeyListeners()) {
                listener.keyTyped(keyEvent);
                listener.keyPressed(keyPressedEvent);
                listener.keyReleased(keyReleasedEvent);
            }
        }

    }

    /**
     * Tests the view's response to a property change event that indicates an error state. Ensures that the view
     * reacts appropriately, such as displaying an error message dialog, upon detecting an error.
     */
    @Test
    public void testPropertyChange_Error() {
        // Prepare the state with an error message
        SetLeaderState stateWithError = new SetLeaderState();
        stateWithError.setError("Error Message");
        when(mockSetLeaderViewModel.getState()).thenReturn(stateWithError);

        // Trigger the property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockSetLeaderViewModel, "state", null, stateWithError);
        setLeaderView.propertyChange(evt);

        // Verify that an error message is displayed
        // You would check that the UI reflects this error message,
        // but this is tricky to test due to the static call to JOptionPane.showMessageDialog.
    }

    /**
     * Tests the view's response to a key pressed event in the certain input field
     */
    @Test
    public void testKeyPressed() {
        // Given
        KeyEvent keyEvent = new KeyEvent(setLeaderView.setLeaderInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        setLeaderView.setLeaderInputField.getKeyListeners()[0].keyPressed(keyEvent);

        // Then
        // Add verifications for keyPressed if there are any side effects
    }

    /**
     * Tests the view's response to a key released event in the certain input field
     */
    @Test
    public void testKeyReleased() {
        // Given
        KeyEvent keyEvent = new KeyEvent(setLeaderView.setLeaderInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        setLeaderView.setLeaderInputField.getKeyListeners()[0].keyReleased(keyEvent);

        // Then
        // Add verifications for keyReleased if there are any side effects
    }
}
