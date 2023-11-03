package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData project);

    void prepareFailView(String error);
}