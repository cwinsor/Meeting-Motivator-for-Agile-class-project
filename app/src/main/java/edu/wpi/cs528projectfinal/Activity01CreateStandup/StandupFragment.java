package edu.wpi.cs528projectfinal.Activity01CreateStandup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import edu.wpi.cs528projectfinal.Activity04Checkin.Attendee;
import edu.wpi.cs528projectfinal.Activity04Checkin.AttendeeList;
import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class StandupFragment extends Fragment {

    private FrameLayout mStandupListView;

    // how to start this (returns an Intent)
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, StandupFragment.class);
        return i;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_single_activity, container, false);

      //  mStandupListView = (FrameLayout) view.findViewById(R.id.fragment_a_single_activity);

        // return mStandupListView;
        return view;
    }

}
