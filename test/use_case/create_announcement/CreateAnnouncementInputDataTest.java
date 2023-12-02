package use_case.create_announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateAnnouncementInputDataTest {
    private CreateAnnouncementInputData inputData;

    private String projectName = "project name";
    private String title = "Test Title";
    private String message = "Test Message";
    private String author = "Test Author";

    @BeforeEach
    void setUp() {
        inputData = new CreateAnnouncementInputData(projectName, title, message, author);
    }

    @Test
    void testGetAnnouncementTitle() {
        assertEquals(title, inputData.getAnnouncementTitle(), "The announcement title should match the input.");
    }

    @Test
    void testGetMessage() {
        assertEquals(message, inputData.getMessage(), "The announcement message should match the input.");
    }

    @Test
    void testGetAuthor() {
        assertEquals(author, inputData.getAuthor(), "The author should match the input.");
    }

    @Test
    void testGetAnnouncementId() {
        assertNotNull(inputData.getAnnouncementId(), "The announcement ID should not be null.");
        assertTrue(inputData.getAnnouncementId().matches("^[0-9a-fA-F-]+$"), "The announcement ID should be a valid UUID.");
    }

    @Test
    void testUniqueIdForDifferentInstances() {
        CreateAnnouncementInputData anotherInputData = new CreateAnnouncementInputData(projectName, title, message, author);
        assertNotEquals(inputData.getAnnouncementId(), anotherInputData.getAnnouncementId(), "Each instance should have a unique announcement ID.");
    }
}
