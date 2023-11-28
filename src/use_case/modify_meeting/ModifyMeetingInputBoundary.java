package use_case.modify_meeting;

import java.util.concurrent.ExecutionException;

public interface ModifyMeetingInputBoundary {
    void execute(ModifyMeetingInputData modifyMeetingInputData) throws ExecutionException, InterruptedException;
}
