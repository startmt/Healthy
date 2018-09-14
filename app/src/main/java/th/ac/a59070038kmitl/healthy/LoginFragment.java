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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LAB203_15 on 20/8/2561.
 */

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;


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

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser muUser = mAuth.getCurrentUser();

        if(muUser != null){
            Log.d("LOGIN", muUser.getEmail());
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
            Log.d("LOGIN", "GO TO BMI");
        }

        Button loginBtn = (Button) getView().findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String email = ((EditText) getView().findViewById(R.id.login_user_email)).getText().toString();
                String password = ((EditText) getView().findViewById(R.id.login_user_password)).getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser _user = authResult.getUser();
                            if (!_user.isEmailVerified()) {
                                Toast.makeText(getActivity(), "กรุณายืนยัน Email", Toast.LENGTH_SHORT).show();
                            } else {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                                Log.d("LOGIN", "GO TO BMI");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("LOGIN", e.getMessage());
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        }
    }
