package th.ac.a59070038kmitl.healthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SleepFromFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button submit = getView().findViewById(R.id.button_save);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = getView().findViewById(R.id.sleep_date).toString();
                String sleeptime = getView().findViewById(R.id.sleep_time).toString();
                String waketime = getView().findViewById(R.id.wake_time).toString();
                ContentValues row1 = new ContentValues();
                row1.put("date", date);
                row1.put("sleeptime", sleeptime);
                row1.put("waketime", waketime);
                SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
                db.insert("sleep", null, row1);
                db.close();
            }
        });
    }
}
