package edu.wpi.cs528projectfinal.Activity04Checkin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import edu.wpi.cs528projectfinal.Activity01CreateStandup.StandupFragment;
import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class AttendeeListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final String MEETING_ID_KEY = "edu.wpi.cs528projectfinal.meeting_id";

    private RecyclerView mAttendeeRecyclerView;
    private AttendeeAdapter mAdapter;
    private boolean mSubtitleVisible;

    // how to start this (returns an Intent)
    public static Intent newIntent(Context packageContext, Integer theMeetingId) {
        Intent i = new Intent(packageContext, AttendeeListFragment.class);
        i.putExtra(MEETING_ID_KEY, theMeetingId);
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(sMSG, "onCreate");
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendee_list, container, false);

        mAttendeeRecyclerView = (RecyclerView) view.findViewById(R.id.attendee_recycler_view);
        mAttendeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }



        // zona - we need to get the meeting ID...
        //// TODO: 3/26/2016
        //   Integer theMeetingIdNumber = getIntent().getIntegerExtra(MEETING_ID_KEY, 0);


        // set up the button that takes to the next fragment-activity
        // reference  http://stackoverflow.com/questions/22571713/start-fragment-activity
        //   android:id="@+id/to_view_01"

     //   Button bSearchByLocation = ((Button) view.findViewById(R.id.to_view_01));

      //  bSearchByLocation.setOnClickListener(new View.OnClickListener() {

        //    @Override
       //     public void onClick(View v) {
       //         if (v.getId() == R.id.to_view_01) { // zona is this even necessary?
       //             // have our desired fragment create the intent that will start itself, and issue that intent
       //             Intent i = new Intent(StandupFragment.newIntent(getActivity()));
       //             startActivity(i);
       //
       //             // reference http://stackoverflow.com/questions/22571713/start-fragment-activity
       //             //   Fragment newFragment = new StandupFragment();
       //             //   FragmentTransaction transaction = getFragmentManager().beginTransaction();
       //             //   transaction.replace(R.id.attendee_recycler_view, newFragment);
       //             //   transaction.commit();
       //
       //             //    Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
       //             //    startActivity(intent);
       //
       //         }
       //      }
       //  });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        AttendeeList attendeeList = AttendeeList.get(getActivity());
        List<Attendee> attendees = attendeeList.getAttendees();

        mAdapter = new AttendeeAdapter(attendees);
        mAttendeeRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_attendee_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_attendee:
                Attendee crime = new Attendee();
                AttendeeList.get(getActivity()).addAttendee(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AttendeeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mIdTextView;
        private TextView mNameTextView;
        private TextView mGpsTextView;
        private TextView mActivityTextView;
        private TextView mStatusTextView;

        private Attendee mAttendee;

        public AttendeeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mIdTextView = (TextView) itemView.findViewById(R.id.attendee_list_item_attendee_id);
            mNameTextView = (TextView) itemView.findViewById(R.id.attendee_list_item_attendee_name);
            mGpsTextView = (TextView) itemView.findViewById(R.id.attendee_list_item_attendee_gps);
            mActivityTextView = (TextView) itemView.findViewById(R.id.attendee_list_item_attendee_activity);
            mStatusTextView = (TextView) itemView.findViewById(R.id.attendee_list_item_attendee_status);
        }

        public void bindAttendee(Attendee attendee) {
            mAttendee = attendee;
            mIdTextView.setText(mAttendee.getId().toString());
            mNameTextView.setText(mAttendee.getName());
            mGpsTextView.setText(mAttendee.getGps());
            mActivityTextView.setText(mAttendee.getActivity());
            mStatusTextView.setText(mAttendee.getStatus());
        }

        @Override
        public void onClick(View v) {
            Intent intent = AttendeeActivity.newIntent(getActivity(), mAttendee.getId());
            startActivity(intent);
        }
    }

    private class AttendeeAdapter extends RecyclerView.Adapter<AttendeeHolder> {

        private List<Attendee> mAttendees;

        public AttendeeAdapter(List<Attendee> attendees) {
            mAttendees = attendees;
        }

        @Override
        public AttendeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new AttendeeHolder(view);
        }

        @Override
        public void onBindViewHolder(AttendeeHolder holder, int position) {
            Attendee attendee = mAttendees.get(position);

            holder.mIdTextView.setText(attendee.getId().toString());
            holder.mNameTextView.setText(attendee.getName());
            holder.mGpsTextView.setText(attendee.getGps());
            holder.mActivityTextView.setText(attendee.getActivity());
            holder.mStatusTextView.setText(attendee.getStatus());
        }

        @Override
        public int getItemCount() {
            return mAttendees.size();
        }

    }

}
