package edu.wpi.cs528projectfinal.activities;

import android.app.ProgressDialog;
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

import java.util.HashMap;

import edu.wpi.cs528projectfinal.R;

public class A30_MorningSurveyFragment extends Fragment {

 //   private Button submitButton;

    protected static A30_MorningSurveyFragment newInstance() {
        A30_MorningSurveyFragment f=new A30_MorningSurveyFragment();

        Bundle args=new Bundle();

        return(f);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a30_morning_survey, container, false);


        // submit button
//        submitButton = (Button) view.findViewById(R.id.submit);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                // upon submit - go back to main page
//                Fragment fragment = new A10b_HomeFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .commit();
//            }
//        });

        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }
}