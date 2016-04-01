package pe.area51.parcelableexample;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_show_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
                intent.putExtra(PreviewActivity.ARG_PERSON, createNewPerson());
                startActivity(intent);
            }
        });
    }

    private Person createNewPerson() {
        final Location location = new Location("mock");
        location.setTime(System.currentTimeMillis());
        final Address address = new Address(
                "Av. Aramburú",
                "Lima",
                "Perú",
                location
        );
        final Person person = new Person(
                "Pedro Arturo",
                "Galvez García",
                24,
                address,
                "123456789",
                "example@example.com",
                "1234567"
        );
        return person;
    }

}
