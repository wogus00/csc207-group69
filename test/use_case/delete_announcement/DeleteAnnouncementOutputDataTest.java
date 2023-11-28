package use_case.delete_announcement;

import entity.Announcement;
import entity.CommonAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAnnouncementOutputDataTest {

    private DeleteAnnouncementOutputData outputData;
    private final CommonAnnouncement testAnnouncement = new CommonAnnouncement("title", "sample", LocalDateTime.now(), "author@mail", "10"); // Assuming a constructor is available
    private final String testDeleteTime = "2023-01-01T12:00:00";
    private final boolean testIsDeleted = true;

    @BeforeEach
    void setUp() {
        outputData = new DeleteAnnouncementOutputData(testAnnouncement, testDeleteTime, testIsDeleted);
    }

    @Test
    void testSetAndGetAnnouncement() {
        assertEquals(testAnnouncement, outputData.getAnnouncement(), "The announcement should match the set value.");

        CommonAnnouncement newAnnouncement = new CommonAnnouncement("title1", "sample1", LocalDateTime.now(), "author@mail", "5"); // Assuming a new instance
        outputData.setAnnouncement(newAnnouncement);
        assertEquals(newAnnouncement, outputData.getAnnouncement(), "The announcement should be updated correctly.");
    }

    @Test
    void testSetAndGetDeleteTime() {
        assertEquals(testDeleteTime, outputData.getDeleteTime(), "The delete time should match the set value.");

        String newDeleteTime = "2023-01-02T12:00:00";
        outputData.setDeleteTime(newDeleteTime);
        assertEquals(newDeleteTime, outputData.getDeleteTime(), "The delete time should be updated correctly.");
    }

    @Test
    void testSetAndGetIsDeleted() {
        assertEquals(testIsDeleted, outputData.getIsDeleted(), "The isDeleted status should match the set value.");

        outputData.setDeleted(false);
        assertFalse(outputData.getIsDeleted(), "The isDeleted status should be updated correctly.");
    }
}
