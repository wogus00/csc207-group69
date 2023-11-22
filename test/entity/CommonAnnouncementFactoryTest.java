package entity;

import static org.junit.Assert.*;

import entity.CommonAnnouncement;
import entity.CommonAnnouncementFactory;
import org.junit.Test;
import java.time.LocalDateTime;

public class CommonAnnouncementFactoryTest {

    @Test
    public void testCreateCommonAnnouncement() {
        // Arrange
        CommonAnnouncementFactory factory = new CommonAnnouncementFactory();
        String title = "Test Title";
        String message = "Test Message";
        LocalDateTime creationTime = LocalDateTime.now();
        String author = "Test Author";
        String id = "TestId";

        // Act
        CommonAnnouncement announcement = (CommonAnnouncement) factory.create(title, message, creationTime, author, id);

        // Assert
        assertNotNull(announcement);
        assertEquals(title, announcement.getAnnouncementTitle());
        assertEquals(message, announcement.getMessage());
        assertEquals(creationTime, announcement.getCreationTime());
        assertEquals(author, announcement.getAuthor());
        assertEquals(id, announcement.getId());
    }
}
