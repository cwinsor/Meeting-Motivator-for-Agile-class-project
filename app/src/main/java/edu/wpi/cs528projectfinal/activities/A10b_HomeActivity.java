package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

public class A10b_HomeActivity extends SingleFragmentActivity{

    // create an Intent that can be used to start this activity
    public Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A10b_HomeActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new A10b_HomeFragment();
    }
}