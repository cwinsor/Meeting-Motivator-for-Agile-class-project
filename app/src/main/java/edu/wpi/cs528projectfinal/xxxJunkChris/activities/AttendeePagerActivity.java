package edu.wpi.cs528projectfinal.xxxJunkChris.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.UUID;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/28/2016.
 */

public class AttendeePagerActivity extends AppCompatActivity {
    private static final String EXTRA_ATTENDEE_ID =
            "edu.wpi.cs528projectFinal.attendee_id";

    private ViewPager mViewPager;
    private List<Attendee> mAttendees;

    private static String sMSG = "ZONA AttendeePagerActivity";

    public static Intent newIntent(Context packageContext, UUID attendeeId) {
        Intent intent = new Intent(packageContext, AttendeePagerActivity.class);
        intent.putExtra(EXTRA_ATTENDEE_ID, attendeeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_pager);

        Log.v(sMSG, "onCreate");

        UUID attendeeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_ATTENDEE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_attendee_pager_view_pager);

        mAttendees = AttendeeLab.get(this).getAttendees();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Attendee attendee = mAttendees.get(position);
                return AttendeeFragment.newInstance(attendee.getId());
            }

            @Override
            public int getCount() {
                return mAttendees.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Attendee attendee = mAttendees.get(position);
                if (attendee.getName() != null) {
                    setTitle(attendee.getName());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        for (int i = 0; i < mAttendees.size(); i++) {
            if (mAttendees.get(i).getId().equals(attendeeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.v(sMSG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(sMSG, "onResume");
    }

/*
    @Override
    public void onSaveInstatnceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Integer", currentPhotoNum);
            }
  */

}
