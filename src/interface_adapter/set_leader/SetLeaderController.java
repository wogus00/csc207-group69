package interface_adapter.set_leader;

import use_case.set_leader.SetLeaderInputBoundary;

public class SetLeaderController {
    private final SetLeaderInputBoundary setLeaderUseCaseInteractor;

    public SetLeaderController(SetLeaderInputBoundary setLeaderUseCaseInteractor) {
        this.setLeaderUseCaseInteractor = setLeaderUseCaseInteractor;
    }

    // You can add methods related to setting a leader here as needed
}
