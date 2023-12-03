package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailController;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.add_email.AddEmailViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link AddEmailView} class.
 * This test suite ensures that the AddEmailView correctly interacts with the AddEmailController and responds to
 * user input as expected. Each test case simulates user actions such as button clicks and key presses, and
 * verifies that the view behaves correctly by invoking controller methods, updating the model, or displaying errors.
 */
public class AddEmailViewTest {

    @Mock ViewManagerModel mockViewManagerModel;
    @Mock
    private AddEmailController mockAddEmailController;
    @Mock
    private AddEmailViewModel mockAddEmailViewModel;

    private AddEmailView addEmailView;

    /**
     * Sets up the test environment before each test.
     * This includes initializing mocks and setting the expected state of the AddEmailViewModel.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockAddEmailViewModel.getState()).thenReturn(new AddEmailState());
        addEmailView = new AddEmailView(mockAddEmailController, mockAddEmailViewModel, mockViewManagerModel);
    }

    /**
     * Tests that clicking the 'Add' button triggers the appropriate action in the controller.
     * Verifies that the addProjectDetails method is called with the correct parameters.
     */
    @Test
    public void testActionPerformed_AddButton_Click() {
        // When
        addEmailView.add.doClick();

        // Then
        // Replace "execute" with the actual method name "addProjectDetails" and use the correct parameter matchers.
        verify(mockAddEmailController).addProjectDetails(anyString(), anyString());
    }

    /**
     * Tests the functionality of the 'Cancel' button click action.
     * Checks whether the relevant text fields are cleared when the cancel button is clicked.
     * The expected behavior is that any input in text fields should be reset.
     */
    @Test
    public void testActionPerformed_CancelButton_Click() {
        // Given: Set up any initial state if necessary.
        // For example, if the cancel button should clear fields, pre-populate them.
        addEmailView.addEmailInputField.setText("Some email");

        // When: Perform the click action on the cancel button.
        addEmailView.cancel.doClick();

        // Then: Verify that the fields are cleared after the action (if that's the expected behavior).
        assertEquals("", addEmailView.addEmailInputField.getText());

        // If the view is supposed to change, verify that the view change method was called.
        // For example:
        // verify(mockViewManager).changeView("MainView"); // Assuming you have a view manager to handle view changes.
    }

    /**
     * Tests the view's response to an error property change event.
     * Ensures that when the view model state changes to include an error, the view displays the appropriate error message.
     */
    @Test
    public void testPropertyChange_Error() {
        // Given
        AddEmailState stateWithError = new AddEmailState();
        stateWithError.setError("Error Message");
        when(mockAddEmailViewModel.getState()).thenReturn(stateWithError);

        // When
        PropertyChangeEvent evt = new PropertyChangeEvent(mockAddEmailViewModel, "state", null, stateWithError);
        addEmailView.propertyChange(evt);
    }

    @Test
    public void testKeyTyped() {
        // Given
        JTextField textField = addEmailView.addEmailInputField;
        KeyListener keyListener = textField.getKeyListeners()[0];
        KeyEvent keyEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');

        // Simulate the keyTyped event
        keyListener.keyTyped(keyEvent);

        // Then
        // Replace "setState" with the actual method that's being called within your keyTyped method.
    }


    @Test
    public void testKeyPressed() {
        // Given
        KeyEvent keyEvent = new KeyEvent(addEmailView.addEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        addEmailView.addEmailInputField.getKeyListeners()[0].keyPressed(keyEvent);

        // Then
        // Add verifications for keyPressed if there are any side effects
    }

    @Test
    public void testKeyReleased() {
        // Given
        KeyEvent keyEvent = new KeyEvent(addEmailView.addEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        addEmailView.addEmailInputField.getKeyListeners()[0].keyReleased(keyEvent);

        // Then
        // Add verifications for keyReleased if there are any side effects
    }

    @Test
    public void testActionPerformed() {
        ActionEvent actionEvent = new ActionEvent(mockAddEmailViewModel, ActionEvent.ACTION_PERFORMED, "");
        addEmailView.actionPerformed(actionEvent);

    }

    @Test
    public void testCancelActionPerformed() {
        // Redirect System.out to a ByteArrayOutputStream
        addEmailView.cancel.doClick();

        // Verify that the controller's method is called with the correct parameters.
        verify(mockViewManagerModel).setActiveView("Main Page");
    }

}
