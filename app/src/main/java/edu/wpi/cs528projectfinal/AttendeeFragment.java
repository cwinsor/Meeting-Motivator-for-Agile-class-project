package edu.wpi.cs528projectfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Chris on 3/26/2016.
 */
public class AttendeeFragment extends Fragment {

    private static final String TAG = "AttendeeFragment";

    private static final String ARG_ATTENDEE_ID = "attendee_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO = 2;

    private Attendee mAttendee;

    private EditText mAttendeeName;
    private EditText mAttendeeGps;
    private EditText mAttendeeActivity;
    private EditText mAttendeeStatus;


    public static AttendeeFragment newInstance(UUID crimeId) {

        Log.v(TAG, "newInstance");

        Bundle args = new Bundle();
        args.putSerializable(ARG_ATTENDEE_ID, crimeId);

        AttendeeFragment fragment = new AttendeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG, "onCreate");

        UUID attendeeId = (UUID) getArguments().getSerializable(ARG_ATTENDEE_ID);
        mAttendee = AttendeeLab.get(getActivity()).getAttendee(attendeeId);

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.v(TAG, "onPause");

        AttendeeLab.get(getActivity())
                .updateAttendee(mAttendee);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(TAG, "--> onCreateView");

        View v = inflater.inflate(R.layout.fragment_attendee, container, false);


        mAttendeeName = (EditText) v.findViewById(R.id.attendee_name);
        mAttendeeName.setText(mAttendee.getName());
        mAttendeeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAttendee.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mAttendeeGps = (EditText) v.findViewById(R.id.attendee_gps);
        mAttendeeGps.setText(mAttendee.getGps());
        mAttendeeGps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAttendee.setGps(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        mAttendeeActivity = (EditText) v.findViewById(R.id.attendee_activity);
        mAttendeeActivity.setText(mAttendee.getActivity());
        mAttendeeActivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAttendee.setActivity(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mAttendeeStatus = (EditText) v.findViewById(R.id.attendee_status);
        mAttendeeStatus.setText(mAttendee.getStatus());
        mAttendeeStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAttendee.setStatus(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.v(TAG, "onActivityResult");

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }


    private String getAttendeeReport() {

        Log.v(TAG, "getAttendeeReport");

        String report = getString(R.string.attendee_report,
                mAttendee.getName(),
                mAttendee.getGps(),
                mAttendee.getActivity(),
                mAttendee.getStatus());
        return report;
    }

}
