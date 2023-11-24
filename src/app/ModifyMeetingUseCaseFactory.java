package app;

import entity.CommonMeetingFactory;
import entity.MeetingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.modify_meeting.ModifyMeetingController;
import interface_adapter.modify_meeting.ModifyMeetingPresenter;
import interface_adapter.modify_meeting.ModifyMeetingViewModel;
import use_case.modify_meeting.*;
import use_case.modify_meeting.ModifyMeetingDataAccessInterface;
import use_case.modify_meeting.ModifyMeetingGmailDataAccessInterface;
import view.ModifyMeetingView;

import javax.swing.*;
import java.io.IOException;

public class ModifyMeetingUseCaseFactory {

    private ModifyMeetingUseCaseFactory() {}

    public static ModifyMeetingView modifyMeetingView(ViewManagerModel viewManagerModel,
                                                ModifyMeetingViewModel modifyMeetingViewModel,
                                                ModifyMeetingDataAccessInterface userDataAccessObject,
                                                ModifyMeetingGmailDataAccessInterface gmailDataAccessObject) {
        try {
            ModifyMeetingController modifyMeetingController = modifyMeetingUseCase(viewManagerModel, modifyMeetingViewModel, userDataAccessObject, gmailDataAccessObject);
            return new ModifyMeetingView(viewManagerModel, modifyMeetingController, modifyMeetingViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid File");
        }
        return null;

    }

    private static ModifyMeetingController modifyMeetingUseCase(ViewManagerModel viewManagerModel,
                                                          ModifyMeetingViewModel modifyMeetingViewModel,
                                                          ModifyMeetingDataAccessInterface userDataAccessObject,
                                                          ModifyMeetingGmailDataAccessInterface gmailDataAccessObject) throws IOException {

        ModifyMeetingOutputBoundary modifyMeetingOutputBoundary = new ModifyMeetingPresenter(viewManagerModel, modifyMeetingViewModel);

        MeetingFactory meetingFactory = new CommonMeetingFactory();

        ModifyMeetingInputBoundary modifyMeetingInteractor = new ModifyMeetingInteractor(userDataAccessObject, gmailDataAccessObject, modifyMeetingOutputBoundary, meetingFactory);

        return new ModifyMeetingController(modifyMeetingInteractor);
    }

}
