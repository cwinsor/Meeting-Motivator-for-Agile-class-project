package edu.wpi.cs528projectfinal.xxxJunkChris.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.wpi.cs528projectfinal.xxxJunkChris.database.AttendeeBaseHelper;
import edu.wpi.cs528projectfinal.xxxJunkChris.database.AttendeeCursorWrapper;
import edu.wpi.cs528projectfinal.xxxJunkChris.database.AttendeeDbSchema.AttendeeTable;

/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 * This is a singleton (static class that self-constructs from 'get')
 */
public class AttendeeLab {
    private static AttendeeLab sAttendeeList;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static AttendeeLab get(Context context) {
        if (sAttendeeList == null) {
            sAttendeeList = new AttendeeLab(context);
        }
        return sAttendeeList;
    }

    private AttendeeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new AttendeeBaseHelper(mContext)
                .getWritableDatabase();
    }


    public void addAttendee(Attendee a) {
        ContentValues values = getContentValues(a);

        mDatabase.insert(AttendeeTable.NAME, null, values);
    }


    public List<Attendee> getAttendees() {
        List<Attendee> attendees = new ArrayList<>();

        AttendeeCursorWrapper cursor = queryAttendees(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            attendees.add(cursor.getAttendee());
            cursor.moveToNext();
        }
        cursor.close();

        return attendees;
    }


    public Attendee getAttendee(UUID id) {
        AttendeeCursorWrapper cursor = queryAttendees(
                AttendeeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getAttendee();
        } finally {
            cursor.close();
        }
    }


    public void updateAttendee(Attendee attendee) {
        String uuidString = attendee.getId().toString();
        ContentValues values = getContentValues(attendee);

        mDatabase.update(AttendeeTable.NAME, values,
                AttendeeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }


    private static ContentValues getContentValues(Attendee attendee) {
        ContentValues values = new ContentValues();
        values.put(AttendeeTable.Cols.UUID, attendee.getId().toString());
        values.put(AttendeeTable.Cols.NAME, attendee.getName());
        values.put(AttendeeTable.Cols.GPS, attendee.getGps());
        values.put(AttendeeTable.Cols.ACTIVITY, attendee.getActivity());
        values.put(AttendeeTable.Cols.STATUS, attendee.getStatus());

        return values;
    }

    private AttendeeCursorWrapper queryAttendees(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                AttendeeTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new AttendeeCursorWrapper(cursor);
    }

}


