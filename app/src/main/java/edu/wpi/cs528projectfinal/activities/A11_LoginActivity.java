package edu.wpi.cs528projectfinal.activities;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs528projectfinal.R;

public class A11_LoginActivity extends Activity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button btnLogin;
    TextView message;

    private static final String INTENT_EXTRA_UID = "uid";
    Integer uid;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // single user get
    private static String url_get_user_details = "http://www.cwinsorconsulting.com/cs528/users_get_user_details_by_username.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USER = "user";
    private static final String TAG_ID = "uid";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_MESSAGE = "message";

    // INTENT names
    private static final String INTENT_UID = "uid";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a11_login);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        message = (TextView) findViewById(R.id.message);

        // getting product details from intent
        Intent i = getIntent();

        // getting user id from intent
        uid = i.getIntExtra(INTENT_EXTRA_UID, 0);

        // save button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // starting background task to validate user
                new GetUserDetails().execute();
            }
        });

    }

    /**
     * Background Async Task to Get complete product details
     * */
    class GetUserDetails extends AsyncTask<String, String, String> {

        String myUsername;
        JSONObject json;


        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(A11_LoginActivity.this);
            pDialog.setMessage("Getting password for verification. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

            myUsername = editTextUsername.getText().toString();
        }

        /**
         * Getting in background thread
         */
        protected String doInBackground(String... args) {
            int success;

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", myUsername));

            // getting product details by making HTTP request
            // Note that product details url will use GET request
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

                    // successfully logged in
                    Intent i = new Intent(getApplicationContext(), A13_MainScreen.class);
                    i.putExtra(INTENT_UID, uid);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed
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

            try {
                // display product data in EditText - this method is called in UI thread
                message.setText(json.getString(TAG_MESSAGE));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }
}