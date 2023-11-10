package use_case.add_email;

public interface AddEmailOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}