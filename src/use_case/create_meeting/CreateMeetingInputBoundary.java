package use_case.create_meeting;

import java.util.concurrent.ExecutionException;

public interface CreateMeetingInputBoundary {
    void execute(CreateMeetingInputData createMeetingInputData) throws ExecutionException, InterruptedException;
}
