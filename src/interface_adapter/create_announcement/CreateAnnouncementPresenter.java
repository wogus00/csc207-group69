package interface_adapter.create_announcement;


import interface_adapter.ViewManagerModel;
import interface_adapter.main_page.MainPageState;
import interface_adapter.main_page.MainPageViewModel;
import use_case.create_announcement.CreateAnnouncementOutputBoundary;
import use_case.create_announcement.CreateAnnouncementOutputData;

/**
 * Presenter for the Create Announcement feature.
 * It takes the response from the use case and updates the view model and view state accordingly.
 */
public class CreateAnnouncementPresenter implements CreateAnnouncementOutputBoundary  {

    private final CreateAnnouncementViewModel createAnnouncementViewModel;

    private ViewManagerModel viewManagerModel;

    private MainPageViewModel mainPageViewModel;

    /**
     * Constructs a CreateAnnouncementPresenter with the necessary view models and manager.
     *
     * @param viewManagerModel               The model managing the views.
     * @param createAnnouncementViewModel    The view model for creating announcements.
     * @param mainPageViewModel              The view model for the main page.
     */
    public CreateAnnouncementPresenter(ViewManagerModel viewManagerModel,
                                       CreateAnnouncementViewModel createAnnouncementViewModel,
                                       MainPageViewModel mainPageViewModel) {
        this.createAnnouncementViewModel = createAnnouncementViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainPageViewModel = mainPageViewModel;
    }

    /**
     * Prepares the success view after an announcement has been created.
     *
     * @param response The output data from the create announcement use case.
     */
    @Override
    public void prepareSuccessView(CreateAnnouncementOutputData response) {
        // on Success, switch to the dashboard
        MainPageState mainPageState = new MainPageState();
        mainPageState.addAnnouncement(response.getAnnouncementTitle());
        mainPageViewModel.setState(mainPageState);
        viewManagerModel.setActiveView(createAnnouncementViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view when the announcement creation fails.
     *
     * @param error A string describing the error encountered during announcement creation.
     */
    @Override
    public void prepareFailView(String error) {
        CreateAnnouncementState createAnnouncementState = createAnnouncementViewModel.getState();
        createAnnouncementState.setAnnouncementTitleError(error);
        createAnnouncementViewModel.firePropertyChanged();
    }
}
