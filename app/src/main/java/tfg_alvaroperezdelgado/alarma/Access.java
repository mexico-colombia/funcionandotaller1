package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Access extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        //El boton btTwitterAccess nos lleva hasta addTwitterUser
        findViewById(R.id.btTwitterAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, addTwitterUser.class));
            }
        });

        //El boton btUBUAccess nos lleva hasta addUbuUser
        findViewById(R.id.btUBUAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, AddUbuUser.class));
            }
        });

        //El boton btWeatherAccess nos lleva hasta AddWeatherUser
        findViewById(R.id.btWeatherAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, AddWeatherUser.class));
            }
        });

        //El boton btAcceptAccess nos lleva hasta MainActivity
        findViewById(R.id.btAcceptAccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Access.this, MainActivity.class));
            }
        });

    }

}
