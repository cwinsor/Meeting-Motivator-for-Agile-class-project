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
 */
public class A15_AddAttendeesFragment extends Fragment {
