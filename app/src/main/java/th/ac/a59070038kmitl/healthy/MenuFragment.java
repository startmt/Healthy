package th.ac.a59070038kmitl.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by LAB203_15 on 27/8/2561.
 */

public class MenuFragment extends Fragment{
    ArrayList<String> menu = new ArrayList<>();
    public MenuFragment(){
        menu.add("BMI");
        menu.add("Register");
        menu.add("Logout");
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menu
        );

        ListView menulist = (ListView) getView().findViewById(R.id.menu_list);
        menulist.setAdapter(menuAdapter);
        menulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MENU", "Click on menu = " + menu.get(position));

                if(position == 0){

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();

                }
                else if(position == 1){

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
                }
                else if(position == 2){

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

}
