package app;


import com.google.api.services.gmail.Gmail;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.delete_announcement.DeleteAnnouncementController;
import interface_adapter.delete_announcement.DeleteAnnouncementPresenter;
import interface_adapter.delete_announcement.DeleteAnnouncementViewModel;
import use_case.delete_announcement.DeleteAnnouncementDataAccessInterface;
import use_case.delete_announcement.DeleteAnnouncementInputBoundary;
import use_case.delete_announcement.DeleteAnnouncementInteractor;
import use_case.delete_announcement.DeleteAnnouncementOutputBoundary;
import view.DeleteAnnouncementView;

import javax.swing.*;

public class DeleteAnnouncementUseCaseFactory {

    /** Prevent instantiation. */
    private DeleteAnnouncementUseCaseFactory() {}

    public static DeleteAnnouncementView createDeleteAnnouncementView(
            ViewManagerModel viewManagerModel,
            DeleteAnnouncementViewModel deleteAnnouncementViewModel,
            DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
            CreateAnnouncementViewModel createAnnouncementViewModel) {

        try {
            DeleteAnnouncementController deleteAnnouncementController = DeleteAnnouncementUseCase(viewManagerModel, deleteAnnouncementViewModel, deleteAnnouncementDataAccessObject, createAnnouncementViewModel);
            return new DeleteAnnouncementView(deleteAnnouncementController, deleteAnnouncementViewModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing Delete Announcement feature.");
        }

        return null;
    }

    private static DeleteAnnouncementController DeleteAnnouncementUseCase(
            ViewManagerModel viewManagerModel,
            DeleteAnnouncementViewModel deleteAnnouncementViewModel,
            DeleteAnnouncementDataAccessInterface deleteAnnouncementDataAccessObject,
            CreateAnnouncementViewModel createAnnouncementViewModel) {

        DeleteAnnouncementOutputBoundary deleteAnnouncementOutputBoundary = new DeleteAnnouncementPresenter(deleteAnnouncementViewModel, createAnnouncementViewModel,viewManagerModel);

        DeleteAnnouncementInputBoundary deleteAnnouncementInteractor = new DeleteAnnouncementInteractor(
                deleteAnnouncementDataAccessObject, deleteAnnouncementOutputBoundary);

        return new DeleteAnnouncementController(deleteAnnouncementInteractor);
    }
}
