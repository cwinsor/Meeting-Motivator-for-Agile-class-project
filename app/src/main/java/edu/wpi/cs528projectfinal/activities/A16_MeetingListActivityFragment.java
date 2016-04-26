package edu.wpi.cs528projectfinal.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.cs528projectfinal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class A16_MeetingListActivityFragment extends ListFragment {
    private A16_MeetingAdapter mAdapter;
    protected ArrayList<A16_Meeting> records;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a16__meeting_list, container, false);
        ListView listMeeting = (ListView) view.findViewById(android.R.id.list);

        records = new ArrayList<A16_Meeting>();
        A16_MeetingList meetingList = new A16_MeetingList();
//        String s = "c";
//        meetingList.setParams(s);
        meetingList.execute();
        mAdapter = new A16_MeetingAdapter(getActivity(), R.layout.list_item_meeting, records);
//        String[] records = new String[]{"1", "2", "3"};
//        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_meeting, R.id.meeting_name, records);
        listMeeting.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.fragment_crime_list, menu);
//
//        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
//        if (mSubtitleVisible) {
//            subtitleItem.setTitle(R.string.hide_subtitle);
//        } else {
//            subtitleItem.setTitle(R.string.show_subtitle);
//        }
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_item_new_crime:
//                Crime crime = new Crime();
//                CrimeLab.get(getActivity()).addCrime(crime);
//                Intent intent = CrimePagerActivity
//                        .newIntent(getActivity(), crime.getId());
//                startActivity(intent);
//                return true;
//            case R.id.menu_item_show_subtitle:
//                mSubtitleVisible = !mSubtitleVisible;
//                getActivity().invalidateOptionsMenu();
//                updateSubtitle();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private class A16_MeetingList extends AsyncTask<String, String, String> {
        String myUsername;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MEETING = "meeting";
        private static final String TAG_MEETING_NAME = "meetingName";
        private static final String TAG_MEETING_TIME = "meetingTime";
        private static final String TAG_MEETING_LOCATION = "location";
        JSONObject json;


    protected void setParams(String username) {
        myUsername = username;
    }

        /**
         * Before starting background thread Show Progress Dialog
         */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Getting password for verification. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

        /**
         * Getting in background thread
         */
        protected String doInBackground(String... args) {
            int success;
            HashMap<String, String> params = new HashMap<>();
            JSONParser jsonParser = new JSONParser();
            String url_get_meeting = "http://www.cwinsorconsulting.com/cs528/meetings_get_all.php";
            json = jsonParser.makeHttpRequest(
                    url_get_meeting, "GET", params);
            Log.d("A16_Meeting Details", json.toString());
            try {
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // successfully received
                    JSONArray meetingObj = json.getJSONArray(TAG_MEETING); // JSON Array
                    for(int i = 0; i < meetingObj.length(); i++) {
                        JSONObject meetingInformation = meetingObj.getJSONObject(i);
                        // get password from JSON Array
                        String meeting_name = (String) meetingInformation.get(TAG_MEETING_NAME);
                        String meeting_time = (String) meetingInformation.get(TAG_MEETING_TIME);
                        String meeting_location = (String) meetingInformation.get(TAG_MEETING_LOCATION);

                        A16_Meeting m = new A16_Meeting();

                        m.set_mName(meeting_name);
                        m.set_mTime(meeting_time);
                        m.set_mLocation(meeting_location);
                        records.add(m);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    }
}

