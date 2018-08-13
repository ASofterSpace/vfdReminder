package com.asofterspace.vfd.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.asofterspace.vfd.reminder.events.UpcomingEvent;
import com.asofterspace.vfd.reminder.utils.UpcomingEventUtils;

import reminder.vfd.asofterspace.com.vfdreminder.R;

import static com.asofterspace.toolbox.notification.NotificationUtils.KEY_RESULT;

/**
 * This class represents an activity that shows a message to the
 * user as a result of the user's actions.
 */

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        boolean affirmative = getIntent().getBooleanExtra(KEY_RESULT, false);

        final TextView resultText = this.findViewById(R.id.resultText);

        String dateStr = "";

        int eventId = getIntent().getIntExtra(UpcomingEventUtils.KEY_EVENT, 0);
        UpcomingEvent event = UpcomingEventUtils.getUpcomingEventById(eventId);

        if (event != null) {
            dateStr = event.getStrDate();
        }

        if (affirmative) {
            resultText.setText("Super, voll gut dass du am " + dateStr + " kommst!");
        } else {
            resultText.setText("Mit schwerem Herzen nehmen wir deine Abwesenheit am " + dateStr + " mal hin...");
        }
    }
}
