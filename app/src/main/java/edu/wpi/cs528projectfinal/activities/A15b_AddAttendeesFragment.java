package edu.wpi.cs528projectfinal.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class A15b_AddAttendeesFragment extends Fragment {

    private static final String KEY_UID="uid";


    protected static A15b_AddAttendeesFragment newInstance(String uid) {
        A15b_AddAttendeesFragment f=new A15b_AddAttendeesFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, uid);
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        return(getArguments().getString(KEY_UID));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a14_new_meeting, container, false);

        updateUI();

        return view;
    }


    private void updateUI() {
        // nothing to do
    }


}