package th.ac.a59070038kmitl.healthy;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import th.ac.a59070038kmitl.healthy.date.DatePickerFragment;

public class MainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            LoginFragment fragment = new LoginFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.main_view, fragment )
                    .commit();
        }
    }
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        TextView date = findViewById(R.id.weight_date);
        date.setText(sdf.format(calendar.getTime()));
    }
}
    