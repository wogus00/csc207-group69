package use_case.add_email;

import data_access.FirebaseAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddEmailInteractorTest {

    @Test
    void successTest() {
        AddEmailInputData inputData = new AddEmailInputData("TestProject", "test@example.com");
        FirebaseAccessObject firebaseAccessObject = new FirebaseAccessObject();

        AddEmailOutputBoundary successPresenter = new AddEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(firebaseAccessObject.getProject("TestProject").getMemberEmails().contains("test@example.com"));
            }

            @Override
            public void prepareFailView(String error) {
                ;
            }
        };

        AddEmailInputBoundary interactor = new AddEmailInteractor(firebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData);
    }
}