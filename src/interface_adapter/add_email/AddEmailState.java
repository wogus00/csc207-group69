package interface_adapter.add_email;

public class AddEmailState {
    private String error;
    public AddEmailState(AddEmailState copy) {
        this.error = copy.error;
    }
    public AddEmailState() {}

    public void setError(String error) {
        this.error = error;
    }
}
