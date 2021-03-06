package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * reference:
 * http://code.tutsplus.com/articles/google-play-services-using-the-places-api--cms-23715
 */
public class A20_MeetingLocationActivity extends FragmentActivity {
    int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

    private static final String KEY_MEETING_LOCATION_RESULT="param_meeting_location";

    // create an Intent that can be used to start this activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, A20_MeetingLocationActivity.class);
        return intent;
    }

// return the location as part of a bundle
    private void finishWithResult(String locationString)
    {
        Bundle conData = new Bundle();
        conData.putString(KEY_MEETING_LOCATION_RESULT, locationString);
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }

    ///////////////////////////////////
    //////////// original code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.a14_new_meeting);
        try {
            startActivityForResult(builder.build(A20_MeetingLocationActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                finishWithResult(place.getName().toString());
            }
        }
    }
}