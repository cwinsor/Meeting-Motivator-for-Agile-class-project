package edu.wpi.cs528projectfinal.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.wpi.cs528projectfinal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class A17_MeetingInformationActivityFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    protected ArrayList<A17_MeetingUser> records;
    protected ArrayList<Double> mLocation;
    private TableLayout tl;
    private MapFragment mapFragment;
    private double latitude, longitude, myLatitude, myLongitude;
    private GoogleApiClient googleApiClient;


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (container == null) {
                return null;
            }

            View view = inflater.inflate(R.layout.a17_meeting_information,
                    container, false);
            tl = (TableLayout) view.findViewById(R.id.detailsTable);
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
            mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapView2);
            records = new ArrayList<A17_MeetingUser>();
            mLocation = new ArrayList<Double>();
            A17_MeetingLocation meetingLocation = new A17_MeetingLocation();
            meetingLocation.execute();
            Button add_attendee = (Button) view.findViewById(R.id.buttonaddattendee);
            add_attendee.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), A17_AddAttendeeActivity.class);
                    startActivity(i);
            }
        });
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onConnected(Bundle bundle) {
        android.location.LocationListener locationListener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLatitude = location.getLatitude();
                myLongitude = location.getLongitude();
                String origin = String.format("%s", (Double)myLatitude) + String.format(",%s", (Double)myLongitude);
                String destination = String.format("%s", (Double)latitude) + String.format(",%s", (Double)longitude);
                estimate(origin, destination);
                Log.d("myLatitude", Double.toString(myLatitude));
                Log.d("myLongitude", Double.toString(myLongitude));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private class A17_MeetingUserInformation extends AsyncTask<String, String, String> {
        String cur_meetingName;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_TABLE = "meeting_user";
        private static final String TAG_MEETING_NAME = "meetingName";
        private static final String TAG_USER_ID = "uid";
        private static final String TAG_USER_LONGITUDE = "userLongitude";
        private static final String TAG_USER_LATITUDE = "userLatitude";
        private static final String TAG_USER_ACTIVITY = "activity";
        private static final String TAG_USER_ARRIVAL = "arrivalTime";
        JSONObject json;

        protected void setParams(String meetingName) {cur_meetingName = meetingName;}

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
            Intent meeting_list = getActivity().getIntent();
            Bundle bundle = meeting_list.getExtras();
            if (!bundle.isEmpty()) {
                if (bundle.containsKey("meetingName")) {
                    setParams(bundle.getString("meetingName"));
                }
            }
            int success1;
            HashMap<String, String> params = new HashMap<>();
            params.put(TAG_MEETING_NAME, cur_meetingName);
            JSONParser jsonParser = new JSONParser();
            String url_get_meeting_user = "http://www.cwinsorconsulting.com/cs528/getAllAttendees.php";
            json = jsonParser.makeHttpRequest(
                    url_get_meeting_user, "GET", params);
            try {
                success1 = json.getInt(TAG_SUCCESS);
                Log.d("success1", Integer.toString(success1));
                if (success1 == 1) {
                    // successfully received
                    JSONArray meeting_userObj = json.getJSONArray(TAG_TABLE); // JSON Array
                    for(int i = 0; i < meeting_userObj.length(); i++) {
                        JSONObject meetingUser = meeting_userObj.getJSONObject(i);
                        // get password from JSON Array
                        String user_id = (String) meetingUser.get(TAG_USER_ID);
                        Log.d("user_id", user_id);
                        String user_latitude = (String) meetingUser.get(TAG_USER_LATITUDE);
                        Log.d("user_latitude", user_latitude);
                        String user_longitude = (String) meetingUser.get(TAG_USER_LONGITUDE);
                        Log.d("user_longitude", user_longitude);
                        String user_activity = (String) meetingUser.get(TAG_USER_ACTIVITY);
                        Log.d("user_activity", user_activity);
                        String user_arrival = (String) meetingUser.get(TAG_USER_ARRIVAL);
                        Log.d("user_arrival", user_arrival);

                        A17_MeetingUser mu = new A17_MeetingUser();

                        mu.set_uid(user_id);
                        mu.set_uLatitude(user_latitude);
                        mu.set_uLongitude(user_longitude);
                        mu.set_uActivity(user_activity);
                        mu.set_uArrival(user_arrival);
                        records.add(mu);
                        //Log.d("records", records.toString());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

//        /**
//         * After completing background task Dismiss the progress dialog
//         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            for (int i = 0; i < records.size(); i++) {
                // Make TR
                TableRow tr = new TableRow(getActivity());
                tr.setId(100 + i);
                tr.setPadding(15,15,15,15);
                tr.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.WRAP_CONTENT));
                tr.setDividerPadding(5);
                //tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, 1));

                TextView user = new TextView(getActivity());
                user.setId(200 + i);
                user.setTextSize(20);
                user.setLines(1);
                user.setLineSpacing(5, 5);
                user.setPadding(5,5,5,5);
                user.setText(records.get(i).get_uid());
                tr.addView(user);
                Log.d("records", Integer.toString(records.size()));

                TextView uLatitude = new TextView(getActivity());
                uLatitude.setId(300 + i);
                uLatitude.setTextSize(20);
                uLatitude.setPadding(5,5,5,5);
                uLatitude.setText(records.get(i).get_uLatitude());
                tr.addView(uLatitude);

                TextView uLongitude = new TextView(getActivity());
                uLongitude.setTextSize(20);
                uLongitude.setId(400 + i);
                uLongitude.setPadding(5,5,5,5);
                uLongitude.setText(records.get(i).get_uLongitude());
                tr.addView(uLongitude);

                TextView uActivity = new TextView(getActivity());
                uActivity.setId(500 + i);
                uActivity.setTextSize(20);
                uActivity.setPadding(5,5,5,5);
                uActivity.setText(records.get(i).get_uActivity());
                tr.addView(uActivity);

                TextView uArrival = new TextView(getActivity());
                uArrival.setId(600 + i);
                uArrival.setTextSize(20);
                uArrival.setPadding(5,5,5,5);
                uArrival.setText(records.get(i).get_uArrival());
                tr.addView(uArrival);

                tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
            googleApiClient.connect();
        }
    }

    private class A17_MeetingLocation extends AsyncTask<String, String, String> implements OnMapReadyCallback{
        String cur_meetingName;

        // Progress Dialog
        private ProgressDialog pDialog;

        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_TABLE2 = "meeting";
        private static final String TAG_MEETING_NAME = "meetingName";
        private static final String TAG_MEETING_LATITUDE = "latitude";
        private static final String TAG_MEETING_LONGITUDE = "longitude";
        JSONObject json2;

        protected void setParams(String meetingName) {cur_meetingName = meetingName;}

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            googleApiClient.connect();
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
            Intent meeting_list = getActivity().getIntent();
            Bundle bundle = meeting_list.getExtras();
            if (!bundle.isEmpty()) {
                if (bundle.containsKey("meetingName")) {
                    setParams("'" + bundle.getString("meetingName") + "'");
                }
            }
            int success2;
            HashMap<String, String> params = new HashMap<>();
            params.put(TAG_MEETING_NAME, cur_meetingName);
            JSONParser jsonParser = new JSONParser();
            String url_get_meeting_location = "http://www.cwinsorconsulting.com/cs528/getMeeting.php";
            json2 = jsonParser.makeHttpRequest(
                    url_get_meeting_location, "GET", params);
            try {
                success2 = json2.getInt(TAG_SUCCESS);
                Log.d("success2", Integer.toString(success2));
                if (success2 == 1) {
                    // successfully received
                    JSONArray meeting_locationObj = json2.getJSONArray(TAG_TABLE2); // JSON Array
                    for(int i = 0; i < meeting_locationObj.length(); i++) {
                        JSONObject meetingLocation = meeting_locationObj.getJSONObject(i);
                        String latitude = (String) meetingLocation.get(TAG_MEETING_LATITUDE);
                        Log.d("latitude", latitude);
                        String longitude = (String) meetingLocation.get(TAG_MEETING_LONGITUDE);
                        Log.d("longitude", longitude);
                        mLocation.add(Double.parseDouble(latitude));
                        mLocation.add(Double.parseDouble(longitude));
                        Log.d("mLocation", mLocation.toString());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        //        /**
//         * After completing background task Dismiss the progress dialog
//         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            latitude = mLocation.get(0);
            longitude = mLocation.get(1);
            mapFragment.getMapAsync(this);
            googleApiClient.disconnect();
            A17_MeetingUserInformation meetingUserInformation = new A17_MeetingUserInformation();
            meetingUserInformation.execute();
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng latLng = new LatLng(latitude, longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
            googleMap.addMarker(new MarkerOptions().position(latLng));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    googleApiClient.connect();
                }
            }
        }
    }

    public void estimate(String origin, String destination) {
        String url = getDirectionsUrl(origin, destination);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);
    }

    private String getDirectionsUrl(String origin, String destination){
        String str_origin = "origin="+origin;
        String str_dest = "destination="+destination;
        String sensor = "sensor=false";
        String parameters = str_origin+"&"+str_dest+"&"+sensor;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuilder sb  = new StringBuilder();
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            assert iStream != null;
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


    private class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }


    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {
            JSONObject jObject;
            List<HashMap<String, String>> result = null;
            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                if (jObject != null) {
                    result = parser.parse(jObject);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            String distance = "";
            String duration = "";

            if (result != null) {
                distance = (String)result.get(0).get("distance");
                duration = (String)result.get(1).get("duration");
                Log.d("Distance:", distance);
                Log.d("Duration:", duration);
                String toastMsg = "You will arrive in "+duration;
                Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_LONG).show();
            }
            googleApiClient.disconnect();
        }
    }
}

