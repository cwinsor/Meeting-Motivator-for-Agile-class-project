package edu.wpi.cs528projectfinal.xxxJunkChris.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edu.wpi.cs528projectfinal.xxxJunkChris.activities.Attendee;

/**
 * Created by Chris on 3/28/2016.
 */
public class AttendeeCursorWrapper extends CursorWrapper {
        public AttendeeCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public Attendee getAttendee() {
            String uuidString = getString(getColumnIndex(AttendeeDbSchema.AttendeeTable.Cols.UUID));
            String name = getString(getColumnIndex(AttendeeDbSchema.AttendeeTable.Cols.NAME));
            String gps = getString(getColumnIndex(AttendeeDbSchema.AttendeeTable.Cols.GPS));
            String activity = getString(getColumnIndex(AttendeeDbSchema.AttendeeTable.Cols.ACTIVITY));
            String status = getString(getColumnIndex(AttendeeDbSchema.AttendeeTable.Cols.STATUS));

            Attendee attendee = new Attendee(UUID.fromString(uuidString));
            attendee.setName(name);
            attendee.setGps(gps);
            attendee.setActivity(activity);
            attendee.setStatus(status);

            return attendee;
        }
    }