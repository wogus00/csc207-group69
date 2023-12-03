package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.create_meeting.CreateMeetingDataAccessInterface;
import use_case.create_meeting.CreateMeetingGmailDataAccessInterface;
import use_case.remove_email.RemoveEmailDataAccessInterface;
import view.AddEmailView;
import view.RemoveEmailView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class AddEmailUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private AddEmailViewModel mockAddEmailViewModel;
    @Mock
    private AddEmailDataAccessInterface mockUserDataAccessObject;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveEmailView() {
        AddEmailView view = AddEmailUseCaseFactory.addEmailView(mockViewManagerModel, mockAddEmailViewModel, mockUserDataAccessObject, mockMainPageViewModel);

        assertNotNull(view);
    }
}
