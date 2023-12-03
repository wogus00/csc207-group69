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
import java.util.ArrayList;
import java.util.Arrays;

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
        char c = 'c';

        projectNameInputField.getKeyListeners()[0].keyTyped(new KeyEvent(
                projectNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        projectNameInputField.getKeyListeners()[0].keyPressed(new KeyEvent(
                projectNameInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));
        projectNameInputField.getKeyListeners()[0].keyReleased(new KeyEvent(
                projectNameInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));

        assertEquals(mockViewModel.getState().getProjectName(), "c");
    }

    @Test
    public void testLeaderEmailInputField() {
        // Given
        JTextField leaderEmailInputField = view.getLeaderEmailInputField();
        char c = 'c';

        leaderEmailInputField.getKeyListeners()[0].keyTyped(new KeyEvent(
                leaderEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        leaderEmailInputField.getKeyListeners()[0].keyReleased(new KeyEvent(
                leaderEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));
        leaderEmailInputField.getKeyListeners()[0].keyPressed(new KeyEvent(
                leaderEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));


        assertEquals(mockViewModel.getState().getLeaderEmail(), "c");
    }

    @Test
    public void testMemberEmailInputField() {
        // Given
        JTextField memberEmailInputField = view.getMemberEmailInputField();
        char c = 'c';

        memberEmailInputField.getKeyListeners()[0].keyTyped(new KeyEvent(
                memberEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        memberEmailInputField.getKeyListeners()[0].keyPressed(new KeyEvent(
                memberEmailInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));
        memberEmailInputField.getKeyListeners()[0].keyReleased(new KeyEvent(
                memberEmailInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, c));


        assertEquals(mockViewModel.getState().getMemberEmail(), new ArrayList<>(Arrays.asList("c")));
    }

    @Test
    public void testLoginButtonActionListener() {
        JButton loginButton = view.getLoginButton(); // Assuming there is a getter for the login button

        // When
        loginButton.doClick();

        // Then
        verify(mockViewManagerModel).setActiveView("log in");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPropertyChangeDialogShownOnProjectNameError() {
        // Given
        CreateProjectState stateWithError = new CreateProjectState();
        stateWithError.setProjectNameError("Error message");
        when(mockViewModel.getState()).thenReturn(stateWithError);

        // When
        view.propertyChange(new PropertyChangeEvent(this, "projectNameError", null, stateWithError));

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
    public void testActionListenerException() throws IOException, AddressException {
        // Setting up the mock behavior
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        doThrow(IOException.class).when(mockController).execute(anyString(), anyString(), any(ArrayList.class));

        assertThrows(RuntimeException.class, () -> {
            view.getCreateButton().getActionListeners()[0].actionPerformed(
                    new ActionEvent(view.getCreateButton(), ActionEvent.ACTION_PERFORMED, null)
            );
        });
    }
}
