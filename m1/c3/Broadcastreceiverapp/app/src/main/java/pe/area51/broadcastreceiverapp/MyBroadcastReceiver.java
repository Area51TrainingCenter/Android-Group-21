package pe.area51.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        final String message;
        switch (intent.getAction()){
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                final boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if(isAirplaneModeOn){
                    message = context.getString(R.string.airplane_mode_on);
                }else{
                    message = context.getString(R.string.airplane_mode_off);
                }
                break;
            case LocationManager.PROVIDERS_CHANGED_ACTION:
                message = context.getString(R.string.location_provider_state_changed);
                break;
            default:
                message = "N/A";
                break;
        }
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
