package use_case.set_leader;

public interface SetLeaderOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
