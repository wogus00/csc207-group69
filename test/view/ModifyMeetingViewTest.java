package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.modify_meeting.ModifyMeetingController;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ModifyMeetingViewTest {

    @Mock
    private ModifyMeetingViewModel mockModifyMeetingViewModel;
    @Mock
    private ModifyMeetingController mockModifyMeetingController;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private ModifyMeetingView modifyMeetingView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        modifyMeetingView = new ModifyMeetingView(mockViewManagerModel, mockModifyMeetingController, mockModifyMeetingViewModel);
    }

    @Test
    public void testComponentTypes() {
        assertNotNull(modifyMeetingView.meetingNameInputField);
        assertNotNull(modifyMeetingView.participantEmailInputField);
        assertNotNull(modifyMeetingView.modify);
        assertNotNull(modifyMeetingView.cancel);
    }

    @Test
    public void testPropertyChangeListener_meetingNameError() {
        // Set up a state in the mock ViewModel
        ModifyMeetingState mockState = new ModifyMeetingState();
        mockState.setMeetingNameError("Error Message");
        when(mockModifyMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockModifyMeetingViewModel, "state", null, mockState);
        modifyMeetingView.propertyChange(evt);

        // Add assertions if needed based on your implementation
    }

    @Test
    public void testCancelActionPerformed() {
        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(modifyMeetingView.cancel, ActionEvent.ACTION_PERFORMED, "");
        modifyMeetingView.cancel.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify viewManagerModel.setActiveView is called with "Main Page"
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testModifyActionPerformed_Success() throws ExecutionException, InterruptedException {
        // Set up a state in the mock ViewModel
        ModifyMeetingState mockState = new ModifyMeetingState();
        when(mockModifyMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(modifyMeetingView.modify, ActionEvent.ACTION_PERFORMED, "");
        modifyMeetingView.modify.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify that modifyMeetingController.execute is called with correct data
        verify(mockModifyMeetingController).execute(anyString(), any(ArrayList.class), anyString(), anyString(), anyString(), anyString());

        // Simulate successful execution (no errors)
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());

        // Simulate property change event to trigger success message
        PropertyChangeEvent evt = new PropertyChangeEvent(mockModifyMeetingViewModel, "state", null, new ModifyMeetingState());
        modifyMeetingView.propertyChange(evt);

        // Verify that viewManagerModel.setActiveView is called with "Main Page"
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testModifyMeetingView_keyTyped_events() {
        // Setup: Mock the necessary components and set initial conditions
        ModifyMeetingState mockState = mock(ModifyMeetingState.class);
        when(mockModifyMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate keyTyped events for each input field
        simulateKeyTyped(modifyMeetingView.meetingNameInputField, 'e');
        simulateKeyTyped(modifyMeetingView.participantEmailInputField, 'l');
        // ... (similarly for other input fields)

        // Verify that the corresponding state-setting methods are called
        verify(mockState, times(1)).setMeetingName("e");
        verify(mockState, times(1)).setParticipantEmail("l");
        // ... (similarly for other state-setting methods)
    }

    private void simulateKeyTyped(JTextField textField, char keyChar) {
        KeyEvent keyEvent = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, keyChar);
        for (KeyListener listener : textField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
    }

    @Test
    public void testParticipantEmailInputField_keyTyped() {
        // Arrange
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(modifyMeetingView.participantEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        modifyMeetingView.participantEmailInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockModifyMeetingViewModel).setState(any(ModifyMeetingState.class));
    }

    @Test
    public void testMeetingDateInputField_keyTyped() {
        // Arrange
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(modifyMeetingView.meetingDateInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        modifyMeetingView.meetingDateInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockModifyMeetingViewModel).setState(any(ModifyMeetingState.class));
    }

    @Test
    public void testStartTimeInputField_keyTyped() {
        // Arrange
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(modifyMeetingView.startTimeInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        modifyMeetingView.startTimeInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockModifyMeetingViewModel).setState(any(ModifyMeetingState.class));
    }

    @Test
    public void testEndTimeInputField_keyTyped() {
        // Arrange
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(modifyMeetingView.endTimeInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        modifyMeetingView.endTimeInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockModifyMeetingViewModel).setState(any(ModifyMeetingState.class));
    }
}