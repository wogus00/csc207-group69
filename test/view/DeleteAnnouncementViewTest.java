package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete_announcement.DeleteAnnouncementController;
import interface_adapter.delete_announcement.DeleteAnnouncementState;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
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

public class DeleteAnnouncementViewTest {

    @Mock
    private DeleteAnnouncementViewModel mockDeleteAnnouncementViewModel;
    @Mock
    private DeleteAnnouncementController mockDeleteAnnouncementController;
    @Mock
    private DeleteAnnouncementState mockDeleteAnnouncementState;

    @Mock
    private ViewManagerModel viewManagerModel;

    private DeleteAnnouncementView deleteAnnouncementView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deleteAnnouncementView = new DeleteAnnouncementView(mockDeleteAnnouncementController, mockDeleteAnnouncementViewModel, viewManagerModel);
        when(mockDeleteAnnouncementViewModel.getState()).thenReturn(mockDeleteAnnouncementState);
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull(deleteAnnouncementView.announcementIdInputField);
        assertNotNull(deleteAnnouncementView.deleteAnnouncementButton);
        assertNotNull(deleteAnnouncementView.cancel);
    }

    @Test
    public void testDeleteAnnouncementButtonActionListener() {
        // Set up the necessary state in the mock ViewModel
        when(mockDeleteAnnouncementState.getAnnouncementID()).thenReturn("someID");

        when(mockDeleteAnnouncementViewModel.getState()).thenReturn(mockDeleteAnnouncementState);

        // Set text in the announcementIdInputField if needed
        deleteAnnouncementView.announcementIdInputField.setText("someID");

        // Trigger the action listener
        ActionEvent event = new ActionEvent(deleteAnnouncementView.deleteAnnouncementButton, ActionEvent.ACTION_PERFORMED, "");
        deleteAnnouncementView.deleteAnnouncementButton.getActionListeners()[0].actionPerformed(event);

        // If the second parameter is indeed supposed to be null, adjust the expectation:
        verify(mockDeleteAnnouncementController).execute(eq("someID"), eq(null));
        // Otherwise, replace eq(null) with eq("expectedValue") or anyString() if the value is not null
    }


    @Test
    public void testAnnouncementIdInputFieldKeyListener() {
        KeyEvent keyEvent = new KeyEvent(deleteAnnouncementView.announcementIdInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        for (KeyListener listener : deleteAnnouncementView.announcementIdInputField.getKeyListeners()) {
            listener.keyTyped(keyEvent);
        }
        verify(mockDeleteAnnouncementViewModel).setState(any(DeleteAnnouncementState.class));
    }

    @Test
    public void testCancelButtonActionListener() {
        ActionEvent event = new ActionEvent(deleteAnnouncementView.cancel, ActionEvent.ACTION_PERFORMED, "");
        deleteAnnouncementView.cancel.getActionListeners()[0].actionPerformed(event);
        // Assertions based on the expected behavior of the cancel button
    }

    @Test
    public void testPropertyChangeListener() {
        when(mockDeleteAnnouncementState.getAnnouncementError()).thenReturn("Error message");
        PropertyChangeEvent evt = new PropertyChangeEvent(mockDeleteAnnouncementViewModel, "state", null, mockDeleteAnnouncementState);
        deleteAnnouncementView.propertyChange(evt);
        // Assertions based on how the view should respond to the change
    }

    @Test
    public void testAnnouncementIdInputFieldKeyPressed() {
        KeyEvent keyPressedEvent = new KeyEvent(deleteAnnouncementView.announcementIdInputField, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');
        for (KeyListener listener : deleteAnnouncementView.announcementIdInputField.getKeyListeners()) {
            listener.keyPressed(keyPressedEvent);
        }

        // Verify that no state change occurs in the DeleteAnnouncementViewModel
        verify(mockDeleteAnnouncementViewModel, never()).setState(any(DeleteAnnouncementState.class));
    }

    // Test for KeyReleased Event on Announcement ID Input Field
    @Test
    public void testAnnouncementIdInputFieldKeyReleased() {
        KeyEvent keyReleasedEvent = new KeyEvent(deleteAnnouncementView.announcementIdInputField, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a');
        for (KeyListener listener : deleteAnnouncementView.announcementIdInputField.getKeyListeners()) {
            listener.keyReleased(keyReleasedEvent);
        }

        // Verify that no state change occurs in the DeleteAnnouncementViewModel
        verify(mockDeleteAnnouncementViewModel, never()).setState(any(DeleteAnnouncementState.class));
    }


}

