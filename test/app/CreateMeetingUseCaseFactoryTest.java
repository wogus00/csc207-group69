package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingState;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import interface_adapter.main_page.MainPageViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_meeting.*;
import view.CreateMeetingView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CreateMeetingUseCaseFactoryTest {

    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateMeetingViewModel mockCreateMeetingViewModel;
    @Mock
    private CreateMeetingDataAccessInterface mockUserDataAccessObject;
    @Mock
    private CreateMeetingGmailDataAccessInterface mockGmailDataAccessObject;
    @Mock
    private MainPageViewModel mockMainPageViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMeetingView() {
        // Arrange
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        CreateMeetingView createMeetingView = CreateMeetingUseCaseFactory.createMeetingView(
                mockViewManagerModel, mockCreateMeetingViewModel, mockUserDataAccessObject,
                mockGmailDataAccessObject, mockMainPageViewModel);

        // Assert
        assertNotNull(createMeetingView);
    }

    @Test
    public void testCreateMeetingUseCase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        // Arrange
        when(mockCreateMeetingViewModel.getState()).thenReturn(new CreateMeetingState());

        // Act
        Method privateMethod = CreateMeetingUseCaseFactory.class.getDeclaredMethod("createMeetingUseCase",
                ViewManagerModel.class, CreateMeetingViewModel.class,
                CreateMeetingDataAccessInterface.class, CreateMeetingGmailDataAccessInterface.class,
                MainPageViewModel.class);
        privateMethod.setAccessible(true);
        CreateMeetingController createMeetingController = (CreateMeetingController) privateMethod.invoke(null,
                mockViewManagerModel, mockCreateMeetingViewModel, mockUserDataAccessObject,
                mockGmailDataAccessObject, mockMainPageViewModel);

        // Assert
        assertNotNull(createMeetingController);
    }
}