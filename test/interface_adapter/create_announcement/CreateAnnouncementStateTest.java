package interface_adapter.create_announcement;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateAnnouncementStateTest {

    @Test
    public void testDefaultConstructor() {
        CreateAnnouncementState state = new CreateAnnouncementState();
        assertNull(state.getAnnouncementTitleError());
        assertEquals("", state.getAnnouncementTitle());
        assertEquals("", state.getMessage());
        assertEquals("", state.getAuthor());
        assertEquals("", state.getId());
    }

    @Test
    public void testCopyConstructor() {
        CreateAnnouncementState original = new CreateAnnouncementState();
        original.setAnnouncementTitle("Title");
        original.setMessage("Message");
        original.setAuthor("Author");
        original.setId("Id");

        CreateAnnouncementState copy = new CreateAnnouncementState(original);
        assertEquals("Title", copy.getAnnouncementTitle());
        assertEquals("Message", copy.getMessage());
        assertEquals("Author", copy.getAuthor());
        assertEquals("Id", copy.getId());
    }

    // Additional tests for setters and getters...
}
