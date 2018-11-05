package th.ac.a59070038kmitl.healthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.TextView;

import th.ac.a59070038kmitl.healthy.computed.DurationSlept;
import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;
import th.ac.a59070038kmitl.healthy.menu.Sleep;

public class SleepFormFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_form, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int state = 0;
        TextView TextDate = getActivity().findViewById(R.id.date);
        TextView TextSleep = getActivity().findViewById(R.id.sleep_time);
        TextView TextWake = getActivity().findViewById(R.id.wake_time);


        int idUpdate = -1;
        Bundle bundle = getArguments();
        if(bundle != null){
            state = 1;
            String date = bundle.getString("date");
            TextDate.setText(date);
            SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
            Cursor myCur = db.rawQuery("select" + date + "from sleeptime", null);
            while (myCur.moveToNext()){
                idUpdate = myCur.getInt(0);
                TextSleep.setText(myCur.getString(2));
                TextWake.setText(myCur.getString(3));
            }
            myCur.close();
            db.close();
        }
        final EditText date =  getView().findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");
            }
        });


        Button submit = getView().findViewById(R.id.button_save);
        Button back = getView().findViewById(R.id.button_back);

        final int finalState = state;
        final int finalIdUpdate = idUpdate;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if(finalState == 0){
                    setDatabase(finalState, row1);
                }
                else{
                    setDatabase(finalState, row1, finalIdUpdate);
                }

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



    public void setDatabase(int state, ContentValues row1){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.insert("sleeptime", null, row1);
        db.close();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new SleepFragment()).commit();
    }
    public void setDatabase(int state, ContentValues row1, int id){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.update("sleeptime", row1, "_id =" + id, null);
        db.close();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new SleepFragment()).commit();
    }
}
