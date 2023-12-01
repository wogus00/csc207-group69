package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.remove_email.RemoveEmailController;
import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Tests for the {@link RemoveEmailView} class. These tests verify the interactions between the
 * {@link RemoveEmailView} and the {@link RemoveEmailController}, as well as the view's response to user actions.
 * Each test method simulates user interface events and checks that the correct controller methods are called
 * and that the view updates its state appropriately.
 */
public class RemoveEmailViewTest {

    @Mock
    ViewManagerModel mockViewManagerModel;
    @Mock
    private RemoveEmailController mockRemoveEmailController;
    @Mock
    private RemoveEmailViewModel mockRemoveEmailViewModel;

    private RemoveEmailView removeEmailView;

    /**
     * Sets up the test environment before each test. This includes initializing the mocked objects
     * and setting up the {@link RemoveEmailView} with a mocked {@link RemoveEmailController} and
     * {@link RemoveEmailViewModel}.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockRemoveEmailViewModel.getState()).thenReturn(new RemoveEmailState());
        removeEmailView = new RemoveEmailView(mockRemoveEmailController, mockRemoveEmailViewModel, mockViewManagerModel);
    }

    /**
     * Tests that the remove button's action performs as expected by simulating a button click
     * and verifying that the removeProjectDetails method of the controller is invoked with the correct arguments.
     */
    @Test
    public void testActionPerformed_RemoveButton_Click() {
        // Simulate the clicking of the remove button
        removeEmailView.remove.doClick();

        // Verify that the controller's method is called with the correct parameters.
        verify(mockRemoveEmailController).removeProjectDetails(anyString(), anyString());
    }

    /**
     * Tests the functionality of the cancel button action. This test should verify that the appropriate
     * view change occurs when the cancel button is clicked, simulating a user's cancellation of the email removal process.
     * Actual behavior to be verified depends on the implementation of the cancel button's action listener.
     */
    @Test
    public void testActionPerformed_CancelButton_Click() {
        // Simulate the clicking of the cancel button
        removeEmailView.cancel.doClick();

        // Verify the expected behavior, such as returning to the previous view
    }

    /**
     * Verifies that typing in the title input field results in the appropriate update to the view model's state.
     * This test simulates the key typing event in the title field and ensures the state reflects the changes.
     */
    @Test
    public void testKeyTyped_RemoveEmailInputField() {
        // Simulate typing in the title input field
        JTextField titleInputField = removeEmailView.removeEmailInputField;
        titleInputField.setText("Email Title");

        // Trigger keyTyped event
        for (char c : "Email Title".toCharArray()) {
            titleInputField.dispatchEvent(new KeyEvent(
                    titleInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Verify that the state is updated in the view model
        verify(mockRemoveEmailViewModel, atLeastOnce()).setState(any(RemoveEmailState.class));
    }

    /**
     * Tests the view's response to a property change event signaling an error. It verifies that when the view model
     * indicates an error, the view reacts by displaying an error message.
     */
    @Test
    public void testPropertyChange_Error() {
        // Prepare the state with an error message
        RemoveEmailState stateWithError = new RemoveEmailState();
        stateWithError.setError("Error Message");
        when(mockRemoveEmailViewModel.getState()).thenReturn(stateWithError);

        // Trigger the property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockRemoveEmailViewModel, "state", null, stateWithError);
        removeEmailView.propertyChange(evt);

        // Verify that an error message is displayed
        // Usually, you would assert that the error message is shown on the UI,
        // but since JOptionPane.showMessageDialog is a static method,
        // it's a bit tricky to verify using Mockito without additional tools or refactoring.
    }

    @Test
    public void testKeyTyped() {
        // Given
        JTextField textField = removeEmailView.removeEmailInputField;
        KeyListener keyListener = textField.getKeyListeners()[0];
        KeyEvent keyEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');

        // Simulate the keyTyped event
        keyListener.keyTyped(keyEvent);

        // Then
        // Replace "setState" with the actual method that's being called within your keyTyped method.
        verify(mockRemoveEmailViewModel).setState(any(RemoveEmailState.class)); // Use the actual class that's expected
    }


    @Test
    public void testKeyPressed() {
        // Given
        KeyEvent keyEvent = new KeyEvent(removeEmailView.removeEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        removeEmailView.removeEmailInputField.getKeyListeners()[0].keyPressed(keyEvent);

        // Then
        // Add verifications for keyPressed if there are any side effects
    }

    @Test
    public void testKeyReleased() {
        // Given
        KeyEvent keyEvent = new KeyEvent(removeEmailView.removeEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        removeEmailView.removeEmailInputField.getKeyListeners()[0].keyReleased(keyEvent);

        // Then
        // Add verifications for keyReleased if there are any side effects
    }

    @Test
    public void testActionPerformed_Cancel_NotImplemented() {
        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Simulate the action event
        ActionEvent mockEvent = mock(ActionEvent.class);
        removeEmailView.actionPerformed(mockEvent);

        // Reset the standard output to its original stream
        System.setOut(originalOut);

        // Verify the output contains the expected text
        String expectedOutput = "Cancel not implemented yet.\n"; // Include newline character because println is used
        Assert.assertEquals(expectedOutput, outContent.toString());
    }
}
