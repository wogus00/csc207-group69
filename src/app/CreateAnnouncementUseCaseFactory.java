package app;

import entity.AnnouncementFactory;
import entity.CommonAnnouncementFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_announcement.CreateAnnouncementController;
import interface_adapter.create_announcement.CreateAnnouncementPresenter;
import interface_adapter.create_announcement.CreateAnnouncementViewModel;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_announcement.CreateAnnouncementDataAccessInterface;
import use_case.create_announcement.CreateAnnouncementInputBoundary;
import use_case.create_announcement.CreateAnnouncementInteractor;
import use_case.create_announcement.CreateAnnouncementOutputBoundary;
import view.CreateAnnouncementView;

import javax.swing.*;
import java.io.IOException;

public class CreateAnnouncementUseCaseFactory {

    private CreateAnnouncementUseCaseFactory() {}

    public static CreateAnnouncementView createAnnouncementView(ViewManagerModel viewManagerModel,
                                                                CreateAnnouncementViewModel createAnnouncementViewModel,
                                                                CreateAnnouncementDataAccessInterface announcementDataAccessObject,
                                                                MainPageViewModel mainPageViewModel) {
        try {
            CreateAnnouncementController createAnnouncementController = createAnnouncementUseCase(viewManagerModel, createAnnouncementViewModel, announcementDataAccessObject, mainPageViewModel);
            return new CreateAnnouncementView(createAnnouncementController, createAnnouncementViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid announcement");
        }
        return null;
    }

    private static CreateAnnouncementController createAnnouncementUseCase(ViewManagerModel viewManagerModel,
                                                                          CreateAnnouncementViewModel createAnnouncementViewModel,
                                                                          CreateAnnouncementDataAccessInterface announcementDataAccessObject,
                                                                          MainPageViewModel mainPageViewModel) throws IOException {
        CreateAnnouncementOutputBoundary createAnnouncementOutputBoundary = new CreateAnnouncementPresenter(viewManagerModel, createAnnouncementViewModel, mainPageViewModel);

        AnnouncementFactory announcementFactory = new CommonAnnouncementFactory();

        CreateAnnouncementInputBoundary createAnnouncementInteractor = new CreateAnnouncementInteractor(announcementDataAccessObject, createAnnouncementOutputBoundary, announcementFactory);

        return new CreateAnnouncementController(createAnnouncementInteractor);
    }
}
