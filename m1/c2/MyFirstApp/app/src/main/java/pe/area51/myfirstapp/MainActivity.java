package pe.area51.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	//Generamos la vista ("View") de este Activity a partir un recurso de Layout.
        setContentView(R.layout.activity_main);
        //En este punto ya existe la vista del Activity, por lo que podremos obtener las referencias de los elementos ("View") de nuestra vista con el método "findViewById".
	//Un "View" puede contener a otros "View". En este caso nuestro View ("LinearLayout") tiene otros View ("TextView", "EditText", "Button").
        final EditText nameEditText = (EditText) findViewById(R.id.edittext_name);
        final Button showWelcomeButton = (Button) findViewById(R.id.button_show_welcome);
	//Establecemos el listener "OnClickListener" para capturar el evento de click del botón.
        showWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
		//Obtenemos el nombre del EditText.
                final String name = nameEditText.getText().toString();
		//Creamos el Intent para iniciar el Activity.
                final Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
		//Asignamos al intent el nombre para que su valor pueda ser obtenido en el otro Activity.
                intent.putExtra(WelcomeActivity.ARG_NAME, name);
		//Iniciamos el Activity.
                startActivity(intent);
            }
        });

    }
}
