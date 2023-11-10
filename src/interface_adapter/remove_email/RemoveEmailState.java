package interface_adapter.remove_email;

import interface_adapter.add_email.AddEmailState;

public class RemoveEmailState {
    private String error;
    public RemoveEmailState(RemoveEmailState copy) {
        this.error = copy.error;
    }
    public RemoveEmailState() {}

    public void setError(String error) {
        this.error = error;
    }
}
