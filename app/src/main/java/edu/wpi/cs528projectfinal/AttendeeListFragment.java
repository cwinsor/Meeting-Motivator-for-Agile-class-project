package edu.wpi.cs528projectfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chris on 3/26/2016.
 */
public class AttendeeListFragment extends Fragment {

    private static String sMSG = "ZONA ----> CrimeListFragment ";
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mAttendeeRecyclerView;
    private AttendeeAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(sMSG, "onCreate");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(sMSG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_attendee_list, container, false);

        mAttendeeRecyclerView = (RecyclerView) view.findViewById(R.id.attendee_recycler_view);
        mAttendeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(sMSG, "onResume");
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(sMSG, "onSaveInstanceState");
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.v(sMSG, "onCreateOptionsMenu");
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
        Log.v(sMSG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.menu_item_new_attendee:
                Attendee crime = new Attendee();
                AttendeeLab.get(getActivity()).addAttendee(crime);
                Intent intent = AttendeePagerActivity
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

    private void updateSubtitle() {
        Log.v(sMSG, "updateSubtitle");
        AttendeeLab attendeeLab = AttendeeLab.get(getActivity());
        int attendeeCount = attendeeLab.getAttendees().size();
        String subtitle = getString(R.string.subtitle_format, attendeeCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }


    private void updateUI() {
        AttendeeLab attendeeLab = AttendeeLab.get(getActivity());
        List<Attendee> attendees = attendeeLab.getAttendees();

        if (mAdapter == null) {
            mAdapter = new AttendeeAdapter(attendees);
            mAttendeeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setAttendees(attendees);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class AttendeeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mGpsTextView;
        private TextView mActivityTextView;
        private TextView mStatusTextView;

        private Attendee mAttendee;

        public AttendeeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_attendee_name_text_view);
            mGpsTextView = (TextView) itemView.findViewById(R.id.list_item_attendee_gps_text_view);
            mActivityTextView = (TextView) itemView.findViewById(R.id.list_item_attendee_activity_text_view);
            mStatusTextView = (TextView) itemView.findViewById(R.id.list_item_attendee_status_text_view);
        }

        public void bindAttendee(Attendee attendee) {
            mAttendee = attendee;
            mNameTextView.setText(mAttendee.getName());
            mGpsTextView.setText(mAttendee.getGps());
            mActivityTextView.setText(mAttendee.getActivity());
            mStatusTextView.setText(mAttendee.getStatus());
        }

        @Override
        public void onClick(View v) {
            Intent intent = AttendeePagerActivity.newIntent(getActivity(), mAttendee.getId());
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
            View view = layoutInflater.inflate(android.R.layout.list_item_attendee, parent, false);
            return new AttendeeHolder(view);
        }

        @Override
        public void onBindViewHolder(AttendeeHolder holder, int position) {
            Attendee attendee = mAttendees.get(position);
            holder.bindAttendee(attendee);
        }

        @Override
        public int getItemCount() {
            return mAttendees.size();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(sMSG, "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(sMSG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(sMSG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(sMSG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(sMSG, "onDestroy");
    }


}
