package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

/**
 * Created by Chris on 4/10/2016.
 * Reference
 * http://www.tutorialspoint.com/android/android_php_mysql.htm
 */
public class A02aLoginActivity extends SingleFragmentActivity {
    private String TAG = this.getClass().toString();

    // create an Intent that can be used to start this activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A02aLoginActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Log.v(TAG, "createFragment");

        return new A02aLoginFragment();
    }

}