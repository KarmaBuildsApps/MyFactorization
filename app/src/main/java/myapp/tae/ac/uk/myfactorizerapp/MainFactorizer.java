package myapp.tae.ac.uk.myfactorizerapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myapp.tae.ac.uk.myfactorizerapp.ui.FactorizerFragment;

public class MainFactorizer extends AppCompatActivity {
    private static final String FRAGMENT_TAG = "Activity_Fragment_For_Retaining";
    private FactorizerFragment ffragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        ffragment = (FactorizerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (ffragment == null) {
            ffragment = new FactorizerFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, ffragment, FRAGMENT_TAG).commit();
        }
    }
}
