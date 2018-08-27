package th.ac.a59070038kmitl.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            MenuFragment fragment = new MenuFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.main_view, fragment )
                    .commit();
        }
    }
}
