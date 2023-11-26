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
        String title = "Test Title";
        String message = "Test Message";
        String author = "Test Author";

        // Act
        controller.execute(title, message, author);

        // Assert
        verify(mockInteractor).execute(any(CreateAnnouncementInputData.class));
    }

    @Test(expected = IOException.class)
    public void testExecute_IOException() throws Exception {
        // Arrange
        doThrow(IOException.class).when(mockInteractor).execute(any(CreateAnnouncementInputData.class));

        // Act
        controller.execute("Test Title", "Test Message", "Invalid Email Format");
    }

    @Test(expected = AddressException.class)
    public void testExecute_AddressException() throws Exception {
        // Arrange
        doThrow(AddressException.class).when(mockInteractor).execute(any(CreateAnnouncementInputData.class));

        // Act
        controller.execute("Test Title", "Test Message", "Invalid Email Format");
    }
}
