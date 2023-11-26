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


}
