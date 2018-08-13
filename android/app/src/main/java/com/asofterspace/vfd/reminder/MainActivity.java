package com.asofterspace.vfd.reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asofterspace.vfd.reminder.events.UpcomingEvent;
import com.asofterspace.vfd.reminder.utils.NotificationUtils;
import com.asofterspace.vfd.reminder.utils.UpcomingEventUtils;
import com.asofterspace.vfd.reminder.utils.UpcomingEventsInitializedCallback;

import java.util.List;

import reminder.vfd.asofterspace.com.vfdreminder.R;

public class MainActivity extends AppCompatActivity implements UpcomingEventsInitializedCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpcomingEventUtils.initialize(this);
    }

    public void initDone() {

        // the UpcomingEventUtils is ready; let's display some dates to the user!
        List<UpcomingEvent> upcomingEvents = UpcomingEventUtils.getUpcomingEvents();

        for (UpcomingEvent event : upcomingEvents) {
            showEventToUser(event);
        }

        // and let's show some notifications too!
        for (UpcomingEvent event : upcomingEvents) {
            if (event.notificationIsNecessary()) {
                NotificationUtils.addNotification(this, event);
            }
        }

        // TODO :: use AlarmManager or something similar to periodically (at least once a day) go
        // through the upcoming events and check if new notifications should be spawned!
    }

    private void showEventToUser(final UpcomingEvent event) {

        LinearLayout parentLayout = findViewById(R.id.dateContainerLayout);
        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView nextText = new TextView(this);
        nextText.setText("Kommst du zu " + event.getName() + " am " + event.getStrDate() + "?");
        nextText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        parentLayout.addView(nextText, paras);

        LinearLayout btnLayout = new LinearLayout(this);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        btnLayout.setWeightSum(2);
        parentLayout.addView(btnLayout, paras);

        LinearLayout.LayoutParams btnParas = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        // gives each of the events a bit of distance to the others
        btnParas.setMargins(0, 0, 0, 8);

        Button bYesBtn = new Button(this);
        bYesBtn.setText("Ja, ich komme");
        bYesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerQuestion(event, true);
            }
        });
        btnLayout.addView(bYesBtn, btnParas);

        Button bNoBtn = new Button(this);
        bNoBtn.setText("Nein, ich kann nicht");
        bNoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerQuestion(event, false);
            }
        });
        btnLayout.addView(bNoBtn, btnParas);
    }

    private void answerQuestion(UpcomingEvent event, boolean canGo) {

        event.setAnswerByUser(canGo);

        Intent showResultMsgIntent = new Intent(this, ResultActivity.class);
        showResultMsgIntent.putExtra(com.asofterspace.toolbox.notification.NotificationUtils.KEY_RESULT, canGo);
        showResultMsgIntent.putExtra(UpcomingEventUtils.KEY_EVENT, event.getId());
        startActivity(showResultMsgIntent);
    }

}
