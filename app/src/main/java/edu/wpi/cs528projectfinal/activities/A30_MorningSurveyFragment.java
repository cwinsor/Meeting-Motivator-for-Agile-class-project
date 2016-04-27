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

    private Button button1a;
    private Button button1b;
    private Button button2a;
    private Button button2b;
    private Button button3a;
    private Button button3b;
    private Button button4a;
    private Button button4b;
    private Button button5;

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


        button1a = (Button) view.findViewById(R.id.button1a);
        button1b = (Button) view.findViewById(R.id.button1b);
        button2a = (Button) view.findViewById(R.id.button2a);
        button2b = (Button) view.findViewById(R.id.button2b);
        button3a = (Button) view.findViewById(R.id.button3a);
        button3b = (Button) view.findViewById(R.id.button3b);
        button4a = (Button) view.findViewById(R.id.button4a);
        button4b = (Button) view.findViewById(R.id.button4b);

        button5 = (Button) view.findViewById(R.id.button5);

        button1a.setBackgroundColor(getResources().getColor(R.color.button_released));
        button1b.setBackgroundColor(getResources().getColor(R.color.button_released));
        button2a.setBackgroundColor(getResources().getColor(R.color.button_released));
        button2b.setBackgroundColor(getResources().getColor(R.color.button_released));
        button3a.setBackgroundColor(getResources().getColor(R.color.button_released));
        button3b.setBackgroundColor(getResources().getColor(R.color.button_released));
        button4a.setBackgroundColor(getResources().getColor(R.color.button_released));
        button4b.setBackgroundColor(getResources().getColor(R.color.button_released));

        button1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1a.setBackgroundColor(getResources().getColor(R.color.button_depressed));
                button1b.setBackgroundColor(getResources().getColor(R.color.button_released));
            }
        });

        button1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1a.setBackgroundColor(getResources().getColor(R.color.button_released));
                button1b.setBackgroundColor(getResources().getColor(R.color.button_depressed));
            }
        });



        button2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2a.setBackgroundColor(getResources().getColor(R.color.button_depressed));
                button2b.setBackgroundColor(getResources().getColor(R.color.button_released));
            }
        });

        button2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2a.setBackgroundColor(getResources().getColor(R.color.button_released));
                button2b.setBackgroundColor(getResources().getColor(R.color.button_depressed));
            }
        });



        button3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3a.setBackgroundColor(getResources().getColor(R.color.button_depressed));
                button3b.setBackgroundColor(getResources().getColor(R.color.button_released));
            }
        });

        button3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3a.setBackgroundColor(getResources().getColor(R.color.button_released));
                button3b.setBackgroundColor(getResources().getColor(R.color.button_depressed));
            }
        });




        button4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4a.setBackgroundColor(getResources().getColor(R.color.button_depressed));
                button4b.setBackgroundColor(getResources().getColor(R.color.button_released));
            }
        });

        button4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4a.setBackgroundColor(getResources().getColor(R.color.button_released));
                button4b.setBackgroundColor(getResources().getColor(R.color.button_depressed));
            }
        });


        // signup button
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment fragment = new A10b_HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });




        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }
}