package interface_adapter.main_page;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageStateTest {

    @Test
    public void testGetProjectName() {
        MainPageState state = new MainPageState();
        state.setProjectName("TestProject");
        assertEquals("TestProject", state.getProjectName());
    }

    @Test
    public void testSetUserEmail() {
        MainPageState state = new MainPageState();
        state.setUserEmail("test@example.com");
        assertEquals("test@example.com", state.getUserEmail());
    }

    @Test
    public void testGetAnnouncementLabel() {
        MainPageState state = new MainPageState();
        state.addAnnouncement("Test Announcement");
        String expectedLabel = "<html>Announcement:<br/>Test Announcement</html>";
        assertEquals(expectedLabel, state.getAnnouncementLabel());
    }

    @Test
    public void testGetLabelForMember() {
        MainPageState state = new MainPageState();
        state.setLeaderEmail("leader@example.com");
        state.setMemberEmail(new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")));
        String label = state.getLabel("member");
        assertTrue(label.contains("Member List:"));
        assertTrue(label.contains("leader@example.com"));
        assertTrue(label.contains("member1@example.com"));
    }

    // Add more tests for other methods as needed

}
