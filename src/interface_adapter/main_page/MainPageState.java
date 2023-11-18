package interface_adapter.main_page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPageState {
    private String projectName = "";

    private String userEmail = "";

    private String leaderEmail = "";

    private ArrayList<String> memberEmail = new ArrayList<>();

    private ArrayList<String> taskList = new ArrayList<>(Arrays.asList(""));
    private ArrayList<String> meetingList = new ArrayList<>(Arrays.asList(""));

    private ArrayList<String> announcements = new ArrayList<>(Arrays.asList("No Announcements"));
    public MainPageState(MainPageState copy) {
        projectName = copy.projectName;;
        userEmail = copy.userEmail;
        leaderEmail = copy.leaderEmail;
        memberEmail = copy.memberEmail;
        taskList = copy.taskList;
        meetingList = copy.meetingList;
        announcements = copy.announcements;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public MainPageState() {}

    public String getProjectName() {return projectName;}

    public void setProjectName(String projectName) {this.projectName = projectName;}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLeaderEmail() {
        return leaderEmail;
    }

    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    public ArrayList<String> getMemberEmail() {return memberEmail;}

    public void setMemberEmail(ArrayList<String> memberEmail) {this.memberEmail = memberEmail;}

    public ArrayList<String> getTaskList() {return taskList;}

    public void setTaskList(ArrayList<String> taskList) {this.taskList = taskList;}

    public ArrayList<String> getMeetingList() {return meetingList;}

    public void setMeetingList(ArrayList<String> meetingList) {this.meetingList = meetingList;}

    public ArrayList<String> getAnnouncements() {return announcements;}

    public void setAnnouncements(ArrayList<String> announcements) {this.announcements = announcements;}

    public String getLabel(String type) {
        List<String> list = new ArrayList<>();
        StringBuilder labelBuilder = new StringBuilder("<html>"); // Use HTML to allow for text wrapping if needed
        int charsCount = 3;

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
        return labelBuilder.toString();
    }

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

    public String getAnnouncementLabel(){
        String text = this.announcements.get(this.announcements.size() - 1);

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

}
