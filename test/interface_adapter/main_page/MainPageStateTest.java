package interface_adapter.main_page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class MainPageStateTest {
    private MainPageState state;

    @BeforeEach
    void setUp() {
        state = new MainPageState();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(state);
        assertTrue(state.getMemberEmail().isEmpty());
        assertEquals("", state.getProjectName());
        // Additional assertions for default constructor
    }

    @Test
    void testCopyConstructor() {
        MainPageState copyState = new MainPageState();
        copyState.setProjectName("Test Project");
        state = new MainPageState(copyState);
        assertEquals(state.getProjectName(), copyState.getProjectName());
        assertTrue(state.getTaskList().isEmpty());
    }

    // Test getters and setters
    @Test
    void testSetterWithGetter() {
        String projectName = "Test Project";
        String leaderEmail = "test@example.com";
        String userEmail = "user1@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("user1@example.com"));
        ArrayList<String> taskList = new ArrayList<>(Arrays.asList("Task 1", "Task 2"));
        ArrayList<String> meetingList = new ArrayList<>(Arrays.asList());
        ArrayList<String> announcement = new ArrayList<>(Arrays.asList("Announcement Message"));
        state.setProjectName(projectName);
        state.setUserEmail(userEmail);
        state.setLeaderEmail(leaderEmail);
        state.setMemberEmail(memberEmails);
        state.setTaskList(taskList);
        state.setMeetingList(meetingList);
        state.setAnnouncements(announcement);
        assertEquals(projectName, state.getProjectName());
        assertEquals(leaderEmail, state.getLeaderEmail());
        assertEquals(taskList, state.getTaskList());
        assertEquals(memberEmails, state.getMemberEmail());
        assertTrue(state.getMeetingList().isEmpty());
        assertEquals(announcement, state.getAnnouncements());
        assertEquals(userEmail, state.getUserEmail());
    }

    // Repeat similar tests for other getters and setters...

    // Test getLabel method
    @Test
    void testGetLabel() {
        state.setLeaderEmail("leader@example.com");
        state.setMemberEmail(new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")));
        String memberLabel = state.getLabel("member");
        assertTrue(memberLabel.contains("leader@example.com"));
        assertTrue(memberLabel.contains("member1@example.com"));
        assertFalse(memberLabel.contains("member2@example.com")); // three emails doesn't fit on one line

    }

    @Test
    void testGetLabelEmptyList() {
        String memberLabel = state.getLabel("member");
        assertFalse(memberLabel.contains("No members")); // leader email has a default value
        String meetingLabel = state.getLabel("meeting");
        assertTrue(meetingLabel.contains("No Meetings")); // default value for meeting list is an empty array list
        String taskLabel = state.getLabel("task");
        assertTrue(taskLabel.contains("No Tasks")); // same with meeting
    }

    // Test getShowAllMessage method
    @Test
    void testGetShowAllMessage() {
        state.setLeaderEmail("leader@example.com");
        state.setMemberEmail(new ArrayList<>(Arrays.asList("member1@example.com", "member2@example.com")));
        String message = state.getShowAllMessage();
        assertTrue(message.contains("Members:"));
        assertTrue(message.contains("Tasks:"));
        assertTrue(message.contains("Meetings:"));
        assertTrue(message.contains("leader@example.com"));
        assertTrue(message.contains("member1@example.com"));
        assertTrue(message.contains("member2@example.com"));

    }

    // Test getAnnouncementLabel method
    @Test
    void testGetAnnouncementLabel() {
        state.setAnnouncements(new ArrayList<>(Arrays.asList("Test Announcement 1", "Test Announcement 2")));
        String label = state.getAnnouncementLabel();
        assertTrue(label.contains("Test Announcement 2"));
        assertFalse(label.contains("Test Announcement 1"));
        // test announcement 2 is more recent
    }

    @Test
    void testGetAnnouncementLabel2() {
        String label = state.getAnnouncementLabel();
        assertTrue(label.contains("No announcement"));
        state.setAnnouncements(new ArrayList<>(Arrays.asList("This is a very long announcement for testing that should " +
                "not fit on one line in the main page, I am trying to make it longer and longer" + "so that it does not fit, test test test test")));
        String label2 = state.getAnnouncementLabel();
        assertTrue(label2.contains("..."));
    }




    // Test getRecentAnnouncements method
    @Test
    void testGetRecentAnnouncements() {
        state.setAnnouncements(new ArrayList<>(Arrays.asList("Announcement 1", "Announcement 2")));
        String announcements = state.getRecentAnnouncements();
        assertTrue(announcements.contains("Announcement 1"));
        assertTrue(announcements.contains("Announcement 2"));
    }
}
