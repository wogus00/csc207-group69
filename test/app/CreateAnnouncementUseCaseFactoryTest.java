package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import use_case.create_announcement.CreateAnnouncementDataAccessInterface;
import use_case.create_announcement.CreateAnnouncementGmailDataAccessInterface;
import view.CreateAnnouncementView;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

class CreateAnnouncementUseCaseFactoryTest {

    @Mock
    private ViewManagerModel viewManagerModel;
    @Mock
    private CreateAnnouncementViewModel createAnnouncementViewModel;
    @Mock
    private CreateAnnouncementDataAccessInterface announcementDataAccessObject;
    @Mock
    private CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject;
    @Mock
    private MainPageViewModel mainPageViewModel;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulCreationOfCreateAnnouncementView() {
        CreateAnnouncementView view = CreateAnnouncementUseCaseFactory.createAnnouncementView(viewManagerModel,
                createAnnouncementViewModel, announcementDataAccessObject, gmailDataAccessObject, mainPageViewModel);

        assertNotNull(view);
        // Additional assertions can be added to check if view is properly configured
    }




}

