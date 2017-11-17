package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.User;

public class AddUbuUser extends AppCompatActivity {

    private EditText userName;
    private EditText userPass;
    private User user;
    private Button accept;
    private String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ubu_user);

        //instanciamos los widgets antes mencionados
        userName=(EditText)findViewById(R.id.etUserUbuAccess);
        userPass=(EditText)findViewById(R.id.etPasswordUbuAccess);
        accept=(Button)findViewById(R.id.btAcceptAddUbu);
        //obtenemos la instancia de alarm
        user=User.getInstance();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //asignamos a los campos de twiter user name y pass
                aux=userName.getText().toString();
                user.setUbuUser(aux);
                aux=userPass.getText().toString();
                user.setUbuPass(aux);

                startActivity(new Intent(AddUbuUser.this, Access.class));
            }
        });
    }
}
