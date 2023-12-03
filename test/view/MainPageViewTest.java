package view;


import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailState;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_announcement.CreateAnnouncementState;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.delete_announcement.DeleteAnnouncementState;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.modify_meeting.ModifyMeetingState;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import interface_adapter.modify_task.ModifyTaskState;
import interface_adapter.modify_task.ModifyTaskViewModel;
import interface_adapter.remove_email.RemoveEmailState;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.set_leader.SetLeaderState;
import interface_adapter.set_leader.SetLeaderViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MainPageViewTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private MainPageViewModel mockMainPageViewModel;
    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private CreateTaskViewModel mockCreateTaskViewModel;
    @Mock
    private CompleteTaskViewModel mockCompleteTaskViewModel;
    @Mock
    private ModifyTaskViewModel mockModifyTaskViewModel;
    @Mock
    private AddEmailViewModel mockAddEmailViewModel;
    @Mock
    private RemoveEmailViewModel mockRemoveEmailViewModel;
    @Mock
    private SetLeaderViewModel mockSetLeaderViewModel;
    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private ModifyMeetingViewModel mockModifyMeetingViewModel;
    @Mock
    private CreateAnnouncementViewModel mockCreateAnnouncementViewModel;
    @Mock
    private DeleteAnnouncementViewModel mockDeleteAnnouncementViewModel;
    // Add any other required mock dependencies

    private MainPageView mainPageView;

    @Before
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this);

        // Instantiate MainPageView with the mocked dependencies
        mainPageView = new MainPageView(mockViewManagerModel,
                mockMainPageViewModel,
                mockLoginViewModel,
                mockCreateTaskViewModel,
                mockCompleteTaskViewModel,
                mockModifyTaskViewModel,
                mockAddEmailViewModel,
                mockRemoveEmailViewModel,
                mockSetLeaderViewModel,
                mockCreateMeetingViewModel,
                mockModifyMeetingViewModel,
                mockCreateAnnouncementViewModel,
                mockDeleteAnnouncementViewModel);
        when(mockMainPageViewModel.getState()).thenReturn(new MainPageState());
        when(mockLoginViewModel.getState()).thenReturn(new LoginState());
        when(mockCreateTaskViewModel.getState()).thenReturn(new CreateTaskState());
        when(mockCompleteTaskViewModel.getState()).thenReturn(new CompleteTaskState());
        when(mockModifyTaskViewModel.getState()).thenReturn(new ModifyTaskState());
        when(mockAddEmailViewModel.getState()).thenReturn(new AddEmailState());
        when(mockRemoveEmailViewModel.getState()).thenReturn(new RemoveEmailState());
        when(mockSetLeaderViewModel.getState()).thenReturn(new SetLeaderState());
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());
        when(mockModifyMeetingViewModel.getState()).thenReturn(new ModifyMeetingState());
        when(mockCreateAnnouncementViewModel.getState()).thenReturn(new CreateAnnouncementState());
        when(mockDeleteAnnouncementViewModel.getState()).thenReturn(new DeleteAnnouncementState());


        // Additional setup can be done here if needed
    }

    @Test
    public void testInformationButtonAction() {
        // Simulate clicking the 'showAllButton'
        ActionEvent showAllEvent = new ActionEvent(mainPageView.showAllButton, ActionEvent.ACTION_PERFORMED, "");
        mainPageView.showAllButton.getActionListeners()[0].actionPerformed(showAllEvent);

        ActionEvent recentAnnounceEvent = new ActionEvent(mainPageView.recentAnnouncement, ActionEvent.ACTION_PERFORMED, "");
        mainPageView.recentAnnouncement.getActionListeners()[0].actionPerformed(recentAnnounceEvent);
    }

    @Test
    public void testLogoutButtonAction() {
        ActionEvent logoutEvent = new ActionEvent(mainPageView.logoutButton, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.logoutButton.getActionListeners()[0].actionPerformed(logoutEvent);

        verify(mockLoginViewModel).setState(any(LoginState.class)); // Verify if setState is called with some LoginState
        verify(mockViewManagerModel).setActiveView("log in"); // Verify if setActiveView with "log in" is called

    }

    @Test
    public void testExtensionButtonAction() {
        ActionEvent taskEvent = new ActionEvent(mainPageView.taskButton, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.taskButton.getActionListeners()[0].actionPerformed(taskEvent);
        assertTrue(mainPageView.taskPanelExtension.isVisible());

        ActionEvent meetingEvent = new ActionEvent(mainPageView.meetingButton, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.meetingButton.getActionListeners()[0].actionPerformed(meetingEvent);
        assertTrue(mainPageView.meetingPanelExtension.isVisible());

        ActionEvent announcementEvent = new ActionEvent(mainPageView.announcementButton, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.announcementButton.getActionListeners()[0].actionPerformed(announcementEvent);
        assertTrue(mainPageView.announcementPanelExtension.isVisible());

        ActionEvent projectEvent = new ActionEvent(mainPageView.projectButton, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.projectButton.getActionListeners()[0].actionPerformed(projectEvent);
        assertTrue(mainPageView.projectPanelExtension.isVisible());
    }

    @Test
    public void testTaskFunctionButtons() {
        ActionEvent eventT1 = new ActionEvent(mainPageView.buttonT1, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonT1.getActionListeners()[0].actionPerformed(eventT1);

        // Verify the interactions for create task
        verify(mockCreateTaskViewModel).setState(any(CreateTaskState.class));
        verify(mockViewManagerModel).setActiveView("Create Task");

        ActionEvent eventT2 = new ActionEvent(mainPageView.buttonT2, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonT2.getActionListeners()[0].actionPerformed(eventT2);

        // Verify the interactions for modify task
        verify(mockModifyTaskViewModel).setState(any(ModifyTaskState.class));
        verify(mockViewManagerModel).setActiveView("Modify Task");

        ActionEvent eventT3 = new ActionEvent(mainPageView.buttonT3, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonT3.getActionListeners()[0].actionPerformed(eventT3);
        // Verify the interactions for complete task
        verify(mockCompleteTaskViewModel).setState(any(CompleteTaskState.class));
        verify(mockViewManagerModel).setActiveView("Complete Task");

        // Add any additional verifications as per your business logic
    }

    @Test
    public void testMeetingFunctionButtons() {
        // Simulate clicking the 'buttonM1' for creating a meeting
        ActionEvent eventM1 = new ActionEvent(mainPageView.buttonM1, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonM1.getActionListeners()[0].actionPerformed(eventM1);
        // Verify the interactions for create meeting
        verify(mockCreateMeetingViewModel).setState(any(CreateMeetingState.class));
        verify(mockViewManagerModel).setActiveView("create meeting");

        // Simulate clicking the 'buttonM2' for modifying a meeting
        ActionEvent eventM2 = new ActionEvent(mainPageView.buttonM2, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonM2.getActionListeners()[0].actionPerformed(eventM2);
        // Verify the interactions for modify meeting
        verify(mockModifyMeetingViewModel).setState(any(ModifyMeetingState.class));
        verify(mockViewManagerModel).setActiveView("Modify Meeting");

        // Add any additional verifications as per your business logic
    }

    @Test
    public void testAnnouncementFunctionButtons() {
        // Simulate clicking the 'buttonA1' for creating an announcement
        ActionEvent eventA1 = new ActionEvent(mainPageView.buttonA1, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonA1.getActionListeners()[0].actionPerformed(eventA1);
        // Verify the interactions for create announcement
        verify(mockCreateAnnouncementViewModel).setState(any(CreateAnnouncementState.class));
        verify(mockViewManagerModel).setActiveView("Create announcement");

        // Simulate clicking the 'buttonA2' for deleting an announcement
        ActionEvent eventA2 = new ActionEvent(mainPageView.buttonA2, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonA2.getActionListeners()[0].actionPerformed(eventA2);
        // Verify the interactions for delete announcement
        verify(mockDeleteAnnouncementViewModel).setState(any(DeleteAnnouncementState.class));
        verify(mockViewManagerModel).setActiveView("Delete announcement");

        // Add any additional verifications as per your business logic
    }

    @Test
    public void testProjectFunctionButtons() {
        // Simulate clicking the 'buttonP1' for adding a member
        ActionEvent eventP1 = new ActionEvent(mainPageView.buttonP1, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonP1.getActionListeners()[0].actionPerformed(eventP1);
        // Verify the interactions for adding a member
        verify(mockAddEmailViewModel).setState(any(AddEmailState.class));
        verify(mockViewManagerModel).setActiveView("Add Email");

        // Simulate clicking the 'buttonP2' for removing a member
        ActionEvent eventP2 = new ActionEvent(mainPageView.buttonP2, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonP2.getActionListeners()[0].actionPerformed(eventP2);
        // Verify the interactions for removing a member
        verify(mockRemoveEmailViewModel).setState(any(RemoveEmailState.class));
        verify(mockViewManagerModel).setActiveView("Remove Email");

        // Simulate clicking the 'buttonP3' for changing the project leader
        ActionEvent eventP3 = new ActionEvent(mainPageView.buttonP3, ActionEvent.ACTION_PERFORMED, "command");
        mainPageView.buttonP3.getActionListeners()[0].actionPerformed(eventP3);
        // Verify the interactions for changing the project leader
        verify(mockSetLeaderViewModel).setState(any(SetLeaderState.class));
        verify(mockViewManagerModel).setActiveView("Set Leader");

        // Add any additional verifications as per your business logic
    }

    @Test
    public void testPropertyChangeListener() {
        // Set up a state in the mock ViewModel
        MainPageState mockState = new MainPageState();
        mockState.setProjectName("Test Project");
        mockState.setUserEmail("user@exmapl.com");
        mockState.setLeaderEmail("user1@exmapl.com");
        mockState.setMemberEmail(new ArrayList<>(Arrays.asList("user@exmapl.com")));
        when(mockMainPageViewModel.getState()).thenReturn(mockState);
        PropertyChangeEvent evt = new PropertyChangeEvent(mockMainPageViewModel, "state", null, mockState);
        mainPageView.propertyChange(evt);

        assertFalse(mainPageView.projectPanelExtension.isVisible());
    }

}



