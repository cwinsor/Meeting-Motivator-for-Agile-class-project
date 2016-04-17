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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs528projectfinal.R;

public class A12_SignupActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText username;
    EditText password;
    TextView message;

    // url to create new account
    private static String url_users_add_new = "http://www.cwinsorconsulting.com/cs528/users_add_new_user.php";

    // JSON Node names
    private static final String TAG_TABLE = "user";
    private static final String TAG_ID = "uid";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



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
            json = jsonParser.makeHttpRequest(url_users_add_new,
                    "POST", params);

            // log response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created
                    Intent i = new Intent(getApplicationContext(), A13_MainScreen.class);
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

            try {
                // display product data in EditText - this method is called in UI thread
                message.setText(json.getString(TAG_MESSAGE));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}