package pe.area51.myservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText userNameEditText = (EditText) findViewById(R.id.edittext_username);
        findViewById(R.id.button_startservice)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String userName = userNameEditText.getText().toString();
                        startService(
                                new Intent(MainActivity.this, MyService.class)
                                        .putExtra(MyService.ARG_USER_NAME, userName)
                        );
                    }
                });
        findViewById(R.id.button_stopservice)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopService(new Intent(MainActivity.this, MyService.class));
                    }
                });
    }
}
