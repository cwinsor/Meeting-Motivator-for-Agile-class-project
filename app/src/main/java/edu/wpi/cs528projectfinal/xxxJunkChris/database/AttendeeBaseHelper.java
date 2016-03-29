package edu.wpi.cs528projectfinal.xxxJunkChris.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Chris on 3/28/2016.
 */
public class AttendeeBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "AttendeeBaseHelper";
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "attendeeBase.db";

    public AttendeeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + AttendeeDbSchema.AttendeeTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        AttendeeDbSchema.AttendeeTable.Cols.UUID + ", " +
                        AttendeeDbSchema.AttendeeTable.Cols.NAME + ", " +
                        AttendeeDbSchema.AttendeeTable.Cols.GPS + ", " +
                        AttendeeDbSchema.AttendeeTable.Cols.ACTIVITY + ", " +
                        AttendeeDbSchema.AttendeeTable.Cols.STATUS +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

