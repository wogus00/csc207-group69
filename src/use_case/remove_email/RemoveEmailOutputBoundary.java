package use_case.remove_email;

public interface RemoveEmailOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
