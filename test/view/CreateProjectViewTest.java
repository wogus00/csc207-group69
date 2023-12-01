package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectState;
import interface_adapter.create_project.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

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
        MockitoAnnotations.initMocks(this);
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testActionPerformed_CreateButton_Click() {
        // Test implementation already provided
    }

    @Test
    public void testKeyTyped_ProjectNameInputField() {
        // Test implementation already provided
    }

    // Additional Test Cases


}
