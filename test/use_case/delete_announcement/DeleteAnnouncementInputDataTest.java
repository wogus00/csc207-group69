package use_case.delete_announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteAnnouncementInputDataTest {

    private DeleteAnnouncementInputData inputData;
    private final String testAnnouncementId = "Announcement123";
    private final String testUserId = "User456";

    @BeforeEach
    void setUp() {
        inputData = new DeleteAnnouncementInputData(testAnnouncementId, testUserId);
    }

    @Test
    void testGetAndSetAnnouncementId() {
        assertEquals(testAnnouncementId, inputData.getAnnouncementId(), "The announcement ID should be correctly retrieved.");

        String newAnnouncementId = "NewAnnouncement123";
        inputData.setAnnouncementId(newAnnouncementId);
        assertEquals(newAnnouncementId, inputData.getAnnouncementId(), "The announcement ID should be correctly set.");
    }

    @Test
    void testGetAndSetUserId() {
        assertEquals(testUserId, inputData.getUserId(), "The user ID should be correctly retrieved.");

        String newUserId = "NewUser456";
        inputData.setUserId(newUserId);
        assertEquals(newUserId, inputData.getUserId(), "The user ID should be correctly set.");
    }
}
