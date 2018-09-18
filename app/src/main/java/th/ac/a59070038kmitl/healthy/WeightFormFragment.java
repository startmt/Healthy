package th.ac.a59070038kmitl.healthy;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import th.ac.a59070038kmitl.healthy.compare.LessCompare;
import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;
import th.ac.a59070038kmitl.healthy.date.DateTimestamp;
import th.ac.a59070038kmitl.healthy.menu.Weight;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class WeightFormFragment extends Fragment{
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mdb = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weightform, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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


                 if (date.isEmpty() || weight.isEmpty() || date.equals("")) {
                     Log.d("WFORM", "DATA NOT COMPLETE.");
                     Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                 } else {
                     final Integer weightint = Integer.parseInt(weight);
                     DateTimestamp dateTime = new DateTimestamp();
                     final long finalDateTime = dateTime.DateTimestamp(date);
                     LessCompare lc = new LessCompare();
                     FragmentTransaction goFragment = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment());
                     lc.CalLessCompare(weightint, finalDateTime, date, goFragment);
                 }
             }
    });

    }
}