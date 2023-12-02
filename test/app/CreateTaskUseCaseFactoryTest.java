package app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.Task;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.create_task.CreateTaskGmailDataAccessInterface;
import view.CreateTaskView;

import javax.swing.*;
import java.io.IOException;


public class CreateTaskUseCaseFactoryTest {

    @Test
    public void testCreateTaskViewSuccess() {
        ViewManagerModel mockViewManagerModel = mock(ViewManagerModel.class);
        CreateTaskViewModel mockCreateTaskViewModel = mock(CreateTaskViewModel.class);
        CreateTaskDataAccessInterface mockUserDataAccessObject = mock(CreateTaskDataAccessInterface.class);
        CreateTaskGmailDataAccessInterface mockGmailDataAccessObject = mock(CreateTaskGmailDataAccessInterface.class);
        MainPageViewModel mockMainPageViewModel = mock(MainPageViewModel.class);

        CreateTaskView result = CreateTaskUseCaseFactory.createTaskView(mockViewManagerModel, mockCreateTaskViewModel, mockUserDataAccessObject, mockGmailDataAccessObject, mockMainPageViewModel);

        assertNotNull(result, "CreateTaskView should not be null for valid inputs");
        // Additional assertions can be added to verify the correct use of dependencies
    }

    // Did not write a test case for exception handling because we cannot force to throw IOException.

}

