package th.ac.a59070038kmitl.healthy.compare;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import th.ac.a59070038kmitl.healthy.data.AddData;
import th.ac.a59070038kmitl.healthy.menu.Weight;

public class LessCompare {

    public String status;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mdb = FirebaseFirestore.getInstance();


    public void CalLessCompare(final int weightcurrent, final long finalDateTime, final String date , final FragmentTransaction fragmentTransaction) {
        mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp", Query.Direction.DESCENDING).limit(1)
                    .whereLessThan("dateTimestamp", finalDateTime).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Weight compareLessWeight = doc.toObject(Weight.class);
                            Log.d("WFORM", doc.toObject(Weight.class).getDate());
                            if (compareLessWeight.getWeight() < weightcurrent) {
                                status = "UP";
                            } else if (compareLessWeight.getWeight() == weightcurrent) {
                                status = "NO UP AND DOWN";
                            } else {
                                status = "DOWN";
                            }
                        }
                    }
                    Weight currentWeight = new Weight(date,finalDateTime,weightcurrent,status);
                    GrestCompare gc = new GrestCompare();
                    gc.CalGrestCompare(weightcurrent, finalDateTime, fragmentTransaction);
                    AddData add = new AddData();
                    add.setFragmentTransaction(fragmentTransaction);
                    add.AddData(currentWeight);

                }

            });
    }


}
