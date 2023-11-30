package app;

import com.google.api.services.gmail.Gmail;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageViewModel;
import interface_adapter.set_leader.SetLeaderViewModel;
import interface_adapter.set_leader.SetLeaderController;
import interface_adapter.set_leader.SetLeaderPresenter;
import interface_adapter.set_leader.SetLeaderViewModel;

import use_case.set_leader.SetLeaderDataAccessInterface;
import use_case.set_leader.SetLeaderInputBoundary;
import use_case.set_leader.SetLeaderInteractor;
import use_case.set_leader.SetLeaderOutputBoundary;
import view.SetLeaderView;

import javax.swing.*;

/**
 * Factory class for constructing and initializing components related to the 'Set Leader' use case.
 * It encapsulates the complexity of creating and linking various objects necessary for setting a leader in the system.
 */
public class SetLeaderUseCaseFactory {
    /** Prevent instantiation. */
    private SetLeaderUseCaseFactory() {}

    /**
     * Creates and returns a SetLeaderView object.
     * This method sets up the necessary controller and view model for the Set Leader view.
     *
     * @param viewManagerModel The view manager model for managing various views.
     * @param setLeaderViewModel The view model for the Set Leader feature.
     * @param setLeaderDataAccessObject The data access object for leader setting operations.
     * @return SetLeaderView The constructed Set Leader view; returns null if initialization fails.
     */
    public static SetLeaderView setLeaderView(
            ViewManagerModel viewManagerModel,
            SetLeaderViewModel setLeaderViewModel,
            SetLeaderDataAccessInterface setLeaderDataAccessObject,
            MainPageViewModel mainPageViewModel) {

        try {
            SetLeaderController setLeaderController = SetLeaderUseCase(viewManagerModel, setLeaderViewModel, setLeaderDataAccessObject, mainPageViewModel);
            return new SetLeaderView(setLeaderController, setLeaderViewModel, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Set Leader feature.");
        }

        return null;
    }

    /**
     * Private helper method to create a SetLeaderController.
     * This method initializes the controller for the Set Leader use case.
     *
     * @param viewManagerModel The view manager model for managing views.
     * @param setLeaderViewModel The view model for the Set Leader feature.
     * @param setLeaderDataAccessObject The data access object for leader setting operations.
     * @return SetLeaderController The constructed controller for Set Leader functionality.
     */
    private static SetLeaderController SetLeaderUseCase(
            ViewManagerModel viewManagerModel,
            SetLeaderViewModel setLeaderViewModel,
            SetLeaderDataAccessInterface setLeaderDataAccessObject,
            MainPageViewModel mainPageViewModel) {

        SetLeaderOutputBoundary setLeaderOutputBoundary = new SetLeaderPresenter(setLeaderViewModel, viewManagerModel, mainPageViewModel);

        SetLeaderInputBoundary setLeaderInteractor = new SetLeaderInteractor(
                setLeaderDataAccessObject, setLeaderOutputBoundary);

        return new SetLeaderController(setLeaderInteractor);
    }
}
