package reminder.vfd.asofterspace.com.vfdreminder;

import android.app.NotificationChannel;
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
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final Button bTest = this.findViewById(R.id.button);

        bTest.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    addNotification();
            }
        });
    }

    private void addNotification() {

        // Tell android what to do when someone clicks on our notification
        Intent intent = new Intent(this, this.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent tapIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Actually create our notification
        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.vfd_logo_1)
                .setContentTitle("FF25 Erinnerung")
                .setContentText("Am Freitag ist Dienst. Kommst du?")
                .setContentIntent(tapIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // autocancel cancels when the user taps - may not be what we want!

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // The ID needs to be UNIQUE for each notification instance! - TODO :: do not hardcode to 9
        notificationManager.notify(9, notBuilder.build());
    }

}
