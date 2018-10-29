package th.ac.a59070038kmitl.healthy.menu;

public class Sleep {
    private String date;
    private String sleeptime;
    private String duration;

    public Sleep(){

    }
    public Sleep (String date, String sleeptime, String duration){
        this.date = date;
        this.sleeptime = sleeptime;
        this.duration = duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public String getSleeptime() {
        return sleeptime;
    }

    public String getDuration() {
        return duration;
    }
}
