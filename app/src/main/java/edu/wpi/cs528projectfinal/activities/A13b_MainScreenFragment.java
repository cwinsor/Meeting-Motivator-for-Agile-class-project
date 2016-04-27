package edu.wpi.cs528projectfinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class A13b_MainScreenFragment extends Fragment {

    Button btnGotoList;
    Button btnGotoCreate;

    private static final String KEY_UID="uid";

    protected static A13b_MainScreenFragment newInstance(String uid) {
        A13b_MainScreenFragment f=new A13b_MainScreenFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, uid);
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        // ZONA - until such time as we use newInstance() (to create Bundle of shared state)
        // we will simply set state here...
       // TODO remove the following line leaving the return of getArguments()
        return "8";
      //  return(getArguments().getString(KEY_UID));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a13_main_screen, container, false);

        btnGotoCreate = (Button) view.findViewById(R.id.goto_create_meeting);
        btnGotoCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = A14b_NewMeetingFragment.newInstance(getUid());

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        btnGotoList = (Button) view.findViewById(R.id.goto_meeting_list);
        btnGotoList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), A16_MeetingListActivity.class);
                startActivity(i);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        // nothing to do
    }

}
