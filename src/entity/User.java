package entity;

public class User {
    private String id;
    private String role;
    private String username;
    private String email;
    private String password;
    private String date_of_meeting;

    public User(String id, String role, String username, String email, String password, String date_of_meeting){
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date_of_meeting = date_of_meeting;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for date_of_meeting
    public String getDateOfMeeting() {
        return date_of_meeting;
    }
}
