package app;

import entity.Meeting;
import interface_adapter.ViewManagerModel;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_meeting.ModifyMeetingDataAccessInterface;
import use_case.modify_meeting.ModifyMeetingGmailDataAccessInterface;
import view.ModifyMeetingView;


import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ModifyMeetingUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private ModifyMeetingViewModel mockModifyMeetingViewModel;
    @Mock
    private ModifyMeetingDataAccessInterface mockModifyMeetingDataAccessObject;
    @Mock
    private ModifyMeetingGmailDataAccessInterface mockModifyMeetingGmailDataAccessObject;
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
        ModifyMeetingView result = ModifyMeetingUseCaseFactory.modifyMeetingView(
                mockViewManagerModel,
                mockModifyMeetingViewModel,
                mockModifyMeetingDataAccessObject,
                mockModifyMeetingGmailDataAccessObject,
                mainPageViewModel
        );

        // Assert
        assertNotNull(result);
        // Additional assertions or verifications can be added here
    }
}
