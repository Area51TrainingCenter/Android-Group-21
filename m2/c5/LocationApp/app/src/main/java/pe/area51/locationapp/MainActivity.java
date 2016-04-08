package pe.area51.locationapp;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private MyLocationListener myLocationListener;
    private TextView textViewLocationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewLocationData = (TextView) findViewById(R.id.textview_location_data);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        myLocationListener = new MyLocationListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationRetrieving();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationRetrieving();
    }


    private void startLocationRetrieving() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0,
                myLocationListener
        );
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0,
                myLocationListener
        );
        locationManager.requestLocationUpdates(
                LocationManager.PASSIVE_PROVIDER,
                0,
                0,
                myLocationListener
        );
    }

    private void stopLocationRetrieving() {
        locationManager.removeUpdates(myLocationListener);
    }

    private void showLocation(final Location location) {
        textViewLocationData.setText(location.toString());
    }

    private class MyLocationListener implements LocationListener {


        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
