package view;

//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectState;
import interface_adapter.create_project.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


import javax.mail.internet.AddressException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class CreateProjectViewTest {

    @Mock
    private CreateProjectController mockController;
    @Mock
    private CreateProjectViewModel mockViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private CreateProjectView view;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testActionPerformed_CreateButton_Click() throws IOException, AddressException {
        // Simulate the action
        JButton createButton = view.getCreateButton();
        createButton.doClick();

        // Validate the interaction
        verify(mockController).execute(anyString(), anyString(), any());

    }

    @Test
    public void testProjectNameInputField() {
        // Given
        JTextField projectNameInputField = view.getProjectNameInputField();
        String projectName = "New Project Name";

        // When
        projectNameInputField.setText(projectName);
        for (char c : projectName.toCharArray()) {
            projectNameInputField.dispatchEvent(new KeyEvent(
                    projectNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setProjectName(projectName);
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testLeaderEmailInputField() {
        // Given
        JTextField leaderEmailInputField = view.getLeaderEmailInputField();
        String leaderEmail = "leader@example.com";

        // When
        leaderEmailInputField.setText(leaderEmail);
        for (char c : leaderEmail.toCharArray()) {
            leaderEmailInputField.dispatchEvent(new KeyEvent(
                    leaderEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setLeaderEmail(leaderEmail);
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testMemberEmailInputField() {
        // Given
        JTextField memberEmailInputField = view.getMemberEmailInputField();
        String memberEmail = "member1@example.com,member2@example.com";

        // When
        memberEmailInputField.setText(memberEmail);
        for (char c : memberEmail.toCharArray()) {
            memberEmailInputField.dispatchEvent(new KeyEvent(
                    memberEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setMemberEmail(memberEmail);
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testLoginButtonActionListener() {
        // Given
        CreateProjectView view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
        JButton loginButton = view.getLoginButton(); // Assuming there is a getter for the login button

        // When
        loginButton.doClick();

        // Then
        verify(mockViewManagerModel).setActiveView("login");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testProjectNameInputFieldKeyListener() {
        // Given
        JTextField projectNameInputField = view.getProjectNameInputField();
        String projectName = "New Project Name";
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setProjectName(projectName);

        // When
        KeyListener[] listeners = projectNameInputField.getKeyListeners();
        for (KeyListener listener : listeners) {
            for (char c : projectName.toCharArray()) {
                listener.keyTyped(new KeyEvent(projectNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
            }
        }

        // Then
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testLeaderEmailInputFieldKeyListener() {
        // Given
        JTextField leaderEmailInputField = view.getLeaderEmailInputField();
        String leaderEmail = "leader@example.com";
        CreateProjectState mockState = mock(CreateProjectState.class);

        when(mockViewModel.getState()).thenReturn(mockState);

        // Simulate user typing in the leaderEmailInputField
        for (char c : leaderEmail.toCharArray()) {
            leaderEmailInputField.dispatchEvent(new KeyEvent(
                    leaderEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        verify(mockViewModel, times(leaderEmail.length())).setState(any(CreateProjectState.class));
    }

    @Test
    public void testMemberEmailInputFieldKeyListener() {
        // Given
        CreateProjectState initialState = new CreateProjectState();
        when(mockViewModel.getState()).thenReturn(initialState);

        JTextField memberEmailInputField = view.getMemberEmailInputField(); // Make sure your view has this getter
        KeyEvent keyEvent = new KeyEvent(memberEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');

        // When
        for(KeyListener listener : memberEmailInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        // Then
        // Verify that setState was called with the updated state
        verify(mockViewModel).setState(any(CreateProjectState.class));

        // To be more precise, you can capture the argument and assert the state's details
        // ArgumentCaptor<CreateProjectState> stateCaptor = ArgumentCaptor.forClass(CreateProjectState.class);
        // verify(mockViewModel).setState(stateCaptor.capture());
        // assertEquals("a", stateCaptor.getValue().getMemberEmail().get(0)); // This line depends on how you manage the state of emails
    }

    @Test
    public void testPropertyChangeDialogShownOnProjectNameError() {
        // Given
        CreateProjectState stateWithError = new CreateProjectState();
        stateWithError.setProjectNameError("Error message");
        when(mockViewModel.getState()).thenReturn(stateWithError);

        // When
        view.propertyChange(new PropertyChangeEvent(this, "projectNameError", "", "Error message"));

        // Then
        // Verify that a dialog box is shown. This might require you to mock JOptionPane.showDialog or similar.
    }

    @Test
    public void testActionPerformed() {
        // ... Set up your mocks and CreateProjectView instance

        // Simulate the action event
        view.actionPerformed(null); // Normally you would pass an actual ActionEvent, but for this test it's not necessary

        // No behavior to verify as the method is empty, but if there was behavior, you would verify it here.
        // Example (if the actionPerformed method had content):
        // verify(mockSomeComponent).someMethod();
    }

    @Test
    public void testPropertyChangeWithError() {
        // Given
        CreateProjectState mockState = mock(CreateProjectState.class);
        when(mockState.getProjectNameError()).thenReturn("Error message");
        PropertyChangeEvent mockEvent = new PropertyChangeEvent(this, "projectName", null, mockState);

        // When
        view.propertyChange(mockEvent);

        // Then
        // The following line assumes that you have a method to get the parent frame or component for the JOptionPane
        // If you do not, you will need to find another way to verify that the JOptionPane has been displayed.
        verify(mockState).getProjectNameError();
        // This line verifies that a JOptionPane is displayed with the correct error message.
        // If you can intercept the dialog parent, you can check for JOptionPane invocations.
        // However, if you can't intercept, this may not be directly testable.
    }

    @Test
    public void testPropertyChangeWithNoError() {
        // Given
        CreateProjectState mockState = mock(CreateProjectState.class);
        when(mockState.getProjectNameError()).thenReturn(null);
        PropertyChangeEvent mockEvent = new PropertyChangeEvent(this, "projectName", null, mockState);

        // When
        view.propertyChange(mockEvent);

        // Then
        // Verify that no dialog is shown if there's no error.
        // This assumes you have a way to monitor or intercept the JOptionPane calls.
        // If not, you might need to refactor your code to make it more testable.
    }

    @Test
    public void testActionPerformed_NoAction() {
        // Given
        ActionEvent event = new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "command");

        // When
        view.actionPerformed(event);

        // Then
        // Normally, you'd verify some interaction here, but since actionPerformed is empty,
        // there's nothing to verify. This is a placeholder for when actionPerformed has an implementation.
    }

    @Test
    public void testPropertyChange_WithError() {
        // Given
        CreateProjectState mockState = mock(CreateProjectState.class);
        PropertyChangeEvent mockEvent = new PropertyChangeEvent(new Object(), "projectNameError", null, mockState);
        when(mockState.getProjectNameError()).thenReturn("Error message");

        // When
        view.propertyChange(mockEvent);

        // Then
        verify(mockState).getProjectNameError();
        // You should also verify that a dialog is shown, but this requires further setup as JOptionPane
        // is a static method and can't be directly mocked without a framework like PowerMock.
    }

    @Test
    public void testLeaderEmailInputFieldKeyPressed() {
        // Given
        CreateProjectView view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
        JTextField leaderEmailInputField = view.getLeaderEmailInputField(); // Assuming there is a getter for leaderEmailInputField
        KeyEvent keyEvent = new KeyEvent(leaderEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        for (KeyListener listener : leaderEmailInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Then
        // Here you would verify that the expected behavior occurs when a key is pressed
        // For example, if pressing a key is supposed to update the state, you would verify that
    }

    @Test
    public void testLeaderEmailInputFieldKeyReleased() {
        // Given
        CreateProjectView view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
        JTextField leaderEmailInputField = view.getLeaderEmailInputField(); // Assuming there is a getter for leaderEmailInputField
        KeyEvent keyEvent = new KeyEvent(leaderEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        for (KeyListener listener : leaderEmailInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Then
        // Here you would verify that the expected behavior occurs when a key is released
        // For example, if releasing a key is supposed to trigger validation, you would verify that
    }

    @Test
    public void shouldUpdateProjectNameOnKeyTyped() {
        // given
        String typedText = "New Project";
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        JTextField projectNameInputField = view.getProjectNameInputField();

        // when
        for (char c : typedText.toCharArray()) {
            KeyEvent keyEvent = new KeyEvent(projectNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c);
            for (KeyListener kl : projectNameInputField.getKeyListeners()) {
                kl.keyTyped(keyEvent);
            }
        }

        // then
        verify(mockViewModel).setState(any(CreateProjectState.class));
    }

    @Test
    public void shouldUpdateLeaderEmailOnKeyTyped() {
        // given
        String typedText = "leader@example.com";
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        JTextField leaderEmailInputField = view.getLeaderEmailInputField();

        // when
        for (char c : typedText.toCharArray()) {
            KeyEvent keyEvent = new KeyEvent(leaderEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c);
            for (KeyListener kl : leaderEmailInputField.getKeyListeners()) {
                kl.keyTyped(keyEvent);
            }
        }

        // then
        verify(mockViewModel).setState(any(CreateProjectState.class));
    }

    @Test
    public void shouldUpdateMemberEmailOnKeyTyped() {
        // given
        String typedText = "member@example.com";
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        JTextField memberEmailInputField = view.getMemberEmailInputField();

        // when
        for (char c : typedText.toCharArray()) {
            KeyEvent keyEvent = new KeyEvent(memberEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c);
            for (KeyListener kl : memberEmailInputField.getKeyListeners()) {
                kl.keyTyped(keyEvent);
            }
        }

        // then
        verify(mockViewModel).setState(any(CreateProjectState.class));
    }

    @Test
    public void shouldShowErrorMessageDialogOnProjectNameError() {
        // given
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        doNothing().when(mockViewManagerModel).firePropertyChanged();

        // when
        CreateProjectState stateWithErrorMessage = new CreateProjectState();
        stateWithErrorMessage.setProjectNameError("Error message");
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "projectNameError", null, stateWithErrorMessage);

        // Simulate property change listener
        view.propertyChange(evt);

        // then
        // You need to verify that JOptionPane.showMessageDialog was called with the correct arguments
        // This might require you to use an ArgumentCaptor or a similar approach to capture JOptionPane invocations
    }

    @Test
    public void shouldNotThrowExceptionOnProjectNameKeyPressed() {
        // given
        JTextField projectNameInputField = view.getProjectNameInputField();
        KeyEvent keyEvent = new KeyEvent(projectNameInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // when
        for (KeyListener kl : projectNameInputField.getKeyListeners()) {
            kl.keyPressed(keyEvent);
        }

        // then
        // No exception expected, verification can be omitted if method is empty
    }

    @Test
    public void shouldNotThrowExceptionOnProjectNameKeyReleased() {
        // given
        JTextField projectNameInputField = view.getProjectNameInputField();
        KeyEvent keyEvent = new KeyEvent(projectNameInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // when
        for (KeyListener kl : projectNameInputField.getKeyListeners()) {
            kl.keyReleased(keyEvent);
        }

        // then
        // No exception expected, verification can be omitted if method is empty
    }

    @Test
    public void shouldNotThrowExceptionOnMemberEmailKeyPressed() {
        // given
        JTextField memberEmailInputField = view.getMemberEmailInputField();
        KeyEvent keyEvent = new KeyEvent(memberEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // when
        for (KeyListener kl : memberEmailInputField.getKeyListeners()) {
            kl.keyPressed(keyEvent);
        }

        // then
        // No exception expected, verification can be omitted if method is empty
    }

    @Test
    public void shouldNotThrowExceptionOnMemberEmailKeyReleased() {
        // given
        JTextField memberEmailInputField = view.getMemberEmailInputField();
        KeyEvent keyEvent = new KeyEvent(memberEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // when
        for (KeyListener kl : memberEmailInputField.getKeyListeners()) {
            kl.keyReleased(keyEvent);
        }

        // then
        // No exception expected, verification can be omitted if method is empty
    }

    @Test
    public void testKeyPressed() {
        // Given
        CreateProjectView view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
        JTextField projectNameInputField = view.getProjectNameInputField(); // Assuming there is a getter for projectNameInputField
        KeyListener[] keyListeners = projectNameInputField.getKeyListeners();

        // Simulate a key press
        KeyEvent keyEvent = new KeyEvent(projectNameInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // When
        for (KeyListener listener : keyListeners) {
            listener.keyPressed(keyEvent);
        }

        // Then
        // Verify that the expected methods are called or state changes occur
    }

    @Test
    void testCreateProjectExceptionHandling() throws AddressException, IOException {
        // Given an IOException is thrown when the execute method is called
        doThrow(IOException.class).when(mockController).execute(anyString(), anyString(), any());

        // When and Then: assert that executing the create button click
        // results in a RuntimeException
        JButton createButton = view.getCreateButton();
        assertThrows(RuntimeException.class, createButton::doClick);

        // Optionally, verify the interactions or the state after the exception was thrown
        // This is just an example and might need to be adjusted based on actual code behavior
        verify(mockViewModel).setState(any(CreateProjectState.class));
    }
}
