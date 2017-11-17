package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.List;

import Model.Alarm;

/**
 * Clase options
 * sirve para seleccionar que opciones queremos seleccionar para que suenen con nuestra alarma
 */
public class Options extends AppCompatActivity {

    //objet alarm
    private Alarm alarm;
    private Switch swTwitter,swUbuMail,swUbuCalendar,swWeather,swCustomMessage,swMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //get id switches
        swCustomMessage = (Switch) findViewById(R.id.swCustomMessageAdd);
        swUbuMail = (Switch) findViewById(R.id.swUbuMailAdd);
        swUbuCalendar = (Switch) findViewById(R.id.swUbuCalendarAdd);
        swTwitter = (Switch) findViewById(R.id.swTwitterAdd);
        swWeather = (Switch) findViewById(R.id.swWeatherAdd);
        swMusic = (Switch) findViewById(R.id.swMusicAdd);
        alarm = Alarm.getInstance();

        List<Boolean> dias;

        dias = alarm.getAlarmDays();

        //mostraremos el switch activado o desactivado en funcion de si esta o no activado
        if (dias.get(0) == true) {
            swCustomMessage.setChecked(true);
        }
        if (dias.get(1) == true) {
            swUbuMail.setChecked(true);
        }
        if (dias.get(2) == true) {
            swUbuCalendar.setChecked(true);
        }
        if (dias.get(3) == true) {
            swTwitter.setChecked(true);
        }
        if (dias.get(4) == true) {
            swWeather.setChecked(true);
        }
        if (dias.get(5) == true) {
            swMusic.setChecked(true);
        }

        findViewById(R.id.btCustomMessageOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, AddMessageUser.class));
            }
        });

        findViewById(R.id.btAcceptOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //primero borramos la lista anterior de dias
                alarm.deleteListDays();
                //añadimos el valor que tenga cada dia
                alarm.setAlarmDays(1, swCustomMessage.isChecked());
                alarm.setAlarmDays(2, swUbuMail.isChecked());
                alarm.setAlarmDays(3, swUbuCalendar.isChecked());
                alarm.setAlarmDays(4, swTwitter.isChecked());
                alarm.setAlarmDays(5, swWeather.isChecked());
                alarm.setAlarmDays(6, swMusic.isChecked());

                startActivity(new Intent(Options.this, addAlarm.class));
            }
        });
        findViewById(R.id.btCancelOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, addAlarm.class));
            }
        });
        findViewById(R.id.btMusicOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Options.this, SelectSong.class));
            }
        });
    }
}
