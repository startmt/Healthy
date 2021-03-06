package th.ac.a59070038kmitl.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        FirebaseFirestore mdb = FirebaseFirestore.getInstance();
        final ListView weightList = getView().findViewById(R.id.weight_list);
        final WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weigth_item,
                weight
        );
        weightList.setAdapter(weightAdapter);

        weightAdapter.clear();
        mdb.collection("myfitness").document(uId).collection("weight").orderBy("dateTimestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                weightAdapter.clear();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                    Log.d("WEIGHT", doc.getData().toString());
                    weight.add(doc.toObject(Weight.class));
                    Log.d("ADAPTER", " from firestore = " + doc.toObject(Weight.class).getDate());
                }
                weightAdapter.notifyDataSetChanged();
            }
        });

        Button addWeightButton = (Button) getView().findViewById(R.id.button_add);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFormFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
