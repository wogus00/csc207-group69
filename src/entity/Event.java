package entity;

public class Event {

    private String name;
    private User responsibility;
    private String detail;
    private String start_end_time;

    public Event(String name, User responsibility, String detail, String start_end_time){
        this.name = name;
        this.responsibility = responsibility;
        this.detail = detail;
        this.start_end_time = start_end_time;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setResponsibility(User responsibility){
        this.responsibility = responsibility;
    }

    public User getResponsibility(){
        return this.responsibility;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getDetail(){
        return this.detail;
    }

    public void setStartEndTime(String start_end_time){
        this.start_end_time = start_end_time;
    }

    public String getStartEndTime(){
        return this.start_end_time;
    }
}
