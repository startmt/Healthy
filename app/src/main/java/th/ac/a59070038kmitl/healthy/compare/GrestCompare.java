package th.ac.a59070038kmitl.healthy.compare;


import android.support.v4.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import th.ac.a59070038kmitl.healthy.data.AddData;
import th.ac.a59070038kmitl.healthy.menu.Weight;

public class GrestCompare {
    public Weight compareGreaterWeight;
    private String status;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mdb = FirebaseFirestore.getInstance();

    public void CalGrestCompare(final int weightcurrent, long finalDateTime, final FragmentTransaction fragmentTransaction){
        mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp").limit(1)
                .whereGreaterThan("dateTimestamp", finalDateTime).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot doc1: queryDocumentSnapshots){
                    compareGreaterWeight = doc1.toObject(Weight.class);
                    if (compareGreaterWeight.getWeight() > weightcurrent){
                        status = "UP";
                    }
                    else if(compareGreaterWeight.getWeight() == weightcurrent){
                        status = "NO UP AND DOWN";
                    }
                    else{
                        status = "DOWN";
                    }
                    compareGreaterWeight.setStatus(status);
                    if(status != null) {
                        AddData add = new AddData();
                        add.setFragmentTransaction(fragmentTransaction);
                        add.AddData(compareGreaterWeight);
                    }
                }
            }
        });
    }

}
