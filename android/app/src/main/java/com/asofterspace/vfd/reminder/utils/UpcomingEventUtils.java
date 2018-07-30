package com.asofterspace.vfd.reminder.utils;

import com.asofterspace.vfd.reminder.events.UpcomingEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This utility class is supposed to help with upcoming events
 */

public class UpcomingEventUtils {

    private static List<UpcomingEvent> upcomingEvents = new ArrayList<>();

    /**
     * This is called to initialize the internal list of upcoming events, e.g. by syncing events
     * from the server.
     */
    public static void initialize(UpcomingEventsInitializedCallback callback) {

        // TODO - for now, we are just using test data! oh no! ^^

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime()));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Regulärer Dienst", false, cal.getTime()));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime()));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Regulärer Dienst", false, cal.getTime()));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime()));

        callback.initDone();
    }

}
