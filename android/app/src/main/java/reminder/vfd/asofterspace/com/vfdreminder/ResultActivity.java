package reminder.vfd.asofterspace.com.vfdreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static reminder.vfd.asofterspace.com.vfdreminder.NotificationUtils.KEY_RESULT;

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
