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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.wpi.cs528projectfinal.R;

public class A11b_LoginFragment extends Fragment {

    EditText editTextUsername;
    EditText editTextPassword;
    Button btnLogin;
    TextView message;


    private static final String KEY_UID="uid";


    protected static A11b_LoginFragment newInstance() {
        A11b_LoginFragment f=new A11b_LoginFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, "not_known");
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        return(getArguments().getString(KEY_UID));
    }


    // single user get
    private static String url_get_user_details = "http://www.cwinsorconsulting.com/cs528/users_get_user_details_by_username.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a11_login, container, false);

        editTextUsername = (EditText) view.findViewById(R.id.username);
        editTextPassword = (EditText) view.findViewById(R.id.password);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        message = (TextView) view.findViewById(R.id.message);

        // save button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // starting background task to validate user
                GetUserDetails gud = new GetUserDetails();
                gud.setParams(
                        editTextUsername.getText().toString(),
                        editTextPassword.getText().toString());
                gud.execute();
            }
        });

        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }

    /**
     * Background Async Task to Get complete product details
     * */
    class GetUserDetails extends AsyncTask<String, String, String> {

        String myUsername;
        String myGivenPassword;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_USER = "user";
        private static final String TAG_ID = "uid";
        private static final String TAG_USERNAME = "username";
        private static final String TAG_PASSWORD = "password";
        private static final String TAG_MESSAGE = "message";

        JSONObject json;


        protected void setParams(String username, String givenPassword) {
            myUsername = username;
            myGivenPassword = givenPassword;
        }


        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getView().getContext());
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

            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("username", myUsername);

            // getting product details by making HTTP request
            // Note that product details url will use GET request
            JSONParser jsonParser = new JSONParser();
            json = jsonParser.makeHttpRequest(
                    url_get_user_details, "GET", params);

            // log response
            Log.d("User Details", json.toString());

            // check for success tag
            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully received
                    JSONArray productObj = json.getJSONArray(TAG_USER); // JSON Array

                    JSONObject userInformation = productObj.getJSONObject(0);

                    // get password from JSON Array
                    String uid = (String) userInformation.get(TAG_ID);
                    String username = (String) userInformation.get(TAG_USERNAME);
                    String passwd = (String) userInformation.get(TAG_PASSWORD);

                    // TODO check password

                    // successfully created
                    Fragment fragment = new A13b_MainScreenFragment();
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
                message.setText(json.getString(TAG_MESSAGE));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}