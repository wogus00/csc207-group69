package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;



public class LoginViewTest {

    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private LoginController mockLoginController;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private LoginView loginView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginView = new LoginView(mockLoginViewModel, mockLoginController, mockViewManagerModel);
    }

    @Test
    public void testComponentTypes() {
        assertNotNull(loginView.projectNameInputField);
        assertNotNull( loginView.userEmailInputField);
        assertNotNull(loginView.logIn);
        assertNotNull(loginView.create);
    }


    @Test
    public void testPropertyChangeListener_loginError() {
        // Set up a state in the mock ViewModel
        LoginState mockState = new LoginState();
        mockState.setLoginError("Error Message");
        when(mockLoginViewModel.getState()).thenReturn(mockState);

        // Simulate property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockLoginViewModel, "state", null, mockState);
        loginView.propertyChange(evt);
    }

    @Test
    public void testPropertyChangeListener_isLogout() {
        // Set up a state in the mock ViewModel
        LoginState mockState = new LoginState();
        mockState.setLogout(true);
        when(mockLoginViewModel.getState()).thenReturn(mockState);
        loginView.userEmailInputField.setText("Test User Email Field");
        loginView.projectNameInputField.setText("Test Project Name Field");

        // Simulate property change event
        PropertyChangeEvent evt = new PropertyChangeEvent(mockLoginViewModel, "state", null, mockState);
        loginView.propertyChange(evt);
        assertEquals("", loginView.projectNameInputField.getText());
        assertEquals("", loginView.userEmailInputField.getText());
    }

    @Test
    public void testLogInButtonActionListener() {
        LoginState mockState = new LoginState();
        mockState.setProjectName("Test Project");
        mockState.setUserEmail("test@example.com");

        when(mockLoginViewModel.getState()).thenReturn(mockState);

        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(loginView.logIn, ActionEvent.ACTION_PERFORMED, "");
        loginView.logIn.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify the loginController.execute is called with correct data
        verify(mockLoginController).execute("Test Project", "test@example.com");
    }

    @Test
    public void testCreateButtonActionListener() {
        // Simulate button click
        ActionEvent clickEvent = new ActionEvent(loginView.create, ActionEvent.ACTION_PERFORMED, "");
        loginView.create.getActionListeners()[0].actionPerformed(clickEvent);

        // Verify viewManagerModel.setActiveView is called with "create project"
        verify(mockViewManagerModel).setActiveView("create project");
        verify(mockViewManagerModel).firePropertyChanged();
    }


}
