package edu.wpi.cs528projectfinal;

import java.util.Date;
import java.util.UUID;


/**
 * Created by Chris on 3/26/2016.
 * Reference Big Nerd Ranch Guide pg 169
 */
public class Attendee {

    private UUID mId;
    private String name;
    private String gps;
    private String activity;
    private String status;

    public Attendee() {
             this(UUID.randomUUID());
    }

    public Attendee(UUID id) {
        mId = id;
        setName("some name");
        setGps("1234");
        setActivity("whatever");
        setStatus("some status");
    }

    public UUID getId() {
        return this.mId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGps() {
        return this.gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
