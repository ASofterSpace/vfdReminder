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

    final public static String KEY_EVENT = "EVENT";

    /**
     * This is called to initialize the internal list of upcoming events, e.g. by syncing events
     * from the server.
     */
    public static void initialize(UpcomingEventsInitializedCallback callback) {

        // TODO - for now, we are just using test data! oh no! ^^

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime(), null));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Regulärer Dienst", false, cal.getTime(), null));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime(), null));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Regulärer Dienst", false, cal.getTime(), null));
        cal.add(Calendar.DATE, 7);
        upcomingEvents.add(new UpcomingEvent("Extradienst", false, cal.getTime(), null));

        callback.initDone();
    }

    public static List<UpcomingEvent> getUpcomingEvents() {
        return upcomingEvents;
    }

    public static UpcomingEvent getUpcomingEventById(int eventId) {

        for (UpcomingEvent event : upcomingEvents) {
            if (eventId == event.getId()) {
                return event;
            }
        }

        return null;
    }

}
