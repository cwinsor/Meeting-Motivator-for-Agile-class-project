package edu.wpi.cs528projectfinal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.wpi.cs528projectfinal.R;

public class A10b_HomeFragment extends Fragment {

    Button btnLogin;
    Button btnSignup;
    Button btnGoToProductExample;
    Button btnGoTo20MeetingLocation;
    Button btnGoTo21MyLocation;
    Button btnGoTo22ArrivalTimeEstimate;
    Button btnGoTo30MorningSurvey;

    private static final String KEY_UID = "uid";

    private static final String KEY_MEETING_LOCATION_RESULT = "param_meeting_location";
    private static final int KEY_LOCATION_CHOOSER_ACTIVITY = 999;



    protected static A10b_HomeFragment newInstance() {
        A10b_HomeFragment f = new A10b_HomeFragment();

        Bundle args = new Bundle();

        args.putString(KEY_UID, "not_known");
        f.setArguments(args);

        return (f);
    }

    String getUid() {
        return (getArguments().getString(KEY_UID));
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a10_home_screen, container, false);

        // Buttons
        btnLogin = (Button) view.findViewById(R.id.btnHomeLogin);
        btnSignup = (Button) view.findViewById(R.id.btnHomeSignup);
<<<<<<< HEAD
        btnGoToProductExample = (Button) view.findViewById(R.id.btnGoToProductExample);
        btnGoTo20MeetingLocation = (Button) view.findViewById(R.id.btnGoTo20MeetingLocation);
        btnGoTo21MyLocation = (Button) view.findViewById(R.id.btnGoTo21MyLocation);
        btnGoTo22ArrivalTimeEstimate = (Button) view.findViewById(R.id.btnGoTo22ArrivalTimeEstimate);
        btnGoTo30MorningSurvey = (Button) view.findViewById(R.id.btnGoTo30MorningSurvey);
=======
//        btnGoToProductExample = (Button) view.findViewById(R.id.btnGoToProductExample);
//        btnGoTo20MeetingLocation = (Button) view.findViewById(R.id.btnGoTo20MeetingLocation);
//        btnGoTo21MyLocation = (Button) view.findViewById(R.id.btnGoTo21MyLocation);
//        btnGoTo22ArrivalTimeEstimate = (Button) view.findViewById(R.id.btnGoTo22ArrivalTimeEstimate);
>>>>>>> 57b4651ee09abee6207bda3d7a722ff5df180873

        // login button
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment fragment = new A11b_LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        // signup button
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment fragment = new A12b_SignupFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

<<<<<<< HEAD
        // legacy example "products" from http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
        btnGoToProductExample.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                //  Intent i = new Intent(getApplicationContext(), A05_MainScreenActivity.class);
                //  startActivity(i);

            }
        });


        btnGoTo20MeetingLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = A20_MeetingLocationActivity.newIntent(getContext());

                // Intent i = A20b_MeetingLocationActivity.newIntent(getContext());
                startActivityForResult(i, KEY_LOCATION_CHOOSER_ACTIVITY);
            }
        });

        btnGoTo21MyLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                // Intent i = new Intent(getApplicationContext(), A21_MyLocationActivity.class);
                // startActivity(i);

            }
        });

        btnGoTo22ArrivalTimeEstimate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                // Intent i = new Intent(getApplicationContext(), A22_EstimateArrivalActivity.class);
                // startActivity(i);

            }
        });


        btnGoTo30MorningSurvey.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching morning survey

                Fragment fragment = new A30_MorningSurveyFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

=======
//        // legacy example "products" from http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
//        btnGoToProductExample.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching All products Activity
//                //  Intent i = new Intent(getApplicationContext(), A05_MainScreenActivity.class);
//                //  startActivity(i);
//
//            }
//        });
//
//
//        btnGoTo20MeetingLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Launching All products Activity
//                Intent i = A20_MeetingLocationActivity.newIntent(getContext());
//
//                // Intent i = A20b_MeetingLocationActivity.newIntent(getContext());
//                startActivityForResult(i, KEY_LOCATION_CHOOSER_ACTIVITY);
//            }
//        });
//
//        btnGoTo21MyLocation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching All products Activity
//                // Intent i = new Intent(getApplicationContext(), A21_MyLocationActivity.class);
//                // startActivity(i);
//
//            }
//        });
//
//        btnGoTo22ArrivalTimeEstimate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching All products Activity
//                // Intent i = new Intent(getApplicationContext(), A22_EstimateArrivalActivity.class);
//                // startActivity(i);
//
//            }
//        });
>>>>>>> 57b4651ee09abee6207bda3d7a722ff5df180873
        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }


    /* Called when the second activity's finished */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case KEY_LOCATION_CHOOSER_ACTIVITY:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle res = data.getExtras();
                    String result = res.getString(KEY_MEETING_LOCATION_RESULT);
                    Toast.makeText(getContext(), "meeting location is..." + result, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        //    Toast.makeText(getContext(), "a10 has been resumed", Toast.LENGTH_LONG).show();
    }

}