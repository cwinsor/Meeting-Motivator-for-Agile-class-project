package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://www.tutorialspoint.com/android/android_php_mysql.htm
 * Reference (passing and receiving extras via Intent to/from Fragment...
 * http://stackoverflow.com/questions/19747447/android-passing-and-retrieving-extra-from-activity-to-fragment
 */
public class A14b_NewMeetingActivity extends SingleFragmentActivity {

    // create an Intent that can be used to start this activity
    public Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A14b_NewMeetingActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new A14b_NewMeetingFragment();
    }

}
