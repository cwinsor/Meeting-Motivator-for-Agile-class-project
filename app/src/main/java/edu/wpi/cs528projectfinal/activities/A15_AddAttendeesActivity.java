package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://www.tutorialspoint.com/android/android_php_mysql.htm
 */
public class A15_AddAttendeesActivity extends SingleFragmentActivity {

    // create an Intent that can be used to start this activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A15_AddAttendeesFragment.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new A15_AddAttendeesFragment();
    }
}
