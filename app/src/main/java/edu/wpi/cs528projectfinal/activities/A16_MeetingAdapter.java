package edu.wpi.cs528projectfinal.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.wpi.cs528projectfinal.R;

/**
 * Created by xiaoyansun on 4/22/16.
 */
public class A16_MeetingAdapter extends ArrayAdapter<A16_Meeting>{

        int groupid;
        ArrayList<A16_Meeting> records;
        Context context;

    static class MeetingHolder {
        TextView textName;
        TextView textTime;
        TextView textLocation;
    }

        public A16_MeetingAdapter(Context context, int vg, ArrayList<A16_Meeting>
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
            holder.textName = (TextView) itemView.findViewById(R.id.meeting_name);
            holder.textName.setText(records.get(position).get_mName());

            holder.textTime = (TextView) itemView.findViewById(R.id.meeting_time);
            holder.textTime.setText(records.get(position).get_mTime());

            holder.textLocation = (TextView) itemView.findViewById(R.id.meeting_location);
            holder.textLocation.setText(records.get(position).get_mLocation());
            return itemView;
        }
}
