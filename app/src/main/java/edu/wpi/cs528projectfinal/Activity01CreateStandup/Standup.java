package edu.wpi.cs528projectfinal.Activity01CreateStandup;

import android.text.format.Time;

import java.util.UUID;


/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 */
public class Standup {

    private UUID id;
    private String name;
    private String description;
    private String meetingDate; //TODO replace with proper Date
    private String meetingTime; //TODO replace with proper Time


    public Standup() {
        id = null;
        //// TODO: 3/26/2016  initialize the id
        setName("no name");
        setDescription("no description");
        setMeetingDate("no date");
        setMeetingTime("no time");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }
}
