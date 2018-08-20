package th.ac.a59070038kmitl.healthy;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class BMIFragment extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @NonNull Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button calculateBtn = (Button) getView().findViewById(R.id.button_calculate);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userHeight = ((EditText)getView().findViewById(R.id.bmi_user_height)).getText().toString();
                String userWeight = ((EditText)getView().findViewById(R.id.bmi_user_weight)).getText().toString();


                if(userHeight.isEmpty() || userWeight.isEmpty() || userHeight.equals("0") || userWeight.equals("0")){
                    Log.d("BMI", "FIELD NAME IS EMPTY");
                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();
                    ((TextView) getView().findViewById(R.id.textView2)).setText("Error!!");
                }
                else {
                    Log.d("BMI", "BMI IS VALUE");
                    DecimalFormat df2 = new DecimalFormat(".##");
                    Double.parseDouble(userHeight);
                    Double.parseDouble(userWeight);
                    Double answer = (Double.parseDouble(userWeight) / ((Double.parseDouble(userHeight)/100) * (Double.parseDouble(userHeight)/100)));

                    Log.d("BMI", String.valueOf(answer));
                    ((TextView) getView().findViewById(R.id.textView2)).setText(df2.format(answer).toString());

                }
            }
        });
    }
}
