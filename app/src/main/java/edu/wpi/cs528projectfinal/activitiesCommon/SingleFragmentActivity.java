package edu.wpi.cs528projectfinal.activitiesCommon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 * Reference (passing and receiving extras via Intent to/from Fragment...
 * http://stackoverflow.com/questions/19747447/android-passing-and-retrieving-extra-from-activity-to-fragment
 */
public abstract class SingleFragmentActivity  extends AppCompatActivity {

    protected abstract Fragment createFragment();
    private static String sMSG = "ZONA --> SingleFragmentActivity ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}

