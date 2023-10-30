package entity;

public class Calendar {
    private String date;

    private String meeting;

    private Event event;

    public Calendar(String date, String meeting, Event event){
        this.date = date;
        this.meeting = meeting;
        this.event = event;
    }

    public void changeDate(String date){
        this.date = date;
    }
}
