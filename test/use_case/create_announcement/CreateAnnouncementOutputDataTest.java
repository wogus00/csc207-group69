package use_case.create_announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateAnnouncementOutputDataTest {

    private CreateAnnouncementOutputData outputData;
    private final String title = "Test Title";
    private final String message = "Test Message";
    private final String creationTime = "2023-01-01T00:00:00";
    private final boolean useCaseFailed = false;
    private final String author = "Test Author";
    private final String announcementID = "12345";

    @BeforeEach
    void setUp() {
        outputData = new CreateAnnouncementOutputData(title, message, creationTime, useCaseFailed, author, announcementID);
    }

    @Test
    void testGetAnnouncementTitle() {
        assertEquals(title, outputData.getAnnouncementTitle(), "The announcement title should match the input.");
    }

    @Test
    void testGetMessage() {
        assertEquals(message, outputData.getMessage(), "The announcement message should match the input.");
    }

    @Test
    void testGetCreationTime() {
        assertEquals(creationTime, outputData.getCreationTime(), "The creation time should match the input.");
    }

    @Test
    void testGetAuthor() {
        assertEquals(author, outputData.getAuthor(), "The author should match the input.");
    }

    @Test
    void testGetAnnouncementID() {
        assertEquals(announcementID, outputData.getAnnouncementID(), "The announcement ID should match the input.");
    }
}

