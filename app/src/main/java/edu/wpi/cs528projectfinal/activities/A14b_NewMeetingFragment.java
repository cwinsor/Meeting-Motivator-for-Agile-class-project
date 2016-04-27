package edu.wpi.cs528projectfinal.activities;

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

import java.util.HashMap;

import edu.wpi.cs528projectfinal.R;
import edu.wpi.cs528projectfinal.activitiesCommon.DatePickerFragment;
import edu.wpi.cs528projectfinal.activitiesCommon.TimePickerFragment;

//import edu.wpi.cs528projectfinal.activitiesCommon.PlacePickerFragment;

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
    private Button btn_meeting_location;
    private Button setTimeButton;
    private Button setDateButton;
    private Button addMeetingButton;
    private String latitude;
    private String longitude;

    private TextView messageTextView;

    private static final String KEY_UID="uid";
    private static final String KEY_MID="mid";

    private static final String KEY_MEETING_LOCATION_RESULT = "param_meeting_location";
    private static final int KEY_LOCATION_CHOOSER_ACTIVITY = 999;


    int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

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

    String getMid() {
        return(getArguments().getString(KEY_MID));
    }

    // url to create new account
    private static String url_meeting_add_new = "http://www.cwinsorconsulting.com/cs528/meeting_add_new_meeting.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

//    int PLACE_PICKER_REQUEST = 12;
    int RESULT_OK = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a14_new_meeting, container, false);

        nameEditText = (EditText)view.findViewById(R.id.meeting_name);
        messageTextView = (TextView)view.findViewById(R.id.message);



        btn_meeting_location = (Button) view.findViewById(R.id.choose_location);
        btn_meeting_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launching All products Activity
//                Intent i = A20_MeetingLocationActivity.newIntent(getContext());
//
//                // Intent i = A20b_MeetingLocationActivity.newIntent(getContext());
//                startActivityForResult(i, KEY_LOCATION_CHOOSER_ACTIVITY);
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        /*
        btn_meeting_location = (Button) view.findViewById(R.id.choose_location);
        btn_meeting_location.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PlacePickerFragment newFragment = new PlacePickerFragment();
                // newFragment.setButton(setTimeButton);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .commit();

            }
        });
*/



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
                        btn_meeting_location.getText().toString(), // String meetingLocation,
                        latitude, // String latitude,
                        longitude, // String longitude,
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


    /* Called when the second activity's finished */
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch(requestCode) {
//            case KEY_LOCATION_CHOOSER_ACTIVITY:
//                if (resultCode == Activity.RESULT_OK) {
//                    Bundle res = data.getExtras();
//                    String result = res.getString(KEY_MEETING_LOCATION_RESULT);
//                    Toast.makeText(getContext(), "meeting location is..." + result, Toast.LENGTH_LONG).show();
//                    btn_meeting_location.setText(result);
//                }
//                break;
//        }
//    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);
                String toastMsg = String.format("Place: %s", place.getName());
                latitude = Double.toString(place.getLatLng().latitude);
                longitude = Double.toString(place.getLatLng().longitude);
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();
                finishWithResult(place.getName().toString());
                btn_meeting_location.setText(place.getName().toString());
            }
        }
    }

    private void finishWithResult(String locationString)
    {
        Bundle conData = new Bundle();
        conData.putString(KEY_MEETING_LOCATION_RESULT, locationString);
        Intent intent = new Intent();
        intent.putExtras(conData);
        getActivity().setResult(RESULT_OK, intent);
//        //getActivity().finish();
//        Bundle res = data.getExtras();
//        String result = res.getString(KEY_MEETING_LOCATION_RESULT);
//        Toast.makeText(getContext(), "meeting location is..." + locationString, Toast.LENGTH_LONG).show();
//        btn_meeting_location.setText(locationString);
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
                    //          Fragment fragment = A15b_AddAttendeesFragment.newInstance(getUid(), getMid());
                    Fragment fragment = new A12b_SignupFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
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
                //  messageTextView.setText("hello!");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // successfully created
            String myuid = getUid();
            String mymid = getMid();
            Fragment fragment = A13b_MainScreenFragment.newInstance(getUid());
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

    }






    /**
     * Background Async Task to update database
     * */
    class RunPlacePicker extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {

        }

        /**
         * Create
         * */
        protected String doInBackground(String... args) {
            try {
                PlacePicker.IntentBuilder intentBuilder =
                        new PlacePicker.IntentBuilder();
                //  intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                Intent intent = intentBuilder.build(getActivity());
                startActivityForResult(intent, PLACE_PICKER_REQUEST);

            } catch (GooglePlayServicesRepairableException
                    | GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {

        }

    }
}