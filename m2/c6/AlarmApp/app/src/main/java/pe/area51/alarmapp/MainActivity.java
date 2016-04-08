package pe.area51.alarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText timeInSecondsEditText = (EditText) findViewById(R.id.edittext_time_in_seconds);
        findViewById(R.id.button_program_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final int timeInSeconds = Integer.parseInt(timeInSecondsEditText.getText().toString());
                    programAlarm(timeInSeconds);
                    showMessage(getString(R.string.alarm_programmed));
                } catch (NumberFormatException e) {
                    showMessage(getString(R.string.number_error));
                }
            }
        });
    }

    private void programAlarm(final int timeInSeconds) {
        final PendingIntent operation = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, AlarmActivity.class)
                        .setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES),
                PendingIntent.FLAG_ONE_SHOT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final long currentTimeMillis = SystemClock.elapsedRealtime();
        final long timeInMillis = timeInSeconds * 1000;
        setAlarmExact(alarmManager, currentTimeMillis + timeInMillis, operation);
    }

    private static void setAlarmExact(final AlarmManager alarmManager, final long triggerAtMillis, final PendingIntent operation) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerAtMillis,
                    operation
            );
        } else {
            alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerAtMillis,
                    operation
            );
        }
    }

    private void showMessage(final String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
