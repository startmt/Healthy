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

import java.util.ArrayList;

import th.ac.a59070038kmitl.healthy.menu.Sleep;

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
        Cursor myCur = db.rawQuery("select * from sleep", null);
        ArrayList<Sleep> sleeps = new ArrayList<>();
        while (myCur.moveToNext()){
            String date = myCur.getString(0);
            String sleeptime = myCur.getString(1);
            String waketime = myCur.getString(2);
            String slepttime = sleeptime + " - " + waketime;
            int duration = (int) 5.30;
            sleeps.add(new Sleep(date, slepttime, duration));

        }
        myCur.close();
    }
}
