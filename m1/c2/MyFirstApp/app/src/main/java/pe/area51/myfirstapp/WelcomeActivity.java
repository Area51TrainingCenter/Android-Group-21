package pe.area51.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    //Ponemos en una constante la llave ("key") para almacenar el nombre en el Intent.
    public static final String ARG_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final TextView welcomeMessageTextView = (TextView) findViewById(R.id.textview_welcome_message);
	//Obtenemos el Intent que inició este Activity ("getIntent") y de este el nombre.
        final String name = getIntent().getStringExtra(ARG_NAME);
	//Ponemos el nombre en el mensaje de bienvenida. Este mensaje tiene la expresión "%s" que será reemplazada por el nombre.
        final String welcomeMessage = getString(R.string.welcome_message, name);
	//Mostramos el mensaje.
        welcomeMessageTextView.setText(welcomeMessage);
    }
}
