package th.ac.a59070038kmitl.healthy.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimestamp {
    long dateTime;


    public DateTimestamp() {
    }


    public long DateTimestamp(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            Date dateformat = sdf.parse(date);
            sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
            dateTime = Integer.parseInt(sdf.format(dateformat));

        }catch(Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }
}
