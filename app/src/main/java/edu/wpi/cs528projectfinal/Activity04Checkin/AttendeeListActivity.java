package edu.wpi.cs528projectfinal.Activity04Checkin;

import android.support.v4.app.Fragment;

import edu.wpi.cs528projectfinal.Common.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 */
public class AttendeeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
    return new AttendeeListFragment();
    }
}
