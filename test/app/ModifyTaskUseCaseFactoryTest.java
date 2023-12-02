package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.modify_task.ModifyTaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_task.ModifyTaskDataAccessInterface;
import use_case.modify_task.ModifyTaskGmailDataAccessInterface;
import view.ModifyTaskView;

import static org.junit.Assert.assertNotNull;

public class ModifyTaskUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private ModifyTaskViewModel mockModifyTaskViewModel;
    @Mock
    private ModifyTaskDataAccessInterface mockModifyTaskDataAccessObject;
    @Mock
    private ModifyTaskGmailDataAccessInterface mockModifyTaskGmailDataAccessObject;
    @Mock
    private MainPageViewModel mainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testModifyLoginView_Success() {
        // Setup
        // Configure mocks if necessary

        // Act
        ModifyTaskView result = ModifyTaskUseCaseFactory.modifyTaskView(
                mockViewManagerModel,
                mockModifyTaskViewModel,
                mockModifyTaskDataAccessObject,
                mockModifyTaskGmailDataAccessObject
        );

        // Assert
        assertNotNull(result);
        // Additional assertions or verifications can be added here
    }
}
