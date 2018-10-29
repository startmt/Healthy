package th.ac.a59070038kmitl.healthy.data;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import th.ac.a59070038kmitl.healthy.menu.Weight;

public class AddData extends Fragment {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mdb = FirebaseFirestore.getInstance();
    FragmentTransaction fragmentTransaction;

    public void setFragmentTransaction(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }
    public void AddData(Weight weight){
        mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").document(weight.getDate()).set(weight).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("FIREBASE", "SUCCESS");
                try{
                    fragmentTransaction.commit();
                } catch (Exception e){
                    Log.d("ACTIVITY", e.getMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

}
