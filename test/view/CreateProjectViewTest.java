//package view;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.create_project.CreateProjectController;
//import interface_adapter.create_project.CreateProjectState;
//import interface_adapter.create_project.CreateProjectViewModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.swing.*;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;
//
//public class CreateProjectViewTest {
//
//    @Mock
//    private CreateProjectController mockController;
//    @Mock
//    private CreateProjectViewModel mockViewModel;
//    @Mock
//    private ViewManagerModel mockViewManagerModel;
//
//    private CreateProjectView createProjectView;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
//        createProjectView = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
//    }
//
//    @org.junit.Test
//    public void testComponentTypes() {
//        assertNotNull(createProjectView.getProjectDescriptionInputField);
//        assertNotNull(createProjectView.participantEmailInputField);
//        assertNotNull(createProjectView.create);
//        assertNotNull(createProjectView.cancel);
//    }
//
//    @Test
//    public void testKeyTyped_ProjectNameInputField() {
//        // Test implementation already provided
//    }
//
//    // Additional Test Cases
//
//    @Test
//    public void testActionPerformed_CancelButton_Click() {
//        // Given
//        JButton cancelButton = createProjectView.getCancelButton();
//
//        // When
//        cancelButton.doClick();
//
//        // Then
//        verify(mockViewManagerModel).changeView("ActualViewName"); // Replace "ActualViewName" with the real view name.
//    }
//
//    @Test
//    public void testProjectDescriptionInputField() {
//        // Given
//        JTextArea projectDescriptionInputField = createProjectView.getProjectDescriptionInputField();
//        String description = "This is a detailed description of the project.";
//
//        // When
//        projectDescriptionInputField.setText(description);
//        for (char c : description.toCharArray()) {
//            projectDescriptionInputField.dispatchEvent(new KeyEvent(
//                    projectDescriptionInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
//        }
//
//        // Then
//        verify(mockViewModel, atLeastOnce()).setState(any(CreateProjectState.class));
//    }
//
//    @Test
//    public void testProjectDeadlineInputField() {
//        // Given
//        JTextField projectDeadlineInputField = createProjectView.getProjectDeadlineInputField();
//        String deadline = "2023-12-31";
//
//        // When
//        projectDeadlineInputField.setText(deadline);
//
//        // Then
//        assertEquals("2023-12-31", createProjectView.getState().getProjectDeadline());
//    }
//
//    @Test
//    public void testErrorMessageDisplay() {
//        // Given
//        String errorMessage = "An error occurred";
//        when(mockViewModel.getState().getErrorMessage()).thenReturn(errorMessage);
//
//        // When
//        createProjectView.displayErrorMessage();
//
//        // Then
//        JLabel errorLabel = createProjectView.getErrorLabel();
//        assertNotNull(errorLabel);
//        assertEquals(errorMessage, errorLabel.getText());
//    }
//    @Test
//    public void testProjectDescriptionInputField() {
//        // Given
//        JTextArea projectDescriptionInputField = createProjectView.getProjectDescriptionInputField();
//        String description = "This is a detailed description of the project.";
//        CreateProjectState expectedState = new CreateProjectState();
//        expectedState.setProjectDescription(description);
//        when(mockViewModel.getState()).thenReturn(expectedState);
//
//        // When
//        projectDescriptionInputField.setText(description);
//        for (char c : description.toCharArray()) {
//            projectDescriptionInputField.dispatchEvent(new KeyEvent(
//                    projectDescriptionInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
//        }
//
//        // Then
//        verify(mockViewModel, atLeastOnce()).setState(refEq(expectedState));
//    }
//
//}
