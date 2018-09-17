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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
                 final String weight = ((TextView) getView().findViewById(R.id.weight_number)).getText().toString();
                 Integer weightint = Integer.parseInt(weight);
                 String status = "UP";
                 long dateTime;
                 Date dateformate;


                 Log.d("WFORM", "SAVE_BUTTON");
                 if(date.isEmpty() || weight.isEmpty()) {

                     Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT);
                 }
                 else {
                     try {
                         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                         sdf.parse(date);
                         dateformate = sdf.get2DigitYearStart();
                         dateTime = dateformate.getTime();
                         Weight weightobj = new Weight(date, dateTime, weightint,status);

                         mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp")
                                 .whereGreaterThan("dateTimestamp", 3).addSnapshotListener(new EventListener<QuerySnapshot>() {
                             @Override
                             public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                                 for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                                     Log.d("WFORM", doc.getData().toString());

                                 }
                             }
                         });


                         mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").document(date).set(weightobj).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Log.d("WFORM", "SUCCESS");
                                 getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();

                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Log.d("WFORM", e.getMessage());
                             }
                         });

                     }catch(Exception e) {
                         e.printStackTrace();
                     }

                 }
             }
         });
    }
}
