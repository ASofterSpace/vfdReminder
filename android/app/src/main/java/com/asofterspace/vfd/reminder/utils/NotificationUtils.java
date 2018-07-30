package com.asofterspace.vfd.reminder.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.asofterspace.vfd.reminder.MainActivity;
import com.asofterspace.vfd.reminder.ResultActivity;

import reminder.vfd.asofterspace.com.vfdreminder.R;

/**
 * Created by Moya on 30.07.2018.
 */

public class NotificationUtils {

    final private static String REMINDER_CHANNEL_ID = "ff25erinnerungen";

    private static boolean channelHasBeenSetUp = false;


    private static void ensureNotificationChannelExists(Context context) {

        if (channelHasBeenSetUp) {
            return;
        }

        // only actually play with the channel if the API is high enough
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // creating the channel
            NotificationChannel channel = new NotificationChannel(REMINDER_CHANNEL_ID, "FF25 Erinnerungen", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Alle Erinnerungen der FF25.");

            // registering the channel
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        channelHasBeenSetUp = true;
    }

    public static void addNotification(Context context) {

        ensureNotificationChannelExists(context);

        int notificationId = com.asofterspace.toolbox.notification.NotificationUtils.generateNotificationId();

        // We create the intent to open the select yes / no activity
        Intent selectYesNoIntent = new Intent(context, MainActivity.class);
        selectYesNoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        selectYesNoIntent.putExtra(com.asofterspace.toolbox.notification.NotificationUtils.KEY_NOTIFICATION_ID, notificationId);

        // Now we create another intent, which is to launch the select yes / no intent upon a tap on a notification
        PendingIntent tapIntent = PendingIntent.getActivity(context, 0, selectYesNoIntent, 0);

        // Actually create our notification
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_vfd_logo_1)
                .setContentTitle("FF25 Erinnerung")
                .setContentText("Am Freitag ist regulärer Dienst. Kommst du?")
                .setContentIntent(tapIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false);
        // autocancel cancels when the user taps - but we want to cancel upon select, not upon tap!

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // We actually invoke the notification
        notificationManager.notify(notificationId, notBuilder.build());
    }

}
