package th.ac.a59070038kmitl.healthy.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.ac.a59070038kmitl.healthy.R;

public class SleepAdapter extends ArrayAdapter<Sleep> {
    Context context;
    ArrayList<Sleep> sleeps = new ArrayList<Sleep>();
    public SleepAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Sleep> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sleeps = (ArrayList<Sleep>) objects;
    }

    @Override
    public View getView(int position, @NonNull View convertView,
                        @NonNull ViewGroup parent) {
        View sleepItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_sleep_item, parent, false
        );
        TextView date = (TextView) sleepItem.findViewById(R.id.sleep_item_date);
        TextView slepttime = (TextView) sleepItem.findViewById(R.id.sleep_item_time);
        TextView duration = (TextView) sleepItem.findViewById(R.id.sleep_item_duration);

        Sleep row = sleeps.get(position);
        date.setText(row.getDate());
        slepttime.setText(row.getSleeptime());
        duration.setText(String.valueOf(row.getDuration()));

        return sleepItem;
    }
}
