package edu.wpi.cs528projectfinal.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class A00aMainScreenFragment extends Fragment {
    private String TAG = this.getClass().toString();

    private Button mGotoCreateStandup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.a00a_fragment_main_screen, container, false);

        mGotoCreateStandup = (Button) view.findViewById(R.id.goto_create_standup);
        mGotoCreateStandup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Intent i = A01aCreateStandupActivity.newIntent(getActivity());
                //    startActivity(i);

                // switch to another fragment at runtime
                // references
                // http://stackoverflow.com/questions/20176999/how-to-switch-between-fragments-during-onclick
                // http://developer.android.com/intl/ru/training/basics/fragments/fragment-ui.html#AddAtRuntime
                Fragment fragment = new A01aCreateStandupFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        updateUI();
    }

    private void updateUI() {
        // nothing to do
    }

}
