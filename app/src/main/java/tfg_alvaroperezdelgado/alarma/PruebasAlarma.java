package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import Model.Alarm;

public class PruebasAlarma extends AppCompatActivity {

    private TextView tvHora;
    private TextView tvMinuto;
    private TextView tvlistaDias;
    private TextView tvCustomMessage;
    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_alarma);
        //instanciamos todos los textView que tenemos
        tvHora = (TextView) findViewById(R.id.tvHoraPrueba);
        tvMinuto = (TextView) findViewById(R.id.tvMinutosPrueba);
        tvlistaDias = (TextView) findViewById(R.id.tvListaDias);
        tvCustomMessage=(TextView)findViewById(R.id.tvMensajeCustomizado);
        //obtenemos la instancia de alarm
        alarm = Alarm.getInstance();
        //pasamos a
        String horaString = String.valueOf(alarm.getHour());
        String minString = String.valueOf(alarm.getMin());
        List<Boolean> listadias = alarm.getAlarmDays();
        String lista = listadias.get(0).toString() + listadias.get(1).toString() + listadias.get(2).toString() + listadias.get(3).toString() + listadias.get(4).toString() + listadias.get(5).toString() + listadias.get(6).toString();
        tvHora.setText(horaString);
        tvMinuto.setText(minString);
        tvlistaDias.setText(lista);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PruebasAlarma.this, addAlarm.class));
            }
        });
    }
}
