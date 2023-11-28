package app;

import com.google.api.services.gmail.Gmail;
import interface_adapter.ViewManagerModel;
import interface_adapter.remove_email.RemoveEmailViewModel;
import interface_adapter.remove_email.RemoveEmailController;
import interface_adapter.remove_email.RemoveEmailPresenter;
import interface_adapter.remove_email.RemoveEmailViewModel;

import use_case.remove_email.RemoveEmailDataAccessInterface;
import use_case.remove_email.RemoveEmailInputBoundary;
import use_case.remove_email.RemoveEmailInteractor;
import use_case.remove_email.RemoveEmailOutputBoundary;
import view.RemoveEmailView;

import javax.swing.*;

/**
 * Factory class for constructing and initializing components related to the 'Remove Email' use case.
 * It encapsulates the complexity of creating and linking various objects necessary for the email removal functionality.
 */
public class RemoveEmailUseCaseFactory {
    /** Prevent instantiation. */
    private RemoveEmailUseCaseFactory() {}

    /**
     * Creates and returns a RemoveEmailView object.
     * This method sets up the necessary controller and view model for the Remove Email view.
     *
     * @param viewManagerModel The view manager model for managing various views.
     * @param removeEmailViewModel The view model for the Remove Email feature.
     * @param removeEmailDataAccessObject The data access object for email removal operations.
     * @return RemoveEmailView The constructed Remove Email view; returns null if initialization fails.
     */
    public static RemoveEmailView removeEmailView(
            ViewManagerModel viewManagerModel,
            RemoveEmailViewModel removeEmailViewModel,
            RemoveEmailDataAccessInterface removeEmailDataAccessObject) {

        try {
            RemoveEmailController removeEmailController = RemoveEmailUseCase(viewManagerModel, removeEmailViewModel, removeEmailDataAccessObject);
            return new RemoveEmailView(removeEmailController, removeEmailViewModel, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Remove Email feature.");
        }

        return null;
    }

    /**
     * Private helper method to create a RemoveEmailController.
     * This method initializes the controller for the Remove Email use case.
     *
     * @param viewManagerModel The view manager model for managing views.
     * @param removeEmailViewModel The view model for the Remove Email feature.
     * @param removeEmailDataAccessObject The data access object for email removal operations.
     * @return RemoveEmailController The constructed controller for Remove Email functionality.
     */
    private static RemoveEmailController RemoveEmailUseCase(
            ViewManagerModel viewManagerModel,
            RemoveEmailViewModel removeEmailViewModel,
            RemoveEmailDataAccessInterface removeEmailDataAccessObject) {

        RemoveEmailOutputBoundary removeEmailOutputBoundary = new RemoveEmailPresenter(removeEmailViewModel);

        RemoveEmailInputBoundary removeEmailInteractor = new RemoveEmailInteractor(
                removeEmailDataAccessObject, removeEmailOutputBoundary);

        return new RemoveEmailController(removeEmailInteractor);
    }
}
