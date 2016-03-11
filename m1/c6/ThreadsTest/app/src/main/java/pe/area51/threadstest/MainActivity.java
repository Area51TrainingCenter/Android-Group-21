package pe.area51.threadstest;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_main_thread).setOnClickListener(this);
        findViewById(R.id.button_worker_thread).setOnClickListener(this);
        findViewById(R.id.button_async_task).setOnClickListener(this);
    }

    private void doLongTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ProgressDialog createProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.progress_title);
        progressDialog.setMessage(getString(R.string.progress_content));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    private void runLongTaskOnMainThread() {
        final ProgressDialog progressDialog = createProgressDialog();
        progressDialog.show();
        doLongTask();
        progressDialog.dismiss();
    }

    private void runLongTaskOnWorkerThread() {
        final ProgressDialog progressDialog = createProgressDialog();
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                doLongTask();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private void runLongTaskOnAsyncTask() {
        new AsyncTask<String, Void, Boolean>() {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = createProgressDialog();
                progressDialog.show();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                doLongTask();
                return null;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                progressDialog.dismiss();
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_thread:
                runLongTaskOnMainThread();
                break;
            case R.id.button_worker_thread:
                runLongTaskOnWorkerThread();
                break;
            case R.id.button_async_task:
                runLongTaskOnAsyncTask();
                break;
        }
    }
}
