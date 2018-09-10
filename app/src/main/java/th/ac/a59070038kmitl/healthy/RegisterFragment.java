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


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();


        Button registerBtn = (Button) getActivity().findViewById(R.id.button_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =  ((EditText)getView().findViewById(R.id.regis_user_email)).getText().toString();
                String password =  ((EditText)getView().findViewById(R.id.regis_user_password)).getText().toString();
                String repassword = ((EditText)getView().findViewById(R.id.regis_user_repassword)).getText().toString();

                if(email.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                    Log.d("REGISTER", "FIELD NAME IS EMPTY");
                    Toast.makeText(getActivity(),"กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6 ){
                    Log.d("REGISTER", "Password not secure");
                    Toast.makeText(getActivity(), "Password ของคุณมีความปลอดภัยต่ำ", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals(repassword) == false){
                    Log.d("REGISTER", "Password and Re password not match");
                    Toast.makeText(getActivity(), "กรุณากรอก password และ re-password ให้ตรงกัน", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword("email", "password").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("REGISTER", "GOTO BMI");
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).commit();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("REGISTER", "WRONG EMAIL");
                            Toast.makeText(getActivity(), "กรุณากรอก Email ให้ถูกต้อง", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
