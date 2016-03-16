package pe.area51.handlerrepetitivetask;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static long REPETITIVE_TASK_FREQUENCY = 1000;

    private long timeCounterInMillis;
    private TextView counterStatusTextView;
    private RepetitiveTask repetitiveTask;

    private long startTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeCounterInMillis = 0;
        repetitiveTask = createRepetitiveTask();
        counterStatusTextView = (TextView) findViewById(R.id.textview_counter_status);
        final Button buttonSwitchCounter = (Button) findViewById(R.id.button_switch_counter);
        buttonSwitchCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repetitiveTask.isRunning()) {
                    stopRepetitiveTask();
                    buttonSwitchCounter.setText(R.string.start_counter);
                } else {
                    startRepetitiveTask();
                    buttonSwitchCounter.setText(R.string.stop_counter);
                }
            }
        });
    }

    private RepetitiveTask createRepetitiveTask() {
        return new RepetitiveTask(createTask(), REPETITIVE_TASK_FREQUENCY);
    }

    private void resetTimeCounter() {
        timeCounterInMillis = 0;
    }

    private Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {
                timeCounterInMillis = SystemClock.elapsedRealtime() - startTimeInMillis;
                updateTextView();
            }
        };
    }

    private void updateTextView() {
        final String counter = String.valueOf(this.timeCounterInMillis);
        counterStatusTextView.setText(counter);
    }

    private void startRepetitiveTask() {
        startTimeInMillis = SystemClock.elapsedRealtime();
        repetitiveTask.start();
    }

    private void stopRepetitiveTask() {
        repetitiveTask.stop();
        resetTimeCounter();
        updateTextView();
    }
}
