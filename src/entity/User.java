package entity;

public class User {
    private final String id;
    private String role;
    private String username;
    private final String email;
    private String password;
    private String date;

    public User(String id, String role, String username, String email, String password, String date){
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getUserame() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public String getDate() {
        return date;
    }
}
