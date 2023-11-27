package interface_adapter.delete_announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.delete_announcement.DeleteAnnouncementInputBoundary;

import static org.mockito.Mockito.*;

class DeleteAnnouncementControllerTest {

    @Mock
    private DeleteAnnouncementInputBoundary interactor;

    private DeleteAnnouncementController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new DeleteAnnouncementController(interactor);
    }

    @Test
    void testExecuteDeletion() {
        String testId = "testAnnouncementId";
        String testUser = "testUser";

        controller.execute(testId, testUser);

        verify(interactor, times(1)).execute(testId, testUser);
    }
}
