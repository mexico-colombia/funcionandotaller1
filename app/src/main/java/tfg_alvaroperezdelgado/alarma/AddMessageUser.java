package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.Container;

/*
* Clase AddMessageUser
* En esta clase guardamos en nuestro objeto alarma
* */
public class AddMessageUser extends AppCompatActivity {

    //Declaramos los widgets que vamos a usar
    //cadena par aguardar el mensaje personalizado
    private String aux;
    //boton de aceptar de la activiti que guardará los valores y retornara a la anterior vista
    private Button acept;
    //campo donde introduciremos el texto que queremos guardar
    private EditText message;
    //objeto singleton alarm donde guardaremos el mensaje
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message_user);

        //instanciamos los widgets antes mencionados
        acept=(Button)findViewById(R.id.btAccessMessageAcept);
        message=(EditText)findViewById(R.id.etCustonMessage);

        //obtenemos la instancia del contenedor
        container=Container.getInstance();

        //Controlar lo que pasa al hacer click en el boton aceptar
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux=message.getText().toString();
                container.setCustomMessage(aux);
                startActivity(new Intent(AddMessageUser.this, addAlarm.class));
            }
        });

    }


}
