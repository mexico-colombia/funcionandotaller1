package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.User;

public class AddWeatherUser extends AppCompatActivity {

    private EditText zipCode;
    private User user;
    private Button accept;
    private String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weather_user);

        //instanciamos los widgets antes mencionados

        zipCode=(EditText)findViewById(R.id.etUserWeatherAccess);
        accept=(Button)findViewById(R.id.btAcceptAddWeather);
        //obtenemos la instancia de alarm
        user=User.getInstance();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux=zipCode.getText().toString();
                user.setZipCode(aux);
                startActivity(new Intent(AddWeatherUser.this, Access.class));
            }
        });
    }
}
