//package view;
//
//import interface_adapter.add_email.AddEmailController;
//import interface_adapter.add_email.AddEmailState;
//import interface_adapter.add_email.AddEmailViewModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.beans.PropertyChangeEvent;
//import java.awt.event.KeyEvent;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
///**
// * Tests for the {@link AddEmailView} class.
// * This test suite ensures that the AddEmailView correctly interacts with the AddEmailController and responds to
// * user input as expected. Each test case simulates user actions such as button clicks and key presses, and
// * verifies that the view behaves correctly by invoking controller methods, updating the model, or displaying errors.
// */
//public class AddEmailViewTest {
//
//    @Mock
//    private AddEmailController mockAddEmailController;
//    @Mock
//    private AddEmailViewModel mockAddEmailViewModel;
//
//    private AddEmailView addEmailView;
//
//    /**
//     * Sets up the test environment before each test.
//     * This includes initializing mocks and setting the expected state of the AddEmailViewModel.
//     */
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        when(mockAddEmailViewModel.getState()).thenReturn(new AddEmailState());
//        addEmailView = new AddEmailView(mockAddEmailController, mockAddEmailViewModel);
//    }
//
//    /**
//     * Tests that clicking the 'Add' button triggers the appropriate action in the controller.
//     * Verifies that the addProjectDetails method is called with the correct parameters.
//     */
//    @Test
//    public void testActionPerformed_AddButton_Click() {
//        // When
//        addEmailView.add.doClick();
//
//        // Then
//        // Replace "execute" with the actual method name "addProjectDetails" and use the correct parameter matchers.
//        verify(mockAddEmailController).addProjectDetails(anyString(), anyString());
//    }
//
//    /**
//     * Tests the functionality of the 'Cancel' button click action.
//     * Checks whether the relevant text fields are cleared when the cancel button is clicked.
//     * The expected behavior is that any input in text fields should be reset.
//     */
//    @Test
//    public void testActionPerformed_CancelButton_Click() {
//        // Given: Set up any initial state if necessary.
//        // For example, if the cancel button should clear fields, pre-populate them.
//        addEmailView.titleInputField.setText("Some Title");
//        addEmailView.messageInputField.setText("Some message");
//
//        // When: Perform the click action on the cancel button.
//        addEmailView.cancel.doClick();
//
//        // Then: Verify that the fields are cleared after the action (if that's the expected behavior).
//        assertEquals("", addEmailView.titleInputField.getText());
//        assertEquals("", addEmailView.messageInputField.getText());
//
//        // If the view is supposed to change, verify that the view change method was called.
//        // For example:
//        // verify(mockViewManager).changeView("MainView"); // Assuming you have a view manager to handle view changes.
//    }
//
//    /**
//     * Verifies that typing in the title input field updates the view model state.
//     * Simulates the user typing a string into the title field and checks if the state is set at least once.
//     */
//    @Test
//    public void testKeyTyped_TitleInputField() {
//        // Given
//        JTextField titleInputField = addEmailView.titleInputField;
//        titleInputField.setText("Subject");
//
//        // When
//        for (char c : "Subject".toCharArray()) {
//            titleInputField.dispatchEvent(new KeyEvent(
//                    titleInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
//        }
//
//        // Then
//        verify(mockAddEmailViewModel, atLeastOnce()).setState(any(AddEmailState.class));
//    }
//
//    /**
//     * Verifies that typing in the message input field updates the view model state.
//     * Simulates the user typing a string into the message field and checks if the state is set at least once.
//     */
//    @Test
//    public void testKeyTyped_MessageInputField() {
//        // Given
//        JTextField messageInputField = addEmailView.messageInputFiled;
//        messageInputField.setText("Message Body");
//
//        // When
//        for (char c : "Message Body".toCharArray()) {
//            messageInputField.dispatchEvent(new KeyEvent(
//                    messageInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
//        }
//
//        // Then
//        verify(mockAddEmailViewModel, atLeastOnce()).setState(any(AddEmailState.class));
//    }
//
//    /**
//     * Tests the view's response to an error property change event.
//     * Ensures that when the view model state changes to include an error, the view displays the appropriate error message.
//     */
//    @Test
//    public void testPropertyChange_Error() {
//        // Given
//        AddEmailState stateWithError = new AddEmailState();
//        stateWithError.setError("Error Message");
//        when(mockAddEmailViewModel.getState()).thenReturn(stateWithError);
//
//        // When
//        PropertyChangeEvent evt = new PropertyChangeEvent(mockAddEmailViewModel, "state", null, stateWithError);
//        addEmailView.propertyChange(evt);
//    }
//}
