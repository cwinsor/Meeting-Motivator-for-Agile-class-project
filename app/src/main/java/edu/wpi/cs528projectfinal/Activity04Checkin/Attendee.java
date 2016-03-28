package edu.wpi.cs528projectfinal.Activity04Checkin;

import java.util.UUID;


/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 */
public class Attendee {

    private UUID id;
    private String name;
    private Integer gps;
    private String activity;
    private String status;

    public Attendee() {
        id = new UUID(12345, 23456);
        //// TODO: 3/26/2016  initialize the id
        setName("some name");
        setGps(0);
        setActivity("whatever");
        setStatus("some status");
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setGps(Integer gps) {
        this.gps = gps;
    }

    public Integer getGps() {
        return this.gps;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
