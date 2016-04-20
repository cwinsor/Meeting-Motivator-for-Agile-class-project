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

public class A12_SignupActivity extends Activity {

    EditText username;
    EditText password;
    TextView message;

    // INTENT extras
    private static final String INTENT_UID = "uid";

    // url to create new account
    private static String url_users_add_new = "http://www.cwinsorconsulting.com/cs528/users_add_new_user.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a12_signup);

        // Edit Text
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        message = (TextView) findViewById(R.id.message);

        // Create button
        Button btnCreateAccount = (Button) findViewById(R.id.signup);

        // button click event
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new account in background thread
                new CreateNewAccount().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new account
     * */
    class CreateNewAccount extends AsyncTask<String, String, String> {
        String myUsername;
        String myPassword;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_TABLE = "user";
        private static final String TAG_ID = "uid";
        private static final String TAG_USERNAME = "username";
        private static final String TAG_PASSWORD = "password";
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        JSONObject json;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(A12_SignupActivity.this);
            pDialog.setMessage("Creating Account..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

            myUsername = username.getText().toString();
            myPassword = password.getText().toString();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_USERNAME, myUsername));
            params.add(new BasicNameValuePair(TAG_PASSWORD, myPassword));

            // make JSON Object
            // Note that create product url accepts POST method
            JSONParser jsonParser = new JSONParser();
            json = jsonParser.makeHttpRequest(url_users_add_new,
                    "POST", params);

            // log response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    // successfully received
                    JSONArray productObj = json.getJSONArray(TAG_TABLE); // JSON Array

                    JSONObject userInformation = productObj.getJSONObject(0);

                    // get password from JSON Array
                    String uid = (String) userInformation.get(TAG_ID);

                    // go to next screen
                    Intent i = new Intent(getApplicationContext(), A13_MainScreen.class);
                    i.putExtra(INTENT_UID, uid);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
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
        }

    }
}