package pe.area51.visitorbroadcaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final static String ACTION_NEW_VISITOR = "pe.area51.visitorbroadcaster.NEW_VISITOR";
    private final static String EXTRA_VISITOR_NAME = "visitor_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText visitorNameEditText = (EditText) findViewById(R.id.activity_main_edittext_visitorname);
        findViewById(R.id.activity_main_button_registervisitor)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String visitorName = visitorNameEditText.getText().toString();
                        final Intent newVisitorIntent = new Intent(ACTION_NEW_VISITOR);
                        /*
                        Este flag sirve para que incluso si la aplicación está en estado
                        "Stopped" pueda recibir el broadcast. Una vez lo reciba, la aplicación
                        pasará a estado "Non Stopped".
                        Más información: "http://developer.android.com/about/versions/android-3.1.html",
                        en la sección "Launch controls on stopped applications".
                         */
                        newVisitorIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        newVisitorIntent.putExtra(EXTRA_VISITOR_NAME, visitorName);
                        sendBroadcast(newVisitorIntent);
                    }
                });
    }
}
