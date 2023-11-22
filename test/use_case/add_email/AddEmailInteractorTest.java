package use_case.add_email;

import data_access.FirebaseAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddEmailInteractorTest {

    FirebaseAccessObject firebaseAccessObject = new FirebaseAccessObject();

    @Test
    void successTest() {
        AddEmailInputData inputData = new AddEmailInputData("TestProject", "test@example.com");

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

    @Test
    void caseSensitivityTest() {
        String email1 = "Abc@gmail.com";
        String email2 = "abc@gmail.com";

        AddEmailInputData inputData1 = new AddEmailInputData("TestProject", email1);
        AddEmailInputData inputData2 = new AddEmailInputData("TestProject", email2);

        AddEmailOutputBoundary successPresenter = new AddEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Check both emails are considered distinct and added
                assertTrue(firebaseAccessObject.getProject("TestProject").getMemberEmails().contains(email1));
                assertTrue(firebaseAccessObject.getProject("TestProject").getMemberEmails().contains(email2));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Addition of emails failed");
            }
        };

        AddEmailInteractor interactor = new AddEmailInteractor(firebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData1);
        interactor.updateProjectDetails(inputData2);
    }

    @Test
    void addEmailToEmptyListTest() {
        String email = "newmember@example.com";
        AddEmailInputData inputData = new AddEmailInputData("NewProject", email);

        // Assuming the 'NewProject' initially has no members
        assertTrue(firebaseAccessObject.getProject("NewProject").getMemberEmails().isEmpty());

        AddEmailOutputBoundary successPresenter = new AddEmailOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Verify the email is added to the previously empty list
                assertTrue(firebaseAccessObject.getProject("NewProject").getMemberEmails().contains(email));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Addition of email to empty list failed");
            }
        };

        AddEmailInteractor interactor = new AddEmailInteractor(firebaseAccessObject, successPresenter);
        interactor.updateProjectDetails(inputData);
    }

}