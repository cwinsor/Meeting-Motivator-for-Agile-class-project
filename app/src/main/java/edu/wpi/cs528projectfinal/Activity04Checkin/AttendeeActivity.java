package edu.wpi.cs528projectfinal.Activity04Checkin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import edu.wpi.cs528projectfinal.Common.SingleFragmentActivity;

/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 */
public class AttendeeActivity extends SingleFragmentActivity {

    private static final String EXTRA_ATTENDEE_ID =
            "edu.wpi.cs528projectfinal.attendee_id";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, AttendeeActivity.class);
        intent.putExtra(EXTRA_ATTENDEE_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new AttendeeFragment();
    }
}