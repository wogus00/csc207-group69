package use_case.login;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LoginOutputDataTest  {

    @Test
    public void testOutputData() {
        String projectName = "Test Project";
        String userEmail = "user1@example.com";
        String leaderEmail = "leader@example.com";
        ArrayList<String> memberEmails = new ArrayList<>(Arrays.asList("user1@example.com","user2@example,com"));
        ArrayList<String> taskList = new ArrayList<>(Arrays.asList("Test Task"));
        ArrayList<String> meetingList = new ArrayList<>(Arrays.asList("Test Meeting"));
        ArrayList<String> announcements = new ArrayList<>(Arrays.asList("Test Announcement Message"));
        boolean useCaseFailed = false;
        LoginOutputData loginOutputData = new LoginOutputData(projectName, userEmail, leaderEmail, memberEmails, taskList, meetingList, announcements, useCaseFailed);
        assertEquals(projectName, loginOutputData.getProjectName());
        assertEquals(userEmail, loginOutputData.getUserEmail());
        assertEquals(leaderEmail, loginOutputData.getLeaderEmail());
        assertEquals(memberEmails, loginOutputData.getMemberEmails());
        assertEquals(taskList, loginOutputData.getTaskList());
        assertEquals(meetingList, loginOutputData.getMeetingList());
        assertEquals(announcements, loginOutputData.getAnnouncements());
    }
}
