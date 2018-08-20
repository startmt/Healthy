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
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @NonNull Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button registerBtn = (Button) getActivity().findViewById(R.id.button_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId =  ((EditText)getView().findViewById(R.id.login_user_id)).getText().toString();
                String password =  ((EditText)getView().findViewById(R.id.login_user_password)).getText().toString();
                String userAge = ((EditText)getView().findViewById(R.id.regis_user_age)).getText().toString();
                String userName = ((EditText)getView().findViewById(R.id.regis_user_name)).getText().toString();

                if(userId.isEmpty() || password.isEmpty() || userAge.isEmpty() || userName.isEmpty()){
                    Log.d("REGISTER", "FIELD NAME IS EMPTY");
                    Toast.makeText(getActivity(),"กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_LONG).show();
                }
                else if (userId.equals("admin")){
                    Log.d("REGISTER", "USER ALREADY EXIST");
                    Toast.makeText(getActivity(), "User นี้มีอยู่ในระบบแล้ว", Toast.LENGTH_LONG);
                }
                else {
                    Log.d("REGISTER", "GOTO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).commit();
                }
            }
        });
    }
}
