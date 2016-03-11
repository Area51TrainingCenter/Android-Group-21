package pe.area51.handlerasynctask;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_execute_long_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HandlerAsyncTask<Boolean, Integer>() {

                    ProgressDialog progressDialog;

                    @Override
                    protected void onPreExecute() {
                        progressDialog = createProgressDialog();
                        progressDialog.show();
                    }

                    @Override
                    protected Boolean doInBackground() {
                        doLongTask();
                        postProgress(25);
                        doLongTask();
                        postProgress(50);
                        doLongTask();
                        postProgress(75);
                        doLongTask();
                        postProgress(100);
                        return true;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        progressDialog.dismiss();
                    }

                    @Override
                    protected void onProgressUpdate(Integer integer) {
                        progressDialog.setProgress(integer);
                    }
                }.execute();
            }
        });
    }

    private void doLongTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ProgressDialog createProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.progress_title);
        progressDialog.setMessage(getString(R.string.progress_content));
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
