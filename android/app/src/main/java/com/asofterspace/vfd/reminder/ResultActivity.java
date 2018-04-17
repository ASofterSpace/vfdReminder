package com.asofterspace.vfd.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import reminder.vfd.asofterspace.com.vfdreminder.R;

import static com.asofterspace.vfd.reminder.NotificationUtils.KEY_RESULT;

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

        if (affirmative) {
            resultText.setText("Super, voll gut dass du kommst!");
        } else {
            resultText.setText("Na okay, mit schwerem Herzen nehmen wir deine Abwesenheit mal hin...");
        }
    }
}
