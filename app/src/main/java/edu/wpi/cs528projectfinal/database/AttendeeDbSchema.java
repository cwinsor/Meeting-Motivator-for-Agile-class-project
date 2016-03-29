package edu.wpi.cs528projectfinal.database;

/**
 * Created by Chris on 3/28/2016.
 */
public class AttendeeDbSchema {

        public static final class AttendeeTable {
            public static final String NAME = "attendees";

            public static final class Cols {
                public static final String UUID = "uuid";
                public static final String NAME = "name";
                public static final String GPS = "gps";
                public static final String ACTIVITY = "activity";
                public static final String STATUS = "status";
            }
        }
    }