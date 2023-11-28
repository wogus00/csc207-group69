package interface_adapter.modify_meeting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ModifyMeetingStateTest {

    @Test
    public void testDefaultConstructor() {
        ModifyMeetingState state = new ModifyMeetingState();
        assertNull(state.getMeetingNameError());
        assertEquals("", state.getMeetingName());
        assertEquals( new ArrayList<>(), state.getParticipantEmail());
        assertEquals("", state.getStartTime());
        assertEquals("", state.getEndTime());
        assertEquals("", state.getProjectName());
    }

    @Test
    public void testCopyConstructor() {
        ModifyMeetingState original = new ModifyMeetingState();
        original.setMeetingName("Meeting");
        original.setParticipantEmail("test1@example.com, test2@example.com");
        original.setMeetingDate("11/11/1111");
        original.setStartTime("11:11");
        original.setEndTime("22:22");
        original.setProjectName("Project");
        original.setMeetingNameError("Error");

        ArrayList<String> email1 = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        ModifyMeetingState copy = new ModifyMeetingState(original);
        assertEquals("Meeting", copy.getMeetingName());
        assertEquals(email1, copy.getParticipantEmail());
        assertEquals("11/11/1111", copy.getMeetingDate());
        assertEquals("11:11", copy.getStartTime());
        assertEquals("22:22", copy.getEndTime());
        assertEquals("Project", copy.getProjectName());
        assertEquals("Error", copy.getMeetingNameError());
    }

    @Test
    public void testToString() {
        ModifyMeetingState original = new ModifyMeetingState();
        original.setMeetingName("Meeting");
        original.setParticipantEmail("test1@example.com, test2@example.com");
        original.setMeetingDate("11/11/1111");
        original.setStartTime("11:11");
        original.setEndTime("22:22");
        original.setProjectName("Project");
        original.setMeetingNameError("Error");

        assertEquals("ModifyMeetingState{meeting name='Meeting', " +
                "participant email='[test1@example.com, test2@example.com]', " +
                "meeting date='11/11/1111', start time='11:11', " +
                "end time='22:22', project name='Project'}", original.toString());
    }
}
