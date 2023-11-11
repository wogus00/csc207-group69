package app;

import entity.CommonMeetingFactory;
import entity.MeetingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_meeting.CreateMeetingController;
import interface_adapter.create_meeting.CreateMeetingPresenter;
import interface_adapter.create_meeting.CreateMeetingViewModel;
import use_case.create_meeting.*;
import view.CreateMeetingView;

import javax.swing.*;
import java.io.IOException;

public class CreateMeetingUseCaseFactory {
    private CreateMeetingUseCaseFactory() {}

    public static CreateMeetingView createMeetingView(ViewManagerModel viewManagerModel,
                                                CreateMeetingViewModel createMeetingViewModel,
                                                CreateMeetingDataAccessInterface userDataAccessObject,
                                                CreateMeetingGmailDataAccessInterface gmailDataAccessObject) {
        try {
            CreateMeetingController createMeetingController = createMeetingUseCase(viewManagerModel, createMeetingViewModel, userDataAccessObject, gmailDataAccessObject);
            return new CreateMeetingView(createMeetingController, createMeetingViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static CreateMeetingController createMeetingUseCase(ViewManagerModel viewManagerModel,
                                                          CreateMeetingViewModel createMeetingViewModel,
                                                          CreateMeetingDataAccessInterface createMeetingDataAccessObject,
                                                          CreateMeetingGmailDataAccessInterface gmailDataAccessObject) throws IOException {

        CreateMeetingOutputBoundary createMeetingOutputBoundary = new CreateMeetingPresenter(viewManagerModel, createMeetingViewModel);

        MeetingFactory meetingFactory = new CommonMeetingFactory();

        CreateMeetingInputBoundary createMeetingInteractor = new CreateMeetingInteractor(createMeetingDataAccessObject, gmailDataAccessObject, createMeetingOutputBoundary, meetingFactory);

        return new CreateMeetingController(createMeetingInteractor);
    }
}
