package app;

import entity.AnnouncementFactory;
import entity.CommonAnnouncementFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementController;
import interface_adapter.create_announcement.CreateAnnouncementPresenter;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_announcement.*;
import view.CreateAnnouncementView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for setting up and providing instances related to the Create Announcement use case.
 * This class offers static methods to create and configure the necessary components for the
 * creation of announcements, including the View, Controller, and Interactor.
 */
public class CreateAnnouncementUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CreateAnnouncementUseCaseFactory() {}

    /**
     * Creates and returns a {@link CreateAnnouncementView} configured with the necessary controller
     * and view model.
     *
     * @param viewManagerModel The model for managing different views in the application.
     * @param createAnnouncementViewModel The view model for creating announcements.
     * @param announcementDataAccessObject The data access object for announcements.
     * @param gmailDataAccessObject The data access object for emails about announcements.
     * @param mainPageViewModel The main page view model of the application.
     * @return A configured instance of {@link CreateAnnouncementView}.
     */
    public static CreateAnnouncementView createAnnouncementView(ViewManagerModel viewManagerModel,
                                                                CreateAnnouncementViewModel createAnnouncementViewModel,
                                                                CreateAnnouncementDataAccessInterface announcementDataAccessObject,
                                                                CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject,
                                                                MainPageViewModel mainPageViewModel) {
        try {
            CreateAnnouncementController createAnnouncementController = createAnnouncementUseCase(
                    viewManagerModel,
                    createAnnouncementViewModel,
                    announcementDataAccessObject,
                    gmailDataAccessObject,
                    mainPageViewModel);
            return new CreateAnnouncementView(createAnnouncementController, createAnnouncementViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid announcement");
        }
        return null;
    }


    /**
     * Creates and returns a {@link CreateAnnouncementController} configured with the necessary
     * interactor, view model, and data access object.
     *
     * @param viewManagerModel The model for managing different views in the application.
     * @param createAnnouncementViewModel The view model for creating announcements.
     * @param announcementDataAccessObject The data access object for announcements.
     * @param gmailDataAccessObject The data access object for Gmail object.
     * @param mainPageViewModel The main page view model of the application.
     * @return A configured instance of {@link CreateAnnouncementController}.
     * @throws IOException If an I/O error occurs.
     */
    private static CreateAnnouncementController createAnnouncementUseCase(ViewManagerModel viewManagerModel,
                                                                          CreateAnnouncementViewModel createAnnouncementViewModel,
                                                                          CreateAnnouncementDataAccessInterface announcementDataAccessObject,
                                                                          CreateAnnouncementGmailDataAccessInterface gmailDataAccessObject,
                                                                          MainPageViewModel mainPageViewModel) throws IOException {
        CreateAnnouncementOutputBoundary createAnnouncementOutputBoundary = new CreateAnnouncementPresenter(viewManagerModel, createAnnouncementViewModel, mainPageViewModel);

        AnnouncementFactory announcementFactory = new CommonAnnouncementFactory();

        CreateAnnouncementInputBoundary createAnnouncementInteractor = new CreateAnnouncementInteractor(announcementDataAccessObject, createAnnouncementOutputBoundary, announcementFactory, gmailDataAccessObject);

        return new CreateAnnouncementController(createAnnouncementInteractor);
    }
}
