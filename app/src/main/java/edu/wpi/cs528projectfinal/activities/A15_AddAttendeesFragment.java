package edu.wpi.cs528projectfinal.activities;

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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs528projectfinal.R;
import edu.wpi.cs528projectfinal.activitiesCommon.DatePickerFragment;
import edu.wpi.cs528projectfinal.activitiesCommon.TimePickerFragment;

/**
 * Created by Chris on 3/26/2016.
 * Reference
 * http://developer.android.com/intl/ru/guide/topics/ui/controls/pickers.html
 * http://stackoverflow.com/questions/14750803/android-send-time-picker-fragment-data-to-activity
 */
public class A15_AddAttendeesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a14_new_meeting, container, false);
        return view;
    }

}