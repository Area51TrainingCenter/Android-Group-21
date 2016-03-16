package pe.area51.handlerrepetitivetask;

import android.os.Handler;

public class RepetitiveTask {

    private boolean isRunning;

    private final Handler callerThreadHandler;
    private final Runnable internalRunnable;

    public RepetitiveTask(final Runnable task, final long frequencyInMillis) {
        callerThreadHandler = new Handler();
        isRunning = false;
        internalRunnable = new Runnable() {
            @Override
            public void run() {
                task.run();
                callerThreadHandler.postDelayed(this, frequencyInMillis);
            }
        };
    }

    public boolean start() {
        if (isRunning) {
            return false;
        } else {
            callerThreadHandler.post(internalRunnable);
            isRunning = true;
            return true;
        }
    }

    public boolean stop() {
        if (isRunning) {
            callerThreadHandler.removeCallbacks(internalRunnable);
            isRunning = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
