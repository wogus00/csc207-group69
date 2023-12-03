package data_access;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

public class ProjectInfoAccessorImplementationTest {

    @Mock
    private FirebaseAccessObject mockFirebaseAccessObject;

    @Mock
    private AnnouncementMessageListGetter mockAnnouncementMessageListGetter;

    @Mock
    private TaskListGetter mockTaskListGetter;

    @Mock
    private MeetingListGetter mockMeetingListGetter;

    private ProjectInfoAccessorImplementation projectInfoAccessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        projectInfoAccessor = new ProjectInfoAccessorImplementation("TestProject", mockFirebaseAccessObject);
        projectInfoAccessor.announcementMessageListGetter = mockAnnouncementMessageListGetter;
        projectInfoAccessor.taskListGetter = mockTaskListGetter;
        projectInfoAccessor.meetingListGetter = mockMeetingListGetter;

        // Setup mock responses
        when(mockAnnouncementMessageListGetter.getInfoList(anyString(), any(FirebaseAccessObject.class)))
                .thenReturn(new ArrayList<>(Arrays.asList("Announcement Message 1", "Announcement Message 2")));
        when(mockTaskListGetter.getInfoList(anyString(), any(FirebaseAccessObject.class)))
                .thenReturn(new ArrayList<>(Arrays.asList("Task 1", "Task 2")));
        when(mockMeetingListGetter.getInfoList(anyString(), any(FirebaseAccessObject.class)))
                .thenReturn(new ArrayList<>(Arrays.asList("Meeting 1", "Meeting 2")));
    }

    @Test
    public void testGetAnnouncementInfoList() {
        ArrayList<String> result = projectInfoAccessor.getAnnouncementInfoList();
        verify(mockAnnouncementMessageListGetter).getInfoList("TestProject", mockFirebaseAccessObject);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Announcement Message 1", result.get(0));
        assertEquals("Announcement Message 2", result.get(1));
    }

    @Test
    public void testGetTaskInfoList() {
        ArrayList<String> result = projectInfoAccessor.getTaskInfoList();
        verify(mockTaskListGetter).getInfoList("TestProject", mockFirebaseAccessObject);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Arrays.asList("Task 1", "Task 2"), result);
    }

    @Test
    public void testGetMeetingInfoList() {
        ArrayList<String> result = projectInfoAccessor.getMeetingInfoList();
        verify(mockMeetingListGetter).getInfoList("TestProject", mockFirebaseAccessObject);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Arrays.asList("Meeting 1", "Meeting 2"), result);
    }

    // Similar tests for getMeetingInfoList and getTaskInfoList
    // ...
}
