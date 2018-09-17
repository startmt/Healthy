package th.ac.a59070038kmitl.healthy;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;
import th.ac.a59070038kmitl.healthy.menu.Weight;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class WeightFormFragment extends Fragment{
    private String status;
    private String status1;
    private int state = 0;

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
         final Button saveButton = (Button) getView().findViewById(R.id.button_save);
         saveButton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 final String date = ((EditText) getView().findViewById(R.id.weight_date)).getText().toString();
                 final String weight = ((EditText) getView().findViewById(R.id.weight_number)).getText().toString();
                 long dateTime = 0;
                 Date dateformat;
                 Log.d("WFORM", date);
                 Log.d("WFORM", weight);


                 if(date.isEmpty() || weight.isEmpty() || date.equals("")){
                     Log.d("WFORM", "DATA NOT COMPLETE.");
                     Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                 }


                 else {
                     final Integer weightint = Integer.parseInt(weight);
                     try {
                         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                         dateformat = sdf.parse(date);
                         sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
                         dateTime = Integer.parseInt(sdf.format(dateformat));

                     }catch(Exception e) {
                         e.printStackTrace();
                     }
                     final long finalDateTime = dateTime;

                     mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp", Query.Direction.DESCENDING).limit(1)
                             .whereLessThan("dateTimestamp", finalDateTime).addSnapshotListener(new EventListener<QuerySnapshot>() {
                         @Override
                         public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                             if (!queryDocumentSnapshots.isEmpty()) {
                                 for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                    Weight compareLessWeight = doc.toObject(Weight.class);
                                    if (compareLessWeight.getWeight() < weightint){
                                        status = "UP";
                                    }
                                    else if(compareLessWeight.getWeight() == weightint){
                                        status = "NO UP AND DOWN";
                                     }
                                    else {
                                        status = "DOWN";
                                    }
                                    Weight weightobj = new Weight(date, finalDateTime, weightint, status);
                                    mdb.collection("myfitness").document(mAuth.getUid())
                                            .collection("weight").document(date).set(weightobj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            state = 1;
                                        }

                                    });
                                    mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp").limit(1)
                                            .whereGreaterThan("dateTimestamp", finalDateTime).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                                                                                      @Override
                                                                                                                      public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                                                                                                                           for (QueryDocumentSnapshot doc1: queryDocumentSnapshots){
                                                                                                                               Weight compareGreaterWeight = doc1.toObject(Weight.class);
                                                                                                                               if (compareGreaterWeight.getWeight() > weightint){
                                                                                                                                   status1 = "UP";
                                                                                                                               }
                                                                                                                               else if(compareGreaterWeight.getWeight() == weightint){
                                                                                                                                   status1 = "NO UP AND DOWN";
                                                                                                                               }
                                                                                                                               else{
                                                                                                                                   status1 = "DOWN";
                                                                                                                               }
                                                                                                                               Weight weight1 = new Weight(compareGreaterWeight.getDate(),
                                                                                                                                       compareGreaterWeight.getDateTimestamp(),
                                                                                                                                       compareGreaterWeight.getWeight(),
                                                                                                                                       status1);
                                                                                                                               mdb.collection("myfitness").document(mAuth.getUid())
                                                                                                                                       .collection("weight").document(compareGreaterWeight.getDate())
                                                                                                                                       .set(weight1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                                   @Override
                                                                                                                                   public void onSuccess(Void aVoid) {

                                                                                                                                   }
                                                                                                                               }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                   @Override
                                                                                                                                   public void onFailure(@NonNull Exception e) {
                                                                                                                                       state = 1;
                                                                                                                                   }
                                                                                                                               });
                                                                                                                           }
                                                                                                                      }
                                                                                                                  });
                                                        }
                                                        if (state == 0){
                                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                                                        }
                                                    }

                                                    else {
                                 mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").orderBy("dateTimestamp").limit(1)
                                         .whereGreaterThan("dateTimestamp", finalDateTime).addSnapshotListener(new EventListener<QuerySnapshot>() {
                                     @Override
                                     public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                                            if (doc.toObject(Weight.class).getWeight() > weightint){
                                                status1 = "UP";
                                            }
                                            else if(doc.toObject(Weight.class).getWeight() == weightint){
                                                status1 = "NO UP AND DOWN";
                                            }
                                            else {
                                                status1 = "DOWN";
                                            }

                                            Weight weight1 = doc.toObject(Weight.class);
                                            Weight weight2 = new Weight(weight1.getDate(), weight1.getDateTimestamp(), weight1.getWeight(), status1);
                                            mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").document(weight2.getDate()).set(weight2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    state = 1;
                                                }
                                            });
                                        }
                                     }
                                 });

                                 Weight weightobj = new Weight(date, finalDateTime, weightint,"");
                                 mdb.collection("myfitness").document(mAuth.getUid()).collection("weight").document(date).set(weightobj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {
                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         state = 1;
                                     }
                                 });
                                 if (state == 0){
                                     getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                                 }
                             }
                                                }
                                            });

                             }


                         }
                     });
    }
}
