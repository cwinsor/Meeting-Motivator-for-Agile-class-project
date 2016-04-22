package edu.wpi.cs528projectfinal.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by Chris on 3/26/2016.
 */
public class A15b_AddAttendeesFragment extends ListFragment implements AdapterView.OnItemClickListener {


    private static final String KEY_UID="uid";
    private static final String KEY_MID="mid";

    protected static A15b_AddAttendeesFragment newInstance(String uid, String mid) {
        A15b_AddAttendeesFragment f=new A15b_AddAttendeesFragment();

        Bundle args=new Bundle();

        args.putString(KEY_UID, uid);
        args.putString(KEY_MID, mid);
        f.setArguments(args);

        return(f);
    }

    String getUid() {
        return(getArguments().getString(KEY_UID));
    }

    String getMid() {
        return(getArguments().getString(KEY_MID));
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a15_add_attendees_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.UserNamesZonaHardcoded, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}