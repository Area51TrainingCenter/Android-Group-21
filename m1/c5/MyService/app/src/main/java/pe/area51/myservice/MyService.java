package pe.area51.myservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {

    public static final String ARG_USER_NAME = "user_name";

    private static final int FOREGROUND_NOTIFICATION_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showMessage("onCreate()");
        startForeground(FOREGROUND_NOTIFICATION_ID, createForegroundNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String userName = intent.getStringExtra(ARG_USER_NAME);
        showMessage(getString(R.string.service_message, userName));
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showMessage("onDestroy()");
    }

    private Notification createForegroundNotification() {
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setContentTitle(getString(R.string.service_foreground_notification_title))
                .setContentText(getString(R.string.service_foreground_notification_content))
                .setSmallIcon(R.drawable.my_service_notification_icon);
        return notificationBuilder.build();
    }

    private void showMessage(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
