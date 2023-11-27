package interface_adapter.delete_announcement;

import entity.CommonAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAnnouncementStateTest {

    private DeleteAnnouncementState state;
    private final String testAnnouncementError = "Error Message";
    private final String testAnnouncementId = "AnnouncementId";
    private final CommonAnnouncement testAnnouncement = new CommonAnnouncement("test1", "testing", LocalDateTime.now(), "tester", "10"); // Assume a constructor or a method to create a test announcement

    @BeforeEach
    void setUp() {
        state = new DeleteAnnouncementState();
    }

    @Test
    void testSetAndGetAnnouncementError() {
        state.setAnnouncementError(testAnnouncementError);
        assertEquals(testAnnouncementError, state.getAnnouncementError(), "The announcement error should match the set value.");
    }

    @Test
    void testSetAndGetAnnouncement() {
        state.setAnnouncement(testAnnouncement);
        assertEquals(testAnnouncement, state.getAnnouncement(), "The announcement should match the set value.");
    }

    @Test
    void testSetAndGetAnnouncementID() {
        state.setAnnouncementID(testAnnouncementId);
        assertEquals(testAnnouncementId, state.getAnnouncementID(), "The announcement ID should match the set value.");
    }

    @Test
    void testCopyConstructor() {
        state.setAnnouncement(testAnnouncement);
        state.setAnnouncementError(testAnnouncementError);
        state.setAnnouncementID(testAnnouncementId);

        DeleteAnnouncementState copiedState = new DeleteAnnouncementState(state);
        assertEquals(testAnnouncement, copiedState.getAnnouncement(), "The copied state should have the same announcement.");
        assertEquals(testAnnouncementError, copiedState.getAnnouncementError(), "The copied state should have the same error message.");
        assertEquals(testAnnouncementId, copiedState.getAnnouncementID(), "The copied state should have the same ID.");
    }
}
