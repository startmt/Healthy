package th.ac.a59070038kmitl.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import th.ac.a59070038kmitl.healthy.menu.Weight;
import th.ac.a59070038kmitl.healthy.menu.WeightAdapter;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class WeightFragment extends Fragment{

    ArrayList<Weight> weight = new ArrayList<Weight>();

    public WeightFragment(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        weight.add(new Weight("01 Jan 2018", 63, "UP"));


        ListView weightList = (ListView) getView().findViewById(R.id.weight_list);
        WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weigth_item,
                weight
        );

        weightList.setAdapter(weightAdapter);



        Button addWeightButton = (Button) getView().findViewById(R.id.button_add);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFormFragment()).commit();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
