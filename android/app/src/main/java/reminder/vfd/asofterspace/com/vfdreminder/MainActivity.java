package reminder.vfd.asofterspace.com.vfdreminder;

import android.app.Notification;
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

import static reminder.vfd.asofterspace.com.vfdreminder.NotificationUtils.KEY_NOTIFICATION_ID;
import static reminder.vfd.asofterspace.com.vfdreminder.NotificationUtils.KEY_RESULT;

public class MainActivity extends AppCompatActivity {

    private Context context;

    final private static String REMINDER_CHANNEL_ID = "ff25erinnerungen";

    private boolean channelHasBeenSetUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final Button bTestWithBtn = this.findViewById(R.id.notificationBtn);
        bTestWithBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNotification();
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

    private void ensureNotificationChannelExists() {

        if (channelHasBeenSetUp) {
            return;
        }

        // only actually play with the channel if the API is high enough
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // creating the channel
            NotificationChannel channel = new NotificationChannel(REMINDER_CHANNEL_ID, "FF25 Erinnerungen", NotificationManagerCompat.IMPORTANCE_DEFAULT);
            channel.setDescription("Alle Erinnerungen der FF25.");

            // registering the channel
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        channelHasBeenSetUp = true;
    }

    private void addNotification() {

        ensureNotificationChannelExists();

        int notificationId = NotificationUtils.getNotificationId();

        // We create the intent to open the select yes / no activity
        Intent selectYesNoIntent = new Intent(this, MainActivity.class);
        selectYesNoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        selectYesNoIntent.putExtra(KEY_NOTIFICATION_ID, notificationId);

        // Now we create another intent, which is to launch the select yes / no intent upon a tap on a notification
        PendingIntent tapIntent = PendingIntent.getActivity(this, 0, selectYesNoIntent, 0);

        // Actually create our notification
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_vfd_logo_1)
                .setContentTitle("FF25 Erinnerung")
                .setContentText("Am Freitag ist Dienst. Kommst du?")
                .setContentIntent(tapIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false);
        // autocancel cancels when the user taps - but we want to cancel upon select, not upon tap!

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // We actually invoke the notification
        notificationManager.notify(notificationId, notBuilder.build());
    }

    private void answerQuestion(boolean affirmative) {

        Intent showResultMsgIntent = new Intent(this, ResultActivity.class);
        showResultMsgIntent.putExtra(KEY_RESULT, affirmative);
        startActivity(showResultMsgIntent);
    }

}
