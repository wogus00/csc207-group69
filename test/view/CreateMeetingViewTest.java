package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
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

public class CreateMeetingViewTest {

    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private CreateMeetingController mockCreateMeetingController;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private CreateMeetingView createMeetingView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createMeetingView = new CreateMeetingView(mockViewManagerModel, mockCreateMeetingController, mockCreateMeetingViewModel);
    }

    @Test
    public void testComponentTypes() {
        assertNotNull(createMeetingView.meetingNameInputField);
        assertNotNull(createMeetingView.participantEmailInputField);
        assertNotNull(createMeetingView.create);
        assertNotNull(createMeetingView.cancel);
    }

    @Test
    public void testPropertyChangeListener_meetingNameError() {
        // Set up a state in the mock ViewModel
        CreateMeetingState mockState = new CreateMeetingState();
        mockState.setMeetingNameError("Error Message");
        when(mockCreateMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockCreateMeetingViewModel, "state", null, mockState);
        createMeetingView.propertyChange(evt);

        // Add assertions if needed based on your implementation
    }

    @Test
    public void testCancelActionPerformed() {
        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(createMeetingView.cancel, ActionEvent.ACTION_PERFORMED, "");
        createMeetingView.cancel.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify viewManagerModel.setActiveView is called with "Main Page"
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testCreateActionPerformed_Success() throws ExecutionException, InterruptedException {
        // Set up a state in the mock ViewModel
        CreateMeetingState mockState = new CreateMeetingState();
        when(mockCreateMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(createMeetingView.create, ActionEvent.ACTION_PERFORMED, "");
        createMeetingView.create.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify that createMeetingController.execute is called with correct data
        verify(mockCreateMeetingController).execute(anyString(), any(ArrayList.class), anyString(), anyString(), anyString(), anyString());

        // Simulate successful execution (no errors)
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Simulate property change event to trigger success message
        PropertyChangeEvent evt = new PropertyChangeEvent(mockCreateMeetingViewModel, "state", null, new CreateMeetingState());
        createMeetingView.propertyChange(evt);

        // Verify that viewManagerModel.setActiveView is called with "Main Page"
        verify(mockViewManagerModel).setActiveView("Main Page");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    public void testCreateMeetingView_keyTyped_events() {
        // Setup: Mock the necessary components and set initial conditions
        CreateMeetingState mockState = mock(CreateMeetingState.class);
        when(mockCreateMeetingViewModel.getState()).thenReturn(mockState);

        // Simulate keyTyped events for each input field
        simulateKeyTyped(createMeetingView.meetingNameInputField, 'e');
        simulateKeyTyped(createMeetingView.participantEmailInputField, 'l');
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
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(createMeetingView.participantEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        createMeetingView.participantEmailInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockCreateMeetingViewModel).setState(any(CreateMeetingState.class));
    }

    @Test
    public void testMeetingDateInputField_keyTyped() {
        // Arrange
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(createMeetingView.meetingDateInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        createMeetingView.meetingDateInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockCreateMeetingViewModel).setState(any(CreateMeetingState.class));
    }

    @Test
    public void testStartTimeInputField_keyTyped() {
        // Arrange
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(createMeetingView.startTimeInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        createMeetingView.startTimeInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockCreateMeetingViewModel).setState(any(CreateMeetingState.class));
    }

    @Test
    public void testEndTimeInputField_keyTyped() {
        // Arrange
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        KeyEvent keyEvent = new KeyEvent(createMeetingView.endTimeInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        createMeetingView.endTimeInputField.getKeyListeners()[0].keyTyped(keyEvent);

        // Assert
        verify(mockCreateMeetingViewModel).setState(any(CreateMeetingState.class));
    }
}