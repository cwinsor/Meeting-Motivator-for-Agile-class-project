package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 */
public class A13b_MainScreenActivity extends SingleFragmentActivity {

    // create an Intent that can be used to start this activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A13b_MainScreenActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new A13b_MainScreenFragment();
    }

}