package view;

import interface_adapter.create_announcement.CreateAnnouncementController;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.mail.internet.AddressException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CreateAnnouncementViewTest {

    @Mock
    private CreateAnnouncementViewModel mockCreateAnnouncementViewModel;
    @Mock
    private CreateAnnouncementController mockCreateAnnouncementController;
    @Mock
    private CreateAnnouncementState mockCreateAnnouncementState;

    private CreateAnnouncementView createAnnouncementView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createAnnouncementView = new CreateAnnouncementView(mockCreateAnnouncementController, mockCreateAnnouncementViewModel);
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(mockCreateAnnouncementState);
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull(createAnnouncementView.titleInputField);
        assertNotNull(createAnnouncementView.messageInputFiled);
        assertNotNull(createAnnouncementView.announcementSent);
        assertNotNull(createAnnouncementView.cancel);
    }

    // Test for Announcement Title Input KeyListener
    @Test
    public void testTitleInputFieldKeyListener() {
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        KeyEvent keyEvent = new KeyEvent(createAnnouncementView.titleInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : createAnnouncementView.titleInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        CreateAnnouncementState expectedState = new CreateAnnouncementState(initialState);
        expectedState.setAnnouncementTitle("a"); // Append 'a' to the initial title
        verify(mockCreateAnnouncementViewModel).setState(refEq(expectedState));
    }



    // Test for Announcement Message Input KeyListener
    @Test
    public void testMessageInputFieldKeyListener() {
        // Mock initial state
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        initialState.setMessage(""); // Assuming the initial message is empty
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        // Simulate key press
        KeyEvent keyEvent = new KeyEvent(createAnnouncementView.messageInputFiled, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : createAnnouncementView.messageInputFiled.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }

        // Manually create the expected state
        CreateAnnouncementState expectedState = new CreateAnnouncementState();
        expectedState.setMessage("a");

        // Verify that setState was called with the expected state
        verify(mockCreateAnnouncementViewModel).setState(refEq(expectedState));
    }




    // Test for Announcement Sent Button ActionListener
    @Test
    public void testAnnouncementSentButtonActionListener() throws AddressException, IOException {
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(new CreateAnnouncementState());
        CreateAnnouncementState expectedState = new CreateAnnouncementState();
        expectedState.setAnnouncementTitle("Test Title");
        expectedState.setMessage("Test Message");
        expectedState.setAuthor("Test Author");

        when(mockCreateAnnouncementViewModel.getState()).thenReturn(expectedState);

        ActionEvent event = new ActionEvent(createAnnouncementView.announcementSent, ActionEvent.ACTION_PERFORMED, "");
        createAnnouncementView.announcementSent.getActionListeners()[0].actionPerformed(event);
        verify(mockCreateAnnouncementController).execute("Test Title", "Test Message", "Test Author");
    }

    @Test
    public void testMessageInputFieldKeyListener_keyPressed() {
        // Prepare a KeyEvent
        KeyEvent keyPressedEvent = new KeyEvent(createAnnouncementView.messageInputFiled, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        initialState.setMessage("initial");
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        // Trigger keyPressed event
        for (KeyListener listener : createAnnouncementView.messageInputFiled.getKeyListeners()) {
            listener.keyPressed(keyPressedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateAnnouncementViewModel, never()).setState(any(CreateAnnouncementState.class));
    }

    @Test
    public void testMessageInputFieldKeyListener_keyReleased() {
        // Prepare a KeyEvent
        KeyEvent keyReleasedEvent = new KeyEvent(createAnnouncementView.messageInputFiled, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        initialState.setMessage("initial");
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        // Trigger keyReleased event
        for (KeyListener listener : createAnnouncementView.messageInputFiled.getKeyListeners()) {
            listener.keyReleased(keyReleasedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateAnnouncementViewModel, never()).setState(any(CreateAnnouncementState.class));
    }




    // Test for Cancel Button ActionListener
    @Test
    public void testCancelButtonActionListener() {
        ActionEvent event = new ActionEvent(createAnnouncementView.cancel, ActionEvent.ACTION_PERFORMED, "");
        createAnnouncementView.cancel.getActionListeners()[0].actionPerformed(event);
        // Add assertions based on the expected behavior of the cancel button
    }

    // Test for Property Change Listener
    @Test
    public void testPropertyChangeListener() {
        CreateAnnouncementState state = new CreateAnnouncementState();
        state.setAnnouncementTitleError("Error message");
        PropertyChangeEvent evt = new PropertyChangeEvent(mockCreateAnnouncementViewModel, "state", null, state);
        createAnnouncementView.propertyChange(evt);
        // Assertions based on how the view should respond to the change
    }

    @Test
    public void testTitleInputFieldKeyListener_keyPressed() {
        // Prepare a KeyEvent
        KeyEvent keyPressedEvent = new KeyEvent(createAnnouncementView.titleInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        initialState.setAnnouncementTitle("initialTitle");
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        // Trigger keyPressed event
        for (KeyListener listener : createAnnouncementView.titleInputField.getKeyListeners()) {
            listener.keyPressed(keyPressedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateAnnouncementViewModel, never()).setState(any(CreateAnnouncementState.class));
    }

    @Test
    public void testTitleInputFieldKeyListener_keyReleased() {
        // Prepare a KeyEvent
        KeyEvent keyReleasedEvent = new KeyEvent(createAnnouncementView.titleInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');

        // Capture the initial state before the event
        CreateAnnouncementState initialState = new CreateAnnouncementState();
        initialState.setAnnouncementTitle("initialTitle");
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(initialState);

        // Trigger keyReleased event
        for (KeyListener listener : createAnnouncementView.titleInputField.getKeyListeners()) {
            listener.keyReleased(keyReleasedEvent);
        }

        // Verify that no state change occurs
        verify(mockCreateAnnouncementViewModel, never()).setState(any(CreateAnnouncementState.class));
    }


}

