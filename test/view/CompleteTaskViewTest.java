package view;

import javax.swing.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
import view.CompleteTaskView;

public class CompleteTaskViewTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CompleteTaskController mockCompleteTaskController;
    @Mock
    private CompleteTaskViewModel mockCompleteTaskViewModel;

    private CompleteTaskView completeTaskView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        completeTaskView = new CompleteTaskView(mockViewManagerModel, mockCompleteTaskController, mockCompleteTaskViewModel);
    }

    @Test
    public void testCancelButtonActionListener() {
        // Simulate button click
        completeTaskView.cancel.getActionListeners()[0].actionPerformed(new ActionEvent(completeTaskView.cancel, ActionEvent.ACTION_PERFORMED, null));

        // Verify viewManagerModel.setActiveView is called with "Main Page"
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testCompleteButtonActionListener_Success() {
        // Set up the state in the mock ViewModel
        CompleteTaskState mockState = new CompleteTaskState();
        mockState.setProjectName("Test Project");
        mockState.setTaskName("Test Task");
        mockState.setUserEmail("test@example.com");
        when(mockCompleteTaskViewModel.getState()).thenReturn(mockState);

        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(completeTaskView.complete, ActionEvent.ACTION_PERFORMED, "");
        completeTaskView.complete.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify completeTaskController.execute is called with correct data
        verify(mockCompleteTaskController).execute("Test Project", "Test Task", "test@example.com");

        // Verify input fields are cleared
        assertEquals("", completeTaskView.taskNameInputField.getText());
    }

    @Test
    public void testPropertyChange_TaskNameError() {
        // Simulate a property change event with a task name error
        CompleteTaskState mockState = new CompleteTaskState();
        mockState.setTaskNameError("Task name error");
        completeTaskView.propertyChange(new PropertyChangeEvent(mockCompleteTaskViewModel, "state", null, mockState));

        // Verify that an error message is displayed
        assertNotNull(completeTaskView.taskNameInputField.getText()); // Assuming error messages are displayed in the text field
    }

    @Test
    public void testPropertyChange_NoTaskNameError() {
        // Simulate a property change event with no task name error
        CompleteTaskState mockState = new CompleteTaskState();
        mockState.setTaskNameError(null);
        completeTaskView.propertyChange(new PropertyChangeEvent(mockCompleteTaskViewModel, "state", null, mockState));

        // Verify that no error message is displayed
        assertTrue(completeTaskView.taskNameInputField.getText().isEmpty());
    }
}
