package th.ac.a59070038kmitl.healthy.menu;

import java.util.Date;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class Weight {
    String date;
    long dateTimestamp;
    int weight;
    String status;
    public Weight() {
    }
    public Weight(String date,long dateTimestamp, int weight, String status ){
        this.date = date;
        this.dateTimestamp = dateTimestamp;
        this.weight = weight;
        this.status = status;
    }
    public  String getDate(){
        return date;
    }

    public long getDateTimestamp() {
        return dateTimestamp;
    }

    public int getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateTimestamp(long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
