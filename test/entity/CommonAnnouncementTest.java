package entity;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class CommonAnnouncementTest {

    @Test
    public void testCommonAnnouncementConstructorAndGetters() {
        // Arrange
        String title = "Test Title";
        String message = "Test Message";
        LocalDateTime creationTime = LocalDateTime.now();
        String author = "Test Author";
        String id = "TestId";

        // Act
        CommonAnnouncement announcement = new CommonAnnouncement(title, message, creationTime, author, id);

        // Assert
        assertEquals(title, announcement.getAnnouncementTitle());
        assertEquals(message, announcement.getMessage());
        assertEquals(creationTime, announcement.getCreationTime());
        assertEquals(author, announcement.getAuthor());
        assertEquals(id, announcement.getId());
    }
}

