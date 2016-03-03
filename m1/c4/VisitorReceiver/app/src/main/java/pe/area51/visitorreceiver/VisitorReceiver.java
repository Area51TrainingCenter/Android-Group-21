package pe.area51.visitorreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class VisitorReceiver extends BroadcastReceiver {

    private final static String ACTION_NEW_VISITOR = "pe.area51.visitorbroadcaster.NEW_VISITOR";
    private final static String EXTRA_VISITOR_NAME = "visitor_name";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_NEW_VISITOR)) {
            final String visitorName = intent.getStringExtra(EXTRA_VISITOR_NAME);
            final String welcomeMessage = context.getString(R.string.new_visitor, visitorName);
            Toast.makeText(context, welcomeMessage, Toast.LENGTH_SHORT).show();
        }
    }

}
