package edu.wpi.cs528projectfinal.Activity04Checkin;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 * This is a singleton (static class that self-constructs from 'get')
 */
public class AttendeeList {
    private static AttendeeList sAttendeeList;

    private List<Attendee> mAttendees;

    public static AttendeeList get(Context context) {
        if (sAttendeeList == null) {
            sAttendeeList = new AttendeeList(context);
        }
        return sAttendeeList;
    }

    private AttendeeList(Context context) {
        mAttendees = new ArrayList<Attendee>();
        /// TODO: 3/26/2016
        // temporary - create a list of attendees here
        for (int i=0; i<100; i++) {
            Attendee attendee = new Attendee();
            attendee.setName("Attendee" + i);
            attendee.setGps(1024 + i * 627);
            attendee.setActivity((i%2==0) ? "Walking" : "Still");
            attendee.setStatus((i%3==0) ? "Stuck in Traffic" : "");
            mAttendees.add(attendee);
        }
    }

    public List<Attendee> getAttendees() {
        return mAttendees;
    }

    public Attendee getAttendee(UUID id) {
        for (Attendee attendee : mAttendees) {
            if (attendee.getId().equals(id)) {
                return attendee;
            }
        }
        return null;
    }
}


