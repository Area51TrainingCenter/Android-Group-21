package pe.area51.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorInfoTextView;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorInfoTextView = (TextView) findViewById(R.id.textview_sensor_info);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAccelerometer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAccelerometer();
    }

    private void startAccelerometer() {
        final Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void stopAccelerometer() {
        sensorManager.unregisterListener(this);
    }

    private void showAccelerometerInfo(final SensorEvent event) {
        final String xAxisValue = String.valueOf(event.values[0]);
        final String yAxisValue = String.valueOf(event.values[1]);
        final String zAxisValue = String.valueOf(event.values[2]);
        final String accelerometerInfoString = new StringBuilder()
                .append(getString(R.string.x_axis, xAxisValue))
                .append("\n")
                .append(getString(R.string.y_axis, yAxisValue))
                .append("\n")
                .append(getString(R.string.z_axis, zAxisValue))
                .toString();
        sensorInfoTextView.setText(accelerometerInfoString);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            showAccelerometerInfo(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
