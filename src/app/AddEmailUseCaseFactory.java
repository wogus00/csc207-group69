package app;

import com.google.api.services.gmail.Gmail;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_email.AddEmailViewModel;
import interface_adapter.add_email.AddEmailController;
import interface_adapter.add_email.AddEmailPresenter;
import interface_adapter.add_email.AddEmailViewModel;

import interface_adapter.main_page.MainPageViewModel;
import use_case.add_email.AddEmailDataAccessInterface;
import use_case.add_email.AddEmailInputBoundary;
import use_case.add_email.AddEmailInteractor;
import use_case.add_email.AddEmailOutputBoundary;
import view.AddEmailView;

import javax.swing.*;

/**
 * A factory class for constructing and initializing components related to the 'Add Email' use case.
 * This class facilitates the creation of the necessary objects for adding email functionality,
 * encapsulating the complex construction logic.
 */
public class AddEmailUseCaseFactory {
    /** Prevent instantiation. */
    private AddEmailUseCaseFactory() {}

    /**
     * Creates and returns an AddEmailView object.
     * This method sets up the necessary controller and view model for the Add Email view.
     *
     * @param viewManagerModel The view manager model for managing various views.
     * @param addEmailViewModel The view model for the Add Email feature.
     * @param addEmailDataAccessObject The data access object for email addition operations.
     * @return AddEmailView The constructed Add Email view; returns null if initialization fails.
     */
    public static AddEmailView addEmailView(
            ViewManagerModel viewManagerModel,
            AddEmailViewModel addEmailViewModel,
            AddEmailDataAccessInterface addEmailDataAccessObject,
            MainPageViewModel mainPageViewModel) {

        try {
            AddEmailController deleteAnnouncementController = AddEmailUseCase(viewManagerModel, addEmailViewModel, addEmailDataAccessObject, mainPageViewModel);
            return new AddEmailView(deleteAnnouncementController, addEmailViewModel, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Add Email feature.");
        }

        return null;
    }

    /**
     * Private helper method to create an AddEmailController.
     * This method initializes the controller for the Add Email use case.
     *
     * @param viewManagerModel The view manager model for managing views.
     * @param addEmailViewModel The view model for the Add Email feature.
     * @param addEmailDataAccessObject The data access object for email addition operations.
     * @return AddEmailController The constructed controller for Add Email functionality.
     */
    private static AddEmailController AddEmailUseCase(
            ViewManagerModel viewManagerModel,
            AddEmailViewModel addEmailViewModel,
            AddEmailDataAccessInterface addEmailDataAccessObject,
            MainPageViewModel mainPageViewModel) {

        AddEmailOutputBoundary addEmailOutputBoundary = new AddEmailPresenter(addEmailViewModel, viewManagerModel, mainPageViewModel);

        AddEmailInputBoundary addEmailInteractor = new AddEmailInteractor(
                addEmailDataAccessObject, addEmailOutputBoundary);

        return new AddEmailController(addEmailInteractor);
    }
}
