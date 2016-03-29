package edu.wpi.cs528projectfinal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edu.wpi.cs528projectfinal.Attendee;
import edu.wpi.cs528projectfinal.database.AttendeeDbSchema.AttendeeTable;

/**
 * Created by Chris on 3/28/2016.
 */
public class AttendeeCursorWrapper extends CursorWrapper {
        public AttendeeCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public Attendee getAttendee() {
            String uuidString = getString(getColumnIndex(AttendeeTable.Cols.UUID));
            String name = getString(getColumnIndex(AttendeeTable.Cols.NAME));
            String gps = getString(getColumnIndex(AttendeeTable.Cols.GPS));
            String activity = getString(getColumnIndex(AttendeeTable.Cols.ACTIVITY));
            String status = getString(getColumnIndex(AttendeeTable.Cols.STATUS));

            Attendee attendee = new Attendee(UUID.fromString(uuidString));
            attendee.setName(name);
            attendee.setGps(gps);
            attendee.setActivity(activity);
            attendee.setStatus(status);

            return attendee;
        }
    }