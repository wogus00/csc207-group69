package app;

import entity.Meeting;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_meeting.CreateMeetingDataAccessInterface;
import use_case.create_meeting.CreateMeetingGmailDataAccessInterface;
import view.CreateMeetingView;


import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CreateMeetingUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private CreateMeetingDataAccessInterface mockCreateMeetingDataAccessObject;
    @Mock
    private CreateMeetingGmailDataAccessInterface mockCreateMeetingGmailDataAccessObject;
    @Mock
    private MainPageViewModel mainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateLoginView_Success() {
        // Setup
        // Configure mocks if necessary

        // Act
        CreateMeetingView result = CreateMeetingUseCaseFactory.createMeetingView(
                mockViewManagerModel,
                mockCreateMeetingViewModel,
                mockCreateMeetingDataAccessObject,
                mockCreateMeetingGmailDataAccessObject,
                mainPageViewModel
        );

        // Assert
        assertNotNull(result);
        // Additional assertions or verifications can be added here
    }
}
