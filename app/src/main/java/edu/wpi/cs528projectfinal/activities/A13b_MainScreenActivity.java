package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 */
public class A13b_MainScreenActivity extends SingleFragmentActivity {
    private String TAG = this.getClass().toString();

    // create an Intent that can be used to start this activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A13b_MainScreenActivity.class);
        return intent;
        // this is a comment !
    }

    @Override
    protected Fragment createFragment() {
        Log.v(TAG, "createFragment");

        return new A00aMainScreenFragment();
    }

}