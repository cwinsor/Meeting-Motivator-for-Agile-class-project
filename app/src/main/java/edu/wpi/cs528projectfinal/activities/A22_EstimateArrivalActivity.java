package edu.wpi.cs528projectfinal.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import edu.wpi.cs528projectfinal.R;

public class A22_EstimateArrivalActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a22_estimate_arrival);
        Button btn_estimate = (Button) findViewById(R.id.btn_estimate);
        btn_estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to read database for destination
                //need get current location
                String origin = "Worcester";
                String destination = "Boston";
                String url = getDirectionsUrl(origin, destination);
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(url);
            }
        });
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
    private String downloadUrl(String strUrl) throws IOException{
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


    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>> >{

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {
            JSONObject jObject;
            List<HashMap<String, String>> result = null;
            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                result = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            String distance = "";
            String duration = "";

            distance = (String)result.get(0).get("distance");
            duration = (String)result.get(1).get("duration");
            Log.d("Distance:", distance);
            Log.d("Duration:", duration);
            String toastMsg = "You will arrive in "+duration;
            Toast.makeText(A22_EstimateArrivalActivity.this, toastMsg, Toast.LENGTH_LONG).show();
        }
    }
}