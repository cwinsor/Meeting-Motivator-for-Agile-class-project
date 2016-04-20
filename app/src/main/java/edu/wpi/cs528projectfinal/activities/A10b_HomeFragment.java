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

import edu.wpi.cs528projectfinal.R;

public class A10b_HomeFragment extends Fragment {

    Button btnLogin;
    Button btnSignup;
    Button btnGoToProductExample;
    Button btnGoTo20MeetingLocation;
    Button btnGoTo21MyLocation;
    Button btnGoTo22ArrivalTimeEstimate;


    private static final String KEY_UID = "uid";


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
        btnGoToProductExample = (Button) view.findViewById(R.id.btnGoToProductExample);
        btnGoTo20MeetingLocation = (Button) view.findViewById(R.id.btnGoTo20MeetingLocation);
        btnGoTo21MyLocation = (Button) view.findViewById(R.id.btnGoTo21MyLocation);
        btnGoTo22ArrivalTimeEstimate = (Button) view.findViewById(R.id.btnGoTo22ArrivalTimeEstimate);

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
                //  Intent i = new Intent(getApplicationContext(), A20_MeetingLocationActivity.class);
                //  startActivity(i);

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
        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }

}