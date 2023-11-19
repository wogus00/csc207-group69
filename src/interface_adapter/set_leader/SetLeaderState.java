package interface_adapter.set_leader;

import interface_adapter.set_leader.SetLeaderState;

public class SetLeaderState {
    private String error;
    public SetLeaderState(SetLeaderState copy) {
        this.error = copy.error;
    }
    public SetLeaderState() {}

    public void setError(String error) {
        this.error = error;
    }
}
