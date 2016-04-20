package edu.wpi.cs528projectfinal.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.wpi.cs528projectfinal.R;
import edu.wpi.cs528projectfinal.activitiesCommon.SingleFragmentActivity;
import edu.wpi.cs528projectfinal.activitiesCommon.TimePickerFragment;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://www.tutorialspoint.com/android/android_php_mysql.htm
 * Reference (passing and receiving extras via Intent to/from Fragment...
 * http://stackoverflow.com/questions/19747447/android-passing-and-retrieving-extra-from-activity-to-fragment
 */
public class A14b_NewMeetingActivity extends SingleFragmentActivity {

    // INTENT extras
    String uid;
    private static final String INTENT_UID = "uid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting user ID (uid) from intent
        Intent i = getIntent();
        uid = i.getStringExtra(INTENT_UID);
    }

    // create an Intent that can be used to start this activity
    public Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A14b_NewMeetingFragment.class);
        intent.putExtra(INTENT_UID, uid);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        A14b_NewMeetingFragment f = new A14b_NewMeetingFragment();
        Bundle args = new Bundle();
        args.putString(INTENT_UID, getIntent().getExtras().getString(INTENT_UID));
        f.setArguments(args);
        return f;
    }
}
