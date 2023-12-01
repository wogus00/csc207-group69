package app;


import com.google.api.services.gmail.Gmail;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.delete_announcement.DeleteAnnouncementController;
import interface_adapter.delete_announcement.DeleteAnnouncementPresenter;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.delete_announcement.DeleteAnnouncementDataAccessInterface;
import use_case.delete_announcement.DeleteAnnouncementInputBoundary;
import use_case.delete_announcement.DeleteAnnouncementInteractor;
import use_case.delete_announcement.DeleteAnnouncementOutputBoundary;
import view.DeleteAnnouncementView;

import javax.swing.*;

/**
 * A factory class for creating and configuring instances related to the Delete Announcement use case.
 * This class provides static methods to create and setup the necessary components for deleting announcements,
 * including the View, Controller, and Interactor.
 */
public class DeleteAnnouncementUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private DeleteAnnouncementUseCaseFactory() {}

    /**
     * Creates and returns a {@link DeleteAnnouncementView} configured with the necessary controller
     * and view model.
     *
     * @param viewManagerModel The model for managing different views in the application.
     * @param deleteAnnouncementViewModel The view model for deleting announcements.
     * @param deleteAnnouncementDataAccessObject The data access object for announcements.
     * @param createAnnouncementViewModel The create announcement view model of the application.
     * @return A configured instance of {@link DeleteAnnouncementView}.
     */
    public static DeleteAnnouncementView createDeleteAnnouncementView(
            ViewManagerModel viewManagerModel,
            DeleteAnnouncementViewModel deleteAnnouncementViewModel,
            DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
            CreateAnnouncementViewModel createAnnouncementViewModel,
            MainPageViewModel mainPageViewModel) {

        try {
            DeleteAnnouncementController deleteAnnouncementController = DeleteAnnouncementUseCase(viewManagerModel, deleteAnnouncementViewModel, deleteAnnouncementDataAccessObject, createAnnouncementViewModel, mainPageViewModel);
            return new DeleteAnnouncementView(deleteAnnouncementController, deleteAnnouncementViewModel, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Delete Announcement feature.");
        }

        return null;
    }

    /**
     * Creates and configures a {@link DeleteAnnouncementController} for handling the delete announcement operations.
     *
     * @param viewManagerModel The model for managing different views in the application.
     * @param deleteAnnouncementViewModel The view model for deleting announcements.
     * @param deleteAnnouncementDataAccessObject The data access object for announcements.
     * @param createAnnouncementViewModel The create announcement view model.
     * @return A configured instance of {@link DeleteAnnouncementController}.
     */
    private static DeleteAnnouncementController DeleteAnnouncementUseCase(
            ViewManagerModel viewManagerModel,
            DeleteAnnouncementViewModel deleteAnnouncementViewModel,
            DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
            CreateAnnouncementViewModel createAnnouncementViewModel,
            MainPageViewModel mainPageViewModel) {

        DeleteAnnouncementOutputBoundary deleteAnnouncementOutputBoundary = new DeleteAnnouncementPresenter(deleteAnnouncementViewModel, createAnnouncementViewModel,viewManagerModel, mainPageViewModel);

        DeleteAnnouncementInputBoundary deleteAnnouncementInteractor = new DeleteAnnouncementInteractor(
                deleteAnnouncementDataAccessObject, deleteAnnouncementOutputBoundary);

        return new DeleteAnnouncementController(deleteAnnouncementInteractor);
    }
}
