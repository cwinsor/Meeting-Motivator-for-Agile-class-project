package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by xiaoyansun on 4/22/16.
 */
public class A17_UserAdapter extends ArrayAdapter<A17_User>{

    int groupid;
    ArrayList<A17_User> records;
    Context context;

    static class MeetingHolder {
        CheckBox checkBox;
        TextView textName;
    }

    public A17_UserAdapter(Context context, int vg, ArrayList<A17_User>
            records) {

        super(context, vg, records);
        this.context = context;
        groupid = vg;
        this.records = records;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(groupid, parent, false);
        MeetingHolder holder = new MeetingHolder();
        holder.textName = (TextView) itemView.findViewById(R.id.list_username);
        holder.textName.setText(records.get(position).get_uName());
        return itemView;
    }
}
