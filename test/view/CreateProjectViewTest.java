package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_project.CreateProjectController;
import interface_adapter.create_project.CreateProjectState;
import interface_adapter.create_project.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


import javax.mail.internet.AddressException;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
        MockitoAnnotations.openMocks(this);
        when(mockViewModel.getState()).thenReturn(new CreateProjectState());
        view = new CreateProjectView(mockController, mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testActionPerformed_CreateButton_Click() throws IOException, AddressException {
        // Simulate the action
        JButton createButton = view.getCreateButton();
        createButton.doClick();

        // Validate the interaction
        verify(mockController).execute(anyString(), anyString(), any());

    }

    @Test
    public void testProjectNameInputField() {
        // Given
        JTextField projectNameInputField = view.getProjectNameInputField();
        String projectName = "New Project Name";

        // When
        projectNameInputField.setText(projectName);
        for (char c : projectName.toCharArray()) {
            projectNameInputField.dispatchEvent(new KeyEvent(
                    projectNameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setProjectName(projectName);
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testLeaderEmailInputField() {
        // Given
        JTextField leaderEmailInputField = view.getLeaderEmailInputField();
        String leaderEmail = "leader@example.com";

        // When
        leaderEmailInputField.setText(leaderEmail);
        for (char c : leaderEmail.toCharArray()) {
            leaderEmailInputField.dispatchEvent(new KeyEvent(
                    leaderEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setLeaderEmail(leaderEmail);
        verify(mockViewModel).setState(refEq(expectedState));
    }

    @Test
    public void testMemberEmailInputField() {
        // Given
        JTextField memberEmailInputField = view.getMemberEmailInputField();
        String memberEmail = "member1@example.com,member2@example.com";

        // When
        memberEmailInputField.setText(memberEmail);
        for (char c : memberEmail.toCharArray()) {
            memberEmailInputField.dispatchEvent(new KeyEvent(
                    memberEmailInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c));
        }

        // Then
        CreateProjectState expectedState = new CreateProjectState();
        expectedState.setMemberEmail(memberEmail);
        verify(mockViewModel).setState(refEq(expectedState));
    }

}
