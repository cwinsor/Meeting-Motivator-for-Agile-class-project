package edu.wpi.cs528projectfinal;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Chris on 3/26/2016.
 */
public class AttendeeListActivity extends SingleFragmentActivity {

    private static String sMSG = "ZONA --> AttendeeListActivity ";

    @Override
    protected Fragment createFragment() {

        Log.v(sMSG, "createFragment");

        return new AttendeeListFragment();
    }

}