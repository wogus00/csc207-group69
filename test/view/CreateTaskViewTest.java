package view;

import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.ViewManagerModel;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import view.CreateTaskView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

public class CreateTaskViewTest {

    @Mock
    private CreateTaskViewModel mockCreateTaskViewModel;
    @Mock
    private CreateTaskController mockCreateTaskController;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private CreateTaskView createTaskView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createTaskView = new CreateTaskView(mockViewManagerModel, mockCreateTaskController, mockCreateTaskViewModel);
        when(mockCreateTaskViewModel.getState()).thenReturn(new CreateTaskState());
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull(createTaskView.taskNameInputField);
        assertNotNull(createTaskView.supervisorInputField);
        assertNotNull(createTaskView.memberEmailsInputField);
        assertNotNull(createTaskView.deadlineInputField);
        assertNotNull(createTaskView.commentsInputField);
        assertNotNull(createTaskView.create);
        assertNotNull(createTaskView.cancel);
    }

    @Test
    public void testTaskNameInputFieldKeyListener() {
        CreateTaskState initialState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createTaskView.taskNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : createTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateTaskState expectedState = new CreateTaskState();
        expectedState.setTaskName("a");
        verify(mockCreateTaskViewModel).setState(refEq(expectedState));
    }

    // Test for Supervisor Input Field KeyListener
    @Test
    public void testSupervisorInputFieldKeyListener() {
        CreateTaskState initialState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createTaskView.supervisorInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'b');
        for (KeyListener listener : createTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateTaskState expectedState = new CreateTaskState();
        expectedState.setSupervisorName("b");
        verify(mockCreateTaskViewModel).setState(refEq(expectedState));
    }

    // Test for Create Button ActionListener
    @Test
    public void testCreateButtonActionListener() {
        CreateTaskState state = new CreateTaskState();
        state.setProjectName("Project");
        state.setTaskName("Task");
        state.setSupervisorName("Supervisor");
        state.setWorkingMembersList("Member1,Member2");
        state.setDeadline("2023-01-01");
        state.setComments("Comments");
        when(mockCreateTaskViewModel.getState()).thenReturn(state);

        ActionEvent event = new ActionEvent(createTaskView.create, ActionEvent.ACTION_PERFORMED, "");
        createTaskView.create.getActionListeners()[0].actionPerformed(event);

        verify(mockCreateTaskController).execute("Project", "Task", "Supervisor", "Member1,Member2", "2023-01-01", "Comments");
    }

    // Test for Cancel Button ActionListener
    @Test
    public void testCancelButtonActionListener() {
        ActionEvent event = new ActionEvent(createTaskView.cancel, ActionEvent.ACTION_PERFORMED, "");
        createTaskView.cancel.getActionListeners()[0].actionPerformed(event);

        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    // Test for Property Change Listener
    @Test
    public void testPropertyChangeListener() {
        // Prepare the mock state with an error message
        CreateTaskState state = new CreateTaskState();
        state.setCreateTaskError("Error message");
        PropertyChangeEvent evt = new PropertyChangeEvent(mockCreateTaskViewModel, "state", null, state);

        // Mock the static JOptionPane to capture its interactions
        try (MockedStatic<JOptionPane> mockedStatic = Mockito.mockStatic(JOptionPane.class)) {
            // Trigger the property change event
            createTaskView.propertyChange(evt);

            // Verify that JOptionPane.showMessageDialog was called with the expected error message
            mockedStatic.verify(() -> JOptionPane.showMessageDialog(eq(createTaskView), eq("Error message")));
        }
    }


    // Test for Member Emails Input Field KeyListener
    @Test
    public void testMemberEmailsInputFieldKeyListener() {
        CreateTaskState initialState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createTaskView.memberEmailsInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'c');
        for (KeyListener listener : createTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateTaskState expectedState = new CreateTaskState();
        expectedState.setWorkingMembersList("c");
        verify(mockCreateTaskViewModel).setState(refEq(expectedState));
    }

    // Test for Deadline Input Field KeyListener
    @Test
    public void testDeadlineInputFieldKeyListener() {
        CreateTaskState initialState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createTaskView.deadlineInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '1');
        for (KeyListener listener : createTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateTaskState expectedState = new CreateTaskState();
        expectedState.setDeadline("1");
        verify(mockCreateTaskViewModel).setState(refEq(expectedState));
    }

    // Test for Comments Input Field KeyListener
    @Test
    public void testCommentsInputFieldKeyListener() {
        CreateTaskState initialState = new CreateTaskState();
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createTaskView.commentsInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'd');
        for (KeyListener listener : createTaskView.commentsInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateTaskState expectedState = new CreateTaskState();
        expectedState.setComments("d");
        verify(mockCreateTaskViewModel).setState(refEq(expectedState));
    }

    // Test for PropertyChangeListener when no error
    @Test
    public void testPropertyChangeListenerNoError() {
        // Mock the static JOptionPane class
        try (MockedStatic<JOptionPane> mockedStatic = Mockito.mockStatic(JOptionPane.class)) {
            // Prepare the state with no error
            CreateTaskState state = new CreateTaskState();
            state.setCreateTaskError(null); // No error
            PropertyChangeEvent evt = new PropertyChangeEvent(mockCreateTaskViewModel, "state", null, state);

            // Trigger the property change
            createTaskView.propertyChange(evt);

            // Verify that JOptionPane.showMessageDialog was never called
            mockedStatic.verify(() -> JOptionPane.showMessageDialog(any(), anyString()), never());
        }
    }


    @Test
    public void testTaskNameInputFieldKeyListener_keyPressed() {
        // Prepare a KeyEvent
        KeyEvent keyPressedEvent = new KeyEvent(createTaskView.taskNameInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateTaskState initialState = new CreateTaskState();
        initialState.setTaskName("initialName");
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        // Trigger keyPressed event
        for (KeyListener listener : createTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyPressed(keyPressedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testTaskNameInputFieldKeyListener_keyReleased() {
        // Prepare a KeyEvent
        KeyEvent keyReleasedEvent = new KeyEvent(createTaskView.taskNameInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateTaskState initialState = new CreateTaskState();
        initialState.setTaskName("initialName");
        when(mockCreateTaskViewModel.getState()).thenReturn(initialState);

        // Trigger keyReleased event
        for (KeyListener listener : createTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyReleased(keyReleasedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testSupervisorInputFieldKeyPressed() {
        // Simulate a key press event
        KeyEvent keyPressEvent = new KeyEvent(createTaskView.supervisorInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyPressed event
        for (KeyListener listener : createTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyPressed(keyPressEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testSupervisorInputFieldKeyReleased() {
        // Simulate a key release event
        KeyEvent keyReleaseEvent = new KeyEvent(createTaskView.supervisorInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyReleased event
        for (KeyListener listener : createTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyReleased(keyReleaseEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testMemberEmailsInputFieldKeyPressed() {
        // Simulate a key press event
        KeyEvent keyPressEvent = new KeyEvent(createTaskView.memberEmailsInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyPressed event
        for (KeyListener listener : createTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyPressed(keyPressEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testMemberEmailsInputFieldKeyReleased() {
        // Simulate a key release event
        KeyEvent keyReleaseEvent = new KeyEvent(createTaskView.memberEmailsInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyReleased event
        for (KeyListener listener : createTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyReleased(keyReleaseEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testDeadlineInputFieldKeyPressed() {
        // Simulate a key press event
        KeyEvent keyPressEvent = new KeyEvent(createTaskView.deadlineInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyPressed event
        for (KeyListener listener : createTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyPressed(keyPressEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testDeadlineInputFieldKeyReleased() {
        // Simulate a key release event
        KeyEvent keyReleaseEvent = new KeyEvent(createTaskView.deadlineInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyReleased event
        for (KeyListener listener : createTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyReleased(keyReleaseEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testCommentsInputFieldKeyPressed() {
        // Simulate a key press event
        KeyEvent keyPressEvent = new KeyEvent(createTaskView.commentsInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyPressed event
        for (KeyListener listener : createTaskView.commentsInputField.getKeyListeners()) {
            listener.keyPressed(keyPressEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testCommentsInputFieldKeyReleased() {
        // Simulate a key release event
        KeyEvent keyReleaseEvent = new KeyEvent(createTaskView.commentsInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Trigger keyReleased event
        for (KeyListener listener : createTaskView.commentsInputField.getKeyListeners()) {
            listener.keyReleased(keyReleaseEvent);
        }

        // Verify that no state change occurs in the CreateTaskViewModel
        verify(mockCreateTaskViewModel, never()).setState(any(CreateTaskState.class));
    }

    @Test
    public void testActionPerformed() {
        // Create an ActionEvent
        ActionEvent actionEvent = new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "testCommand");

        // Call the actionPerformed method
        createTaskView.actionPerformed(actionEvent);

        // Assert that the method completes without exceptions
        // Since the method is empty, there are no changes or interactions to verify
        assertTrue("actionPerformed method should complete without errors.", true);
    }




    // Additional tests to follow...
}
