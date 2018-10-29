package th.ac.a59070038kmitl.healthy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import th.ac.a59070038kmitl.healthy.menu.Sleep;
import th.ac.a59070038kmitl.healthy.menu.SleepAdapter;

public class SleepFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        Cursor myCur = db.rawQuery("select * from sleephisw", null);
        ArrayList<Sleep> sleeps = new ArrayList<>();
        final ListView sleepList = getView().findViewById(R.id.sleep_list);
        final SleepAdapter sleepAdapter = new SleepAdapter(
                getActivity(),
                R.layout.fragment_sleep_item,
                sleeps
        );
        sleepList.setAdapter(sleepAdapter);
        sleepAdapter.clear();
        while (myCur.moveToNext()){

            String date = myCur.getString(1);
            String sleeptime = myCur.getString(2);
            String waketime = myCur.getString(3);
            String slepttime = sleeptime + " - " + waketime;
            String duration = myCur.getString(4);
            sleeps.add(new Sleep(date, slepttime, duration));

        }
        myCur.close();
        sleepAdapter.notifyDataSetChanged();
        Button addslptime = getActivity().findViewById(R.id.button_add);
        addslptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFormFragment()).commit();
            }
        });
    }
}
