package th.ac.a59070038kmitl.healthy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;

import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;
import th.ac.a59070038kmitl.healthy.menu.Weight;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class WeightFormFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weightform, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser mUser = mAuth.getCurrentUser();
        final FirebaseFirestore mdb = FirebaseFirestore.getInstance();
         final EditText date = getView().findViewById(R.id.weight_date);
         date.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 DialogFragment datePicker = new DatePickerFragment();
                 datePicker.show(getFragmentManager(), "date picker");
             }
         });
         Button backButton = (Button) getView().findViewById(R.id.button_back);
         backButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
             }
         });
         Button saveButton = (Button) getView().findViewById(R.id.button_save);
         saveButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String date = ((TextView) getView().findViewById(R.id.weight_date)).getText().toString();
                 String weight = ((TextView) getView().findViewById(R.id.weight_number)).getText().toString();
                 Integer weightint = Integer.parseInt(weight);
                 String status = " ";
                 Weight weightobj = new Weight(date, weightint,status);
                 Log.d("WFORM", "SAVE_BUTTON");
                 if(date.isEmpty() || weight.isEmpty()){
                     Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT);
                 }
                 else {
                     mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").document(date).set(weightobj).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                             Log.d("WFORM", "SUCCESS");
                             getActivity().getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new WeightFragment()).addToBackStack(null).commit();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Log.d("WFORM", e.getMessage());
                         }
                     });
                 }
             }
         });
    }
}
