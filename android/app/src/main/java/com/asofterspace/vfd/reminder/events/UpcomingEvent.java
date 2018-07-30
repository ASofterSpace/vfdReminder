package com.asofterspace.vfd.reminder.events;

import java.util.Date;

/**
 * This class represents a single upcoming event.
 */

public class UpcomingEvent {

    private String name;

    private boolean mandatory;

    private Date date;


    public UpcomingEvent(String name, boolean mandatory, Date date) {

        this.name = name;
        this.mandatory = mandatory;
        this.date = date;
    }

}
