package pe.area51.parcelableexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    public static final String ARG_PERSON = "person";

    private TextView previewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        previewTextView = (TextView) findViewById(R.id.textview_preview);
        showPerson((Person) getIntent().getParcelableExtra(ARG_PERSON));
    }

    private void showPerson(final Person person) {
        previewTextView.setText(person.toString());
    }
}
