package com.asofterspace.vfd.reminder.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a single upcoming event.
 */

public class UpcomingEvent {

    private static int idCounter = 0;

    private int id;

    private String name;

    private boolean mandatory;

    private Boolean answerByUser;

    // do not forget to always update both at the same time - therefore, always call setDate()!
    private Date date;
    private int dayOfWeek;


    public UpcomingEvent(String name, boolean mandatory, Date date, Boolean answer) {

        this.id = idCounter++;

        this.name = name;

        this.mandatory = mandatory;

        setDate(date);

        this.answerByUser = answer;
    }

    /**
     * Sets the answer and forwards it to the server... at least that is the plan!
     * @param answer True for going, false for not going
     */
    public void setAnswerByUser(boolean answer) {
        this.answerByUser = answer;

        // TODO :: forward the result to the server!
    }

    /**
     * Sets the date and updates the day of week too
     * @param date The date to be set for this event
     */
    private void setDate(Date date) {

        this.date = date;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public Date getDate() {
        return date;
    }

    public String getStrDate() {

        String[] daysOfWeek = {"Sonnabend", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};

        SimpleDateFormat sdf = new SimpleDateFormat("d. M. yyyy");

        return "am " + daysOfWeek[dayOfWeek] + " den " + sdf.format(date);
    }

}
