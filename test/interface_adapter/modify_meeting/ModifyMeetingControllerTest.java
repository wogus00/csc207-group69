package interface_adapter.modify_meeting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.modify_meeting.ModifyMeetingInputBoundary;
import use_case.modify_meeting.ModifyMeetingInputData;

import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class ModifyMeetingControllerTest {

    @Mock
    private ModifyMeetingInputBoundary mockInteractor;

    private ModifyMeetingController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ModifyMeetingController(mockInteractor);
    }

    @Test
    public void testExecute_Successful() throws Exception {
        // Arrange
        String meetingName = "Meeting";
        ArrayList<String> participantEmail = new ArrayList<>(Arrays.asList("test1@example.com", "test2@example.com"));
        String meetingDate = "11/11/1111";
        String startTime = "11:11";
        String endTime = "22:22";
        String projectName = "Project";

        // Act
        controller.execute(meetingName, participantEmail, meetingDate, startTime, endTime, projectName);

        // Assert
        verify(mockInteractor).execute(any(ModifyMeetingInputData.class));
    }
}
