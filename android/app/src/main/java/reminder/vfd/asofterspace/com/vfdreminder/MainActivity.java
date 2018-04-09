package reminder.vfd.asofterspace.com.vfdreminder;

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

public class MainActivity extends AppCompatActivity {

    private Context context;

    private int latestNotification = 0;

    final private static String REMINDER_CHANNEL_ID = "ff25erinnerungen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final Button bChannel = this.findViewById(R.id.channelButton);
        bChannel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setupNotificationChannel();
            }
        });

        final Button bTest = this.findViewById(R.id.testButton);
        bTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNotification();
            }
        });

        final Button bTestWithBtn = this.findViewById(R.id.testButtonWithBtn);
        bTestWithBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNotificationWithButton();
            }
        });
    }

    private void setupNotificationChannel() {
        // only actually play with the channel if the API is high enough
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // creating the channel
            NotificationChannel channel = new NotificationChannel(REMINDER_CHANNEL_ID, "FF25 Erinnerungen", NotificationManagerCompat.IMPORTANCE_DEFAULT);
            channel.setDescription("Alle Erinnerungen der FF25.");

            // registering the channel
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {

        // Tell android what to do when someone clicks on our notification
        Intent intent = new Intent(this, this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent tapIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Actually create our notification
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
                .setSmallIcon(R.drawable.vfd_logo_1)
                .setContentTitle("FF25 Erinnerung")
                .setContentText("Am Freitag ist Dienst. Kommst du?")
                .setContentIntent(tapIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // autocancel cancels when the user taps - may not be what we want!

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // The ID needs to be UNIQUE for each notification instance!
        latestNotification++;
        notificationManager.notify(latestNotification, notBuilder.build());
    }

    private void addNotificationWithButton() {

        // Tell android what to do when someone clicks on a button in our notification
        Intent affirmative = new Intent(this, ReminderConfirmActivity.class);
        affirmative.setAction("JA");
        PendingIntent affirmativeIntent = PendingIntent.getBroadcast(this, 0, affirmative, 0);
        Intent negative = new Intent(this, ReminderDenyActivity.class);
        negative.setAction("NEIN");
        PendingIntent negativeIntent = PendingIntent.getBroadcast(this, 0, negative, 0);

        // Actually create our notification
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, REMINDER_CHANNEL_ID)
                .setSmallIcon(R.drawable.vfd_logo_1)
                .setContentTitle("FF25 Erinnerung")
                .setContentText("Am Freitag ist Dienst. Kommst du?")
                .addAction(R.drawable.vfd_logo_1, "ja", affirmativeIntent)
                .addAction(R.drawable.vfd_logo_1, "nein", negativeIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // autocancel cancels when the user taps - may not be what we want!

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // The ID needs to be UNIQUE for each notification instance!
        latestNotification++;
        notificationManager.notify(latestNotification, notBuilder.build());
    }

}
