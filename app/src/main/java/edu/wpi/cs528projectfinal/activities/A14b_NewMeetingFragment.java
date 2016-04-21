package edu.wpi.cs528projectfinal.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.wpi.cs528projectfinal.R;
import edu.wpi.cs528projectfinal.activitiesCommon.DatePickerFragment;
import edu.wpi.cs528projectfinal.activitiesCommon.TimePickerFragment;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://developer.android.com/intl/ru/guide/topics/ui/controls/pickers.html
 * http://stackoverflow.com/questions/14750803/android-send-time-picker-fragment-data-to-activity
 * http://stackoverflow.com/questions/10798489/proper-way-to-give-initial-data-to-fragments
 */
public class A14b_NewMeetingFragment extends Fragment {

    private EditText nameEditText;
    private EditText locationEditText;
    private Button setLocationButton;
    private Button setTimeButton;
    private Button setDateButton;
    private Button addMeetingButton;

    private TextView messageTextView;

    private static final String KEY_UID="uid";


    protected static A14b_NewMeetingFragment newInstance(String uid) {
        A14b_NewMeetingFragment f=new A14b_NewMeetingFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, uid);
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        return(getArguments().getString(KEY_UID));
    }


    // url to create new account
    private static String url_meeting_add_new = "http://www.cwinsorconsulting.com/cs528/meeting_add_new_meeting.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    int PLACE_PICKER_REQUEST = 1;
    int RESULT_OK = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a14_new_meeting, container, false);

        nameEditText = (EditText)view.findViewById(R.id.meeting_name);
        locationEditText = (EditText)view.findViewById(R.id.location);
        messageTextView = (TextView)view.findViewById(R.id.message);

        // references
        // http://www.truiton.com/2015/04/using-new-google-places-api-android/
        // http://stackoverflow.com/questions/29781978/google-placepicker-closes-immediately-after-launch
        // https://developers.google.com/places/android-api/signup
        // http://stackoverflow.com/questions/34365369/googleservice-failed-to-initialize
        Button btn_meeting_location = (Button) view.findViewById(R.id.choose_location);
        btn_meeting_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    PlacePicker.IntentBuilder intentBuilder =
                            new PlacePicker.IntentBuilder();
                    //intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                    Intent intent = intentBuilder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    int foo = 1;
                    int bar = 2;
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == PLACE_PICKER_REQUEST) {
                    if (/* zona resultCode == RESULT_OK*/ true) {
                        Place place = PlacePicker.getPlace(getContext(), data);
                        String toastMsg = String.format("Place: %s", place.getName());
                        Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



        setTimeButton = (Button)view.findViewById(R.id.choose_time);
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.setButton(setTimeButton);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        setDateButton = (Button)view.findViewById(R.id.choose_date);
        setDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setButton(setDateButton);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        addMeetingButton = (Button)view.findViewById(R.id.create);
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // go to database using background thread
                CreateNewMeeting cnm = new CreateNewMeeting();
                cnm.setParams(
                        getUid(), // String organizerUid,
                        nameEditText.getText().toString(), // String meetingName,
                        locationEditText.getText().toString(), // String meetingLocation,
                        "1234", // String latitude,
                        "5678", // String longitude,
                        setTimeButton.getText().toString() + setDateButton.getText().toString(), // String meetingDateTime,
                        "s", // String meetingStatus,
                        "tnc"); // String timeNeedsChange) {

                cnm.execute();
            }
        });



        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }



    /**
     * Background Async Task to update database
     * */
    class CreateNewMeeting extends AsyncTask<String, String, String> {

        // JSON Node names
        private static final String TAG_TABLE = "meeting";
        private static final String TAG_ID = "mid";
        private static final String TAG_MEETING_NAME = "meetingName";
        private static final String TAG_LOCATION = "location";
        private static final String TAG_LATITUDE = "latitude";
        private static final String TAG_LONGITUDE = "longitude";
        private static final String TAG_MEETING_TIME = "meetingTime";
        private static final String TAG_ORGANIZER_ID = "organizerId";
        private static final String TAG_MEETING_STATUS = "meetingStatus";
        private static final String TAG_TIME_NEEDS_CHANGE = "timeNeedsChange";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        String myMeetingName;
        String myMeetingLocation;
        String myLatitude;
        String myLongitude;
        String myMeetingDateTime;
        String myOrganizerId;
        String myMeetingStatus;
        String myTimeNeedsChange;

        String myMid;
        String message;

        // Progress Dialog
        private ProgressDialog pDialog;

        JSONObject json;

        protected void setParams(
                String organizerUid,
                String meetingName,
                String meetingLocation,
                String latitude,
                String longitude,
                String meetingDateTime,
                String meetingStatus,
                String timeNeedsChange) {
            myMeetingName = meetingName;
            myMeetingLocation = meetingLocation;
            myLatitude = latitude;
            myLongitude = longitude;
            myMeetingDateTime = meetingDateTime;
            myOrganizerId = organizerUid;
            myMeetingStatus = meetingStatus;
            myTimeNeedsChange = timeNeedsChange;
        }

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getView().getContext());
            pDialog.setMessage("Creating..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Create
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put(TAG_MEETING_NAME, myMeetingName);
            params.put(TAG_LOCATION, myMeetingLocation);
            params.put(TAG_LATITUDE, myLatitude);
            params.put(TAG_LONGITUDE, myLongitude);
            params.put(TAG_MEETING_TIME, myMeetingDateTime);
            params.put(TAG_ORGANIZER_ID, myOrganizerId);
            params.put(TAG_MEETING_STATUS, myMeetingStatus);
            params.put(TAG_TIME_NEEDS_CHANGE, myTimeNeedsChange   );

            // make JSON Object
            JSONParser jsonParser = new JSONParser();
            json = jsonParser.makeHttpRequest(url_meeting_add_new, "POST", params);

            // log response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created
                    Fragment fragment = new A15b_AddAttendeesFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();

            try {
                // display response
                messageTextView.setText(json.getString(TAG_MESSAGE));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}