package interface_adapter.create_announcement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.create_announcement.CreateAnnouncementInputBoundary;
import use_case.create_announcement.CreateAnnouncementInputData;

import javax.mail.internet.AddressException;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class CreateAnnouncementControllerTest {

    @Mock
    private CreateAnnouncementInputBoundary mockInteractor;

    private CreateAnnouncementController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CreateAnnouncementController(mockInteractor);
    }

    @Test
    public void testExecute_Successful() throws Exception {
        // Arrange
        String projectName = "project name";
        String title = "Test Title";
        String message = "Test Message";
        String author = "Test Author";

        // Act
        controller.execute(projectName, title, message, author);

        // Assert
        verify(mockInteractor).execute(any(CreateAnnouncementInputData.class));
    }

}
