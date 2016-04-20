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

public class A12b_SignupFragment extends Fragment {

    private EditText username;
    private EditText password;
    private TextView message;


    private static final String KEY_UID="uid";

    protected static A12b_SignupFragment newInstance() {
        A12b_SignupFragment f=new A12b_SignupFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, "not_known");
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        return(getArguments().getString(KEY_UID));
    }

    // url to create new account
    private static String url_users_add_new = "http://www.cwinsorconsulting.com/cs528/users_add_new_user.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a12_signup, container, false);

        // Edit Text
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        message = (TextView) view.findViewById(R.id.message);

        // Create button
        Button btnCreateAccount = (Button) view.findViewById(R.id.signup);

        // button click event
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new account in background thread
                CreateNewAccount cna = new CreateNewAccount();
                cna.setParams(
                        username.getText().toString(),
                        password.getText().toString());
                cna.execute();
            }
        });

        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
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


        protected void setParams(String username, String password) {
            myUsername = username;
            myPassword = password;
        }

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getView().getContext());
            pDialog.setMessage("Creating Account..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put(TAG_USERNAME, myUsername);
            params.put(TAG_PASSWORD, myPassword);

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