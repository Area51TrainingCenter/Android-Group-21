package pe.area51.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String KEY_VISITOR_NAME = "visitorName";
    private TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewStatus = (TextView) findViewById(R.id.textview_status);
        final EditText editTextName = (EditText) findViewById(R.id.edittext_name);
        findViewById(R.id.button_register_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editTextName.getText().toString();
                saveVisitorName(name);
                updateStatusMessage(getString(R.string.welcome, name));
            }
        });
        showLastVisitorName();
    }

    private void showLastVisitorName() {
        final String lastVisitorName = loadLastVisitorName();
        if (lastVisitorName == null) {
            updateStatusMessage(getString(R.string.first_visitor));
        } else {
            updateStatusMessage(getString(R.string.last_visitor, lastVisitorName));
        }
    }

    private void updateStatusMessage(final String message) {
        textViewStatus.setText(message);
    }

    private String loadLastVisitorName() {
        return getSharedPreferences().getString(KEY_VISITOR_NAME, null);
    }

    private boolean saveVisitorName(final String visitorName) {
        return getSharedPreferences()
                .edit()
                .putString(KEY_VISITOR_NAME, visitorName)
                .commit();
    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences("lastVisitor", MODE_PRIVATE);
    }

}
