package edu.wpi.cs528projectfinal.activities;

/**
 * Created by xiaoyansun on 4/17/16.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectionsJSONParser {

    /** Receives a JSONObject and returns a list of lists containing latitude and longitude */
    public List<HashMap<String,String>> parse(JSONObject jObject){

        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONObject jDistance = null;
        JSONObject jDuration = null;

        try {
            jRoutes = jObject.getJSONArray("routes");
            jLegs = ( (JSONObject)jRoutes.get(jRoutes.length() - 1)).getJSONArray("legs");
            jDistance = ((JSONObject) jLegs.get(jLegs.length() - 1)).getJSONObject("distance");
            HashMap<String, String> hmDistance = new HashMap<String, String>();
            hmDistance.put("distance", jDistance.getString("text"));

            jDuration = ((JSONObject) jLegs.get(jLegs.length() - 1)).getJSONObject("duration");
            HashMap<String, String> hmDuration = new HashMap<String, String>();
            hmDuration.put("duration", jDuration.getString("text"));
            result.add(hmDistance);
            result.add(hmDuration);
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception ignored){
        }
        return result;
    }
}
