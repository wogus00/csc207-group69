package view;


import interface_adapter.ViewManagerModel;
import interface_adapter.modify_task.ModifyTaskController;
import interface_adapter.modify_task.ModifyTaskState;
import interface_adapter.modify_task.ModifyTaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

public class ModifyTaskViewTest {

    @Mock
    private ModifyTaskViewModel mockModifyTaskViewModel;
    @Mock
    private ModifyTaskController mockModifyTaskController;
    @Mock
    private ModifyTaskState mockModifyTaskState;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private ModifyTaskView modifyTaskView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        modifyTaskView = new ModifyTaskView(mockViewManagerModel, mockModifyTaskController, mockModifyTaskViewModel);
        when(mockModifyTaskViewModel.getState()).thenReturn(mockModifyTaskState);
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull(modifyTaskView.taskNameInputField);
        assertNotNull(modifyTaskView.supervisorInputField);
        assertNotNull(modifyTaskView.memberEmailsInputField);
        assertNotNull(modifyTaskView.deadlineInputField);
        assertNotNull(modifyTaskView.commentsInputField);
        assertNotNull(modifyTaskView.modify);
        assertNotNull(modifyTaskView.cancel);
    }

//    @Test
//    public void testModifyButtonActionListener() {
//        // Set up the necessary state in the mock ViewModel
//        when(mockModifyTaskState.getProjectName()).thenReturn("Project Name");
//        when(mockModifyTaskState.getTaskName()).thenReturn("Task Name");
//        // Set other state fields as required
//
//        // Trigger the action listener
//        ActionEvent event = new ActionEvent(modifyTaskView.modify, ActionEvent.ACTION_PERFORMED, "");
//        modifyTaskView.modify.getActionListeners()[0].actionPerformed(event);
//
//        // Verify that the ModifyTaskController is called with the correct parameters
//        verify(mockModifyTaskController).execute(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
//    }

    @Test
    public void testTaskNameInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.taskNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : modifyTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testCancelButtonActionListener() {
        ActionEvent event = new ActionEvent(modifyTaskView.cancel, ActionEvent.ACTION_PERFORMED, "");
        modifyTaskView.cancel.getActionListeners()[0].actionPerformed(event);

        // Verify that ViewManagerModel is notified about view change
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPropertyChangeListener() {
        // Mock a state change
        when(mockModifyTaskState.getTaskNameError()).thenReturn("Error Message");
        PropertyChangeEvent evt = new PropertyChangeEvent(mockModifyTaskViewModel, "state", null, mockModifyTaskState);
        modifyTaskView.propertyChange(evt);

    }

    @Test
    public void testSupervisorInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.supervisorInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : modifyTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testMemberEmailsInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.memberEmailsInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'b');
        for (KeyListener listener : modifyTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testDeadlineInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.deadlineInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'c');
        for (KeyListener listener : modifyTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testCommentsInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.commentsInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'd');
        for (KeyListener listener : modifyTaskView.commentsInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testModifyButtonActionListener() {
        // Set up the necessary state in the mock ViewModel
        when(mockModifyTaskViewModel.getState()).thenReturn(mockModifyTaskState);
        when(mockModifyTaskState.getProjectName()).thenReturn("Project Name");
        when(mockModifyTaskState.getTaskName()).thenReturn("Task Name");
        when(mockModifyTaskState.getSupervisor()).thenReturn("supervisor@example.com");
        when(mockModifyTaskState.getWorkingMembersList()).thenReturn("member1@example.com,member2@example.com");
        when(mockModifyTaskState.getDeadline()).thenReturn("2023-12-31");
        when(mockModifyTaskState.getComments()).thenReturn("Some comments");
        when(mockModifyTaskState.getTaskNameError()).thenReturn(null);
        when(mockModifyTaskState.getWorkingMembersError()).thenReturn(null);

        // Simulate clicking the modify button
        modifyTaskView.modify.doClick();

        // Verify that the controller's execute method was called with the right parameters
        verify(mockModifyTaskController).execute("Project Name", "Task Name", "supervisor@example.com", "member1@example.com,member2@example.com", "2023-12-31", "Some comments");

        // Verify that the ViewModel's state was updated as expected
        verify(mockModifyTaskViewModel, times(2)).getState(); // Called twice: before and after the execute method

        // Verify that the view changes to "Main Page" and fires property change
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testTaskNameInputFieldKeyPressed() {
        // Create a KeyEvent for the keyPressed event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.taskNameInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyPressed event on all KeyListeners of taskNameInputField
        for (KeyListener listener : modifyTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testTaskNameInputFieldKeyReleased() {
        // Create a KeyEvent for the keyReleased event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.taskNameInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyReleased event on all KeyListeners of taskNameInputField
        for (KeyListener listener : modifyTaskView.taskNameInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testSupervisorInputFieldKeyPressed() {
        // Create a KeyEvent for the keyPressed event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.supervisorInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyPressed event on all KeyListeners of supervisorInputField
        for (KeyListener listener : modifyTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testSupervisorInputFieldKeyReleased() {
        // Create a KeyEvent for the keyReleased event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.supervisorInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyReleased event on all KeyListeners of supervisorInputField
        for (KeyListener listener : modifyTaskView.supervisorInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testMemberEmailsInputFieldKeyPressed() {
        // Create a KeyEvent for the keyPressed event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.memberEmailsInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyPressed event on all KeyListeners of memberEmailsInputField
        for (KeyListener listener : modifyTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testMemberEmailsInputFieldKeyReleased() {
        // Create a KeyEvent for the keyReleased event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.memberEmailsInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyReleased event on all KeyListeners of memberEmailsInputField
        for (KeyListener listener : modifyTaskView.memberEmailsInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testDeadlineInputFieldKeyPressed() {
        // Create a KeyEvent for the keyPressed event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.deadlineInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyPressed event on all KeyListeners of deadlineInputField
        for (KeyListener listener : modifyTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testDeadlineInputFieldKeyReleased() {
        // Create a KeyEvent for the keyReleased event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.deadlineInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyReleased event on all KeyListeners of deadlineInputField
        for (KeyListener listener : modifyTaskView.deadlineInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testCommentsInputFieldKeyPressed() {
        // Create a KeyEvent for the keyPressed event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.commentsInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyPressed event on all KeyListeners of commentsInputField
        for (KeyListener listener : modifyTaskView.commentsInputField.getKeyListeners()) {
            listener.keyPressed(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }


    @Test
    public void testCommentsInputFieldKeyReleased() {
        // Create a KeyEvent for the keyReleased event
        KeyEvent keyEvent = new KeyEvent(modifyTaskView.commentsInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');

        // Trigger the keyReleased event on all KeyListeners of commentsInputField
        for (KeyListener listener : modifyTaskView.commentsInputField.getKeyListeners()) {
            listener.keyReleased(keyEvent);
        }

        // Verify that no state change occurs in ModifyTaskViewModel
        verify(mockModifyTaskViewModel, never()).setState(any(ModifyTaskState.class));
    }

    @Test
    public void testActionPerformed() {
        // Create an ActionEvent
        ActionEvent actionEvent = new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "command");

        // Call actionPerformed method
        modifyTaskView.actionPerformed(actionEvent);

    }


}
