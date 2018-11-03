package th.ac.a59070038kmitl.healthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.EditText;

import th.ac.a59070038kmitl.healthy.computed.DurationSlept;
import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;

public class SleepFormFragment extends Fragment {
    private String date = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_form, container, false);
        try{
            this.date = getArguments().getString("data");
            Log.d("SLEEPFORM", getArguments().getString("data"));
        }catch (Exception e){
            Log.d("SLEEPFORM", e.getMessage());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("SLEEPFORM", this.date + "111");
        Log.d("SLEEPFORM", "HEY");
        Button submit = getView().findViewById(R.id.button_save);
        Button back = getView().findViewById(R.id.button_back);
        EditText date =  getView().findViewById(R.id.date);
        Log.d("SLEEPFORM", this.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SLEEPFORM","Start submit");
                String date = ((EditText) getView().findViewById(R.id.date)).getText().toString();
                String sleeptime =((EditText) getView().findViewById(R.id.sleep_time)).getText().toString();
                String waketime = ((EditText) getView().findViewById(R.id.wake_time)).getText().toString();
                DurationSlept durationSlept = new DurationSlept(sleeptime, waketime);
                String durationtime = durationSlept.getDurationtime();
                ContentValues row1 = new ContentValues();
                row1.put("date", date);
                row1.put("sleeptime", sleeptime);
                row1.put("waketime", waketime);
                row1.put("duration", durationtime);
                SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
                if(date.equals("") == false){
                    try {
                        db.update("sleeptime", row1, "date = " + date, null);
                    }catch (Exception e){
                        db.insert("sleeptime", null, row1);
                    }
                }
                else{
                    db.insert("sleeptime", null, row1);
                }
                date = "";
                db.close();


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment()).commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment()).commit();
            }
        });
    }
}
