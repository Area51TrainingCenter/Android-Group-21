package pe.area51.handlerasynctask;

import android.os.Handler;

public abstract class HandlerAsyncTask<Result, Progress> {

    private final Handler callerThreadHandler;

    public HandlerAsyncTask() {
        callerThreadHandler = new Handler();
    }

    protected abstract void onPreExecute();

    protected abstract Result doInBackground();

    protected abstract void onPostExecute(final Result result);

    protected void onProgressUpdate(final Progress progress) {

    }

    protected void postProgress(final Progress progress) {
        callerThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });
    }

    public void execute() {
        callerThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                onPreExecute();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Result result = doInBackground();
                callerThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        }).start();
    }
}
