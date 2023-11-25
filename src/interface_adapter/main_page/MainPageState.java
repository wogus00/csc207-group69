package interface_adapter.main_page;

import entity.Announcement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * State class for the main page
 * It stores data for the project logged in and email of the user
 */
public class MainPageState {
    private String projectName = "";

    private String userEmail = "";

    private String leaderEmail = "";

    private ArrayList<String> memberEmail = new ArrayList<>();

    private ArrayList<String> taskList = new ArrayList<>(Arrays.asList(""));
    private ArrayList<String> meetingList = new ArrayList<>(Arrays.asList(""));

    private ArrayList<String> announcements = new ArrayList<>(Arrays.asList("No Announcements"));

    /**
     * Creates a new MainPageState as a copy of another MainPageState.
     *
     * @param copy The MainPageState instance to copy.
     */
    public MainPageState(MainPageState copy) {
        projectName = copy.projectName;;
        userEmail = copy.userEmail;
        leaderEmail = copy.leaderEmail;
        memberEmail = copy.memberEmail;
        taskList = copy.taskList;
        meetingList = copy.meetingList;
        announcements = copy.announcements;
    }

    /**
     * Constructs a default MainPageState with initial values.
     */
    public MainPageState() {}

    /**
     * Retrieves the project name.
     *
     * @return The name of the project.
     */
    public String getProjectName() {return projectName;}

    /**
     * Sets the project name.
     *
     * @param projectName The name of the project.
     */
    public void setProjectName(String projectName) {this.projectName = projectName;}

    /**
     * Retrieves the user email.
     *
     * @return The email of the user.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the user email.
     *
     * @param userEmail The email of user.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Retrieves the email of the leader of the project.
     *
     * @return The email fo the leader.
     */
    public String getLeaderEmail() {
        return leaderEmail;
    }

    /**
     * Sets the email of the leader of the project.
     *
     * @param leaderEmail The email of the leader.
     */
    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    /**
     * Retrieves the array list of all members' (except the leader) email of the project.
     *
     * @return The array list of all members' email.
     */
    public ArrayList<String> getMemberEmail() {return memberEmail;}

    /**
     * Sets the array list of all members' (except the leader) email of the project.
     *
     * @param memberEmail The array list of all members' email.
     */
    public void setMemberEmail(ArrayList<String> memberEmail) {this.memberEmail = memberEmail;}

    /**
     * Retrieves the array list of name of tasks in the project.
     *
     * @return The array list of all task names.
     */
    public ArrayList<String> getTaskList() {return taskList;}

    /**
     * Retrieves the array list of name of tasks in the project.
     *
     * @param taskList The array list of all task names.
     */
    public void setTaskList(ArrayList<String> taskList) {this.taskList = taskList;}

    /**
     * Retrieves the array list of name of meetings in the project.
     *
     * @return The array list of all meeting names.
     */
    public ArrayList<String> getMeetingList() {return meetingList;}

    /**
     * Sets the array list of name of meetings in the project.
     *
     * @param meetingList The array list of all meeting names.
     */
    public void setMeetingList(ArrayList<String> meetingList) {this.meetingList = meetingList;}

    /**
     * Retrieves the array list of messages of all announcements in the project.
     *
     * @return The array list of all announcement messages
     */
    public ArrayList<String> getAnnouncements() {return announcements;}

    /**
     * Sets the array list of messages of all announcements in the project.
     *
     * @param announcements The array list of all announcement messages
     */
    public void setAnnouncements(ArrayList<String> announcements) {this.announcements = announcements;}

    /**
     * Returns an HTML-formatted text for a label to be displayed on the main page view.
     * The label displays a list of members, tasks, or meetings based on the inputted type.
     * It is modified to have a decent format when shown on the main page view.
     * Default value for the return text is set for no information about the type to shown
     *
     * @param type String representing the type of information to be displayed by the label.
     *             Expected values are "member", "task", or "meeting".
     * @return String representing the text for a certain label.
     */
    public String getLabel(String type) {
        List<String> list = new ArrayList<>();
        StringBuilder labelBuilder = new StringBuilder("<html>"); // Use HTML to allow for text wrapping if needed
        int charsCount = 3;
        boolean empty = false;

        if (type.equals("member")) {
            list.add(this.leaderEmail);
            list.addAll(this.memberEmail);
            labelBuilder.append("Member List: <font color='blue'>");
            charsCount += "Member List: ".length();
        } if (type.equals("task")) {
            list.addAll(this.taskList);
            labelBuilder.append("Task List: ");
            charsCount += "Task List: ".length();
        } if (type.equals("meeting")) {
            list.addAll(this.meetingList);
            labelBuilder.append("Meeting List: ");
            charsCount += "Meeting List: ".length();
        }

        final int initialCount = charsCount;
        final int maxChars = 75; // Set a maximum character limit for the JLabel


        for (String item : list) {
            if (charsCount + item.length() > maxChars) {
                if (charsCount > initialCount) {
                    labelBuilder.delete(labelBuilder.length() - 9, labelBuilder.length());
                }
                labelBuilder.append("...");
                break;
            } else {
                labelBuilder.append(item).append(", ");
                labelBuilder.append("</font>");
                charsCount += item.length() + 2; // Add 2 for the comma and space
            }
        }

        // Remove the following ", </font>" if the text length does not exceed the limit
        if (!labelBuilder.isEmpty()) {
            char lastChar = labelBuilder.charAt(labelBuilder.length() - 1);
            if ('.' != lastChar) {
                labelBuilder.delete(labelBuilder.length() - 9, labelBuilder.length());
            }
        }
        // Consider with a long name for the first task/meeting or leader email


        labelBuilder.append("</html>");
        if (list.isEmpty()) {
            if (type.equals("member")) {
                labelBuilder = new StringBuilder("<html>Member List: No members</html>");
            } if (type.equals("task")) {
                labelBuilder = new StringBuilder("<html>Task List: No Tasks</html>");
            } if (type.equals("meeting")) {
                labelBuilder = new StringBuilder("<html>Meeting List: No Meetings</html>");
            }
        }
        return labelBuilder.toString();
    }

    /**
     * Returns an HTML-formatted text for a message to be displayed as a prompt on the main page view.
     * The text displayed information about all members, tasks and meetings in the project
     * It is modified so that the text has a decent format when shown on a JOptionPane     *
     *
     * @return String representing the text for a prompt showing specific information about all members,
     * tasks and meetings in the project.
     */
    public String getShowAllMessage() {
        List<String> memberList = new ArrayList<>();
        memberList.add(this.leaderEmail + "(leader)");
        memberList.addAll(this.memberEmail);

        StringBuilder messageBuilder = new StringBuilder("<html>");
        List<List<String>> labelList = Arrays.asList(memberList, taskList, meetingList);
        for (List<String> list : labelList) {
            if (list == memberList) {
                messageBuilder.append("Members:");
            }
            if (list == taskList) {
                messageBuilder.append("Tasks:");
            }
            if (list == meetingList) {
                messageBuilder.append("Meetings:");
            }
            for (String item : list) {
                messageBuilder.append("<li>").append(item).append("</li>");
            }
            messageBuilder.append("<br/>");
        }
        messageBuilder.append("</html>");
        return messageBuilder.toString();
    }

    /**
     * Returns an HTML-formatted text for the label showing an announcement on the main page.
     * It is modified to have a decent format when shown on the main page view.
     * Default value for the return text is set for no announcements in the project
     *
     * @return String representing the text for the announcement label.
     */
    public String getAnnouncementLabel(){
        String text;
        if (this.announcements.isEmpty()) {
            text = "No announcement";
        } else {
            text = this.announcements.get(this.announcements.size() - 1);
        }

        final int maxLength = 146; // maximum length for two lines

        if (text.length() > maxLength) {
            text = text.substring(0, maxLength) + "...";
        }

        int lineBreakIndex = text.indexOf(" ", maxLength / 2);

        if (lineBreakIndex != -1 && lineBreakIndex < maxLength) {
            text = text.substring(0, lineBreakIndex) + "<br/>" + text.substring(lineBreakIndex + 1);
        }
        return "<html>Announcement:<br/>" + text + "</html>";
    }

    /**
     * Returns an HTML-formatted text for the more detailed information about announcements as a prompt
     * It is modified to have a decent format when shown as a prompt
     * Default value for the return text is set for no announcements in the project
     *
     * @return String representing the text for the announcement information on a JOptionPanel.
     */
    public String getRecentAnnouncements(){
        ArrayList<String> recents = new ArrayList<>();
        int length = this.announcements.size() - 1;
        if (length > 2) {
            length = 2;
        }
        for (int i = 0; i <= length; i++) {
            recents.add(this.announcements.get(length - i));
        }
        while (recents.size() < 3) {
            recents.add("");
        }

        return String.format("<html>%s<br/>%s<br/>%s</html>", "- " + recents.get(0), "- " + recents.get(1), "- " + recents.get(2));

    }

    public void addAnnouncement(String announcementTitle){this.announcements.add(announcementTitle);}
}
