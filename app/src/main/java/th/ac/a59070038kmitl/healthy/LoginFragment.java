package th.ac.a59070038kmitl.healthy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_15 on 20/8/2561.
 */

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @NonNull Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView regisTv = (TextView) getView().findViewById(R.id.text_register);
        regisTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Log.d("LOGIN", "GOTO REGISTER");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).commit();
            }
        });


        Button loginBtn = (Button) getView().findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String userId =  ((EditText)getView().findViewById(R.id.login_user_id)).getText().toString();
                String password =  ((EditText)getView().findViewById(R.id.login_user_password)).getText().toString();
                if(userId.isEmpty() || password.isEmpty()){
                    Log.d("LOGIN", "USER OR PASSWORD IS EMPTY");

                    Toast.makeText(
                        getActivity(),"กรุณาใส่ user or password", Toast.LENGTH_SHORT
                    ).show();
                }
                else if(userId.equals("admin") && password.equals("admin")){
                    Log.d("LOGIN", "GOTO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                }
                else{
                    Log.d("LOGIN", "INVALID USER OR PASSWORD");
                    Toast.makeText(
                            getActivity()," user or password Invalid", Toast.LENGTH_SHORT
                    ).show();
                }

            }
        });
        }
    }
