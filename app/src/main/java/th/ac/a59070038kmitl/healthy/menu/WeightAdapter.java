package th.ac.a59070038kmitl.healthy.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import th.ac.a59070038kmitl.healthy.R;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class WeightAdapter extends ArrayAdapter<Weight>{
    Context context;
    ArrayList<Weight> weights = new ArrayList<Weight>();
    public WeightAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Weight> objects) {
        super(context, resource, objects);
        this.context = context;
        this.weights = (ArrayList<Weight>) objects;
    }
    @NonNull
    public View getView(int position, @NonNull View convertView,
                       @NonNull ViewGroup parent){
        Collections.reverse(weights);
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weigth_item,
                parent,
                false);

        TextView date = (TextView) weightItem.findViewById(R.id.weight_item_date);
        TextView weight = (TextView) weightItem.findViewById(R.id.weight_item_weight);
        TextView status = (TextView) weightItem.findViewById(R.id.weight_item_status);

        Weight row = weights.get(position);

        Log.d("WEIGHTAD", String.valueOf(position));
        Log.d("WEIGHTAD", row.getDate());
        Log.d("WEIGHTAD", String.valueOf(row.getWeight()));
        Log.d("WEIGHTAD", row.getStatus());
        date.setText(row.getDate());
        weight.setText(String.valueOf(row.getWeight()));
        status.setText(row.getStatus());

        return weightItem;
    }
}
