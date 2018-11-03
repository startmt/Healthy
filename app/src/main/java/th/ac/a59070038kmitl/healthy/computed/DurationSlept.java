package th.ac.a59070038kmitl.healthy.computed;

import android.util.Log;

import static java.lang.Math.abs;

public class DurationSlept {
    private int h1;
    private int h2;
    private int m1;
    private int m2;
    private String durationtime;
    public  DurationSlept(String time1, String time2){
        String hour;
        String minute;
        String[] time = time1.split(":");
        this.h1 = Integer.parseInt(time[0]);
        this.m1 = Integer.parseInt(time[1]);
        time = time2.split(":");
        this.h2 = Integer.parseInt(time[0]);
        this.m2 = Integer.parseInt(time[1]);
        int hourandmin1 = (h1 * 60) + m1;
        int hourandmin2 = (h2 * 60) + m2;
        if (hourandmin1 > hourandmin2){
            hour = String.valueOf(Math.round((1440 - (hourandmin1 - hourandmin2)) / 60));
            minute = String.valueOf(Math.round((1440 - (hourandmin1 - hourandmin2))) % 60);
            this.durationtime = hour + "." + minute;
        }
        else {
            hour = String.valueOf(Math.round((hourandmin2 - hourandmin1) / 60));
            minute = String.valueOf(Math.round((hourandmin2 - hourandmin1)) % 60);
            this.durationtime = hour + "." + minute;
        }
    }

    public String  getDurationtime() {
        return durationtime;
    }
}
