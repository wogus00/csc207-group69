package entity;

import static org.junit.Assert.*;

import interface_adapter.create_meeting.CreateMeetingState;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CommonMeetingFactoryTest {

    @Test
    public void testCreateCommonMeeting() {
        // Arrange
        CommonMeetingFactory factory = new CommonMeetingFactory();
        String meetingName = "Meeting";
        ArrayList<String> participantEmail = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        String meetingDate = "11/11/1111";
        String startTime = "11:11";
        String endTime = "22:22";
        String projectName = "Project";

        // Act
        CommonMeeting meeting = (CommonMeeting) factory.create(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        // Assert
        ArrayList<String> emails = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        assertNotNull(meeting);
        assertEquals("Meeting", meeting.getMeetingName());
        assertEquals(emails, meeting.getParticipantEmail());
        assertEquals("11/11/1111", meeting.getMeetingDate());
        assertEquals("11:11", meeting.getStartTime());
        assertEquals("22:22", meeting.getEndTime());
        assertEquals("Project", meeting.getProjectName());
    }
}

