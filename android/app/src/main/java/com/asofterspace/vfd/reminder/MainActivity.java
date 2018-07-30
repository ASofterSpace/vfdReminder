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
import android.view.View;
import android.widget.Button;

import com.asofterspace.vfd.reminder.utils.NotificationUtils;
import com.asofterspace.vfd.reminder.utils.UpcomingEventUtils;
import com.asofterspace.vfd.reminder.utils.UpcomingEventsInitializedCallback;

import reminder.vfd.asofterspace.com.vfdreminder.R;

public class MainActivity extends AppCompatActivity implements UpcomingEventsInitializedCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpcomingEventUtils.initialize(this);

        final Context context = this;

        final Button bTestWithBtn = this.findViewById(R.id.notificationBtn);
        bTestWithBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationUtils.addNotification(context);
            }
        });

        final Button bYesBtn = this.findViewById(R.id.yesBtn);
        bYesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerQuestion(true);
            }
        });

        final Button bNoBtn = this.findViewById(R.id.noBtn);
        bNoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerQuestion(false);
            }
        });
    }

    public void initDone() {
        // TODO - the UpcomingEventUtils is ready; let's display some dates to the user!
    }

    private void answerQuestion(boolean affirmative) {

        Intent showResultMsgIntent = new Intent(this, ResultActivity.class);
        showResultMsgIntent.putExtra(com.asofterspace.toolbox.notification.NotificationUtils.KEY_RESULT, affirmative);
        startActivity(showResultMsgIntent);
    }

}
