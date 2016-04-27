package edu.wpi.cs528projectfinal.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class A17_AddAttendeeActivityFragment extends Fragment {
    private A17_UserAdapter uAdapter;
    protected ArrayList<A17_User> records;
    private A17_UserList userList;
    private ListView listUser;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.a17_add_attendee, container, false);
        listUser = (ListView) view.findViewById(R.id.user_list);

        records = new ArrayList<A17_User>();
        userList = new A17_UserList();
        userList.execute();
        Log.d("records", records.toString());
        uAdapter = new A17_UserAdapter(getActivity(), R.layout.list_item_add_attendee, records);
        Log.d("records", records.toString());
        listUser.setAdapter(uAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class A17_UserList extends AsyncTask<String, String, String> {
        String myUsername;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MEETING = "user";
        private static final String TAG_USER_ID = "uid";
        private static final String TAG_USER_NAME = "username";
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
            String url_get_meeting = "http://www.cwinsorconsulting.com/cs528/users_get_all.php";
            json = jsonParser.makeHttpRequest(
                    url_get_meeting, "GET", params);
            Log.d("A17_User Details", json.toString());
            try {
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // successfully received
                    JSONArray meetingObj = json.getJSONArray(TAG_MEETING); // JSON Array
                    for(int i = 0; i < meetingObj.length(); i++) {
                        JSONObject meetingInformation = meetingObj.getJSONObject(i);
                        // get password from JSON Array
                        String user_id = (String) meetingInformation.get(TAG_USER_ID);
                        String user_name = (String) meetingInformation.get(TAG_USER_NAME);

                        A17_User u = new A17_User();

                        u.set_uid(user_id);
                        u.set_uName(user_name);
                        records.add(u);
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
            uAdapter.notifyDataSetChanged();
        }
    }
}

