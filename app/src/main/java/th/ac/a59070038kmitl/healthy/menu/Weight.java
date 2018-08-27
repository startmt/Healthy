package th.ac.a59070038kmitl.healthy.menu;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class Weight {
    String date;
    int weight;
    String status;
    public Weight() {
    }
    public Weight( String date, int weight, String status ){
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public int getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

}
