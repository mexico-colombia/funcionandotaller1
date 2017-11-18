package com.omar.alarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import Model.Alarm;


public class addAlarm extends AppCompatActivity {
    //alarmManager
    private AlarmManager alarmManager;

    //time picker como obtendremos la hora de la alarma
    private TimePicker alarmTimePicker;

    //texto del medio
    private TextView tvUpdateText;

    private Context context;

    //objeto alarma
    private Alarm alarm;
    //Create a pendidgIntent that delays the intent
    //until the specified calendar time
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        //obtenemos el objeto alarma
        this.context = this;

        //inicialize our alarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //inicialize our timePicker
        alarmTimePicker = (TimePicker) findViewById(R.id.tmTimePicker);

        //inicialize text update box
        tvUpdateText = (TextView) findViewById(R.id.tvUpdateText);

        //create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();


        //obtenemos el objeto alarma
        alarm = Alarm.getInstance();

        //create an intent to the alarm receiver class
        //tells which receiver to send signal to
        final Intent intent = new Intent(this.context, AlarmReceiver.class);

        //inicialice buttons
        Button startAlarm = (Button) findViewById(R.id.btAcceptAdd);

        //El boton dias me lleva al layout dias
        findViewById(R.id.btDiasAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addAlarm.this, Days.class));
            }
        });

        //el boton cancel me lleva directamente a mainActivity
        findViewById(R.id.btCancelAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addAlarm.this, MainActivity.class));
            }
        });
        findViewById(R.id.btOptionsAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addAlarm.this, Options.class));
            }
        });
        //create onClick listener to start the alarm
        startAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour,minute;


                //Esto comprueba si la version es superior a la que necesita o no para usar un metodo u otro
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //estamos asociando a nuestro calendario a traves del timePicker la hora y el dia
                    //setting calendar instance with the Hour and minute we picked
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());//getCurrentHour()
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());//getCurrentMinute()
                    calendar.set(Calendar.SECOND, 0);
                    //get the string values of the hour and minute
                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                }else{
                    //estamos asociando a nuestro calendario a traves del timePicker la hora y el dia
                    //setting calendar instance with the Hour and minute we picked
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    calendar.set(Calendar.SECOND, 0);

                    hour = alarmTimePicker.getCurrentHour();
                    minute = alarmTimePicker.getCurrentMinute();
                }
                //guardamos en alarma la hora y el minuto
                alarm.setMin(minute);
                alarm.setHour(hour);

                //Convert the String values to string
                String stringHour = String.valueOf(hour);
                String stringMinute = String.valueOf(minute);

                //convertir a 12 hour time
                if (hour > 12) {
                    stringHour = String.valueOf(hour - 12);
                }

                //convertir minuto 10:7 a 10:07
                if (minute < 10) {
                    stringMinute = String.valueOf("0" + minute);

                }

                //method that changes the updatetext Textbox
                setAlarmText("Alarma " + stringHour + " : " + stringMinute);

                //put in extra string into intent
                //tell the clock that you pressed the alarm on button
                //put extra works putting name and string
                intent.putExtra("extra", "alarm on");

                //Create a pendidgIntent that delays the intent
                //until the specified calendar time
                //tels the alarm manager to send a delayed intent
                pendingIntent = PendingIntent.getBroadcast(addAlarm.this, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                //le pasamos el reloj de tiempo real, el calendario que contiene la hora y minuto a los que vamos a poner la alarma
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()
                        , pendingIntent);

            }
        });


        //inicialice stopButton
        Button stopAlarm = (Button) findViewById(R.id.btCancelAdd);

        //create onClick listener to stop the alarm
        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method that changes the updatetext Textbox
                setAlarmText("Alarm off!");

                //cancel the alarm
                alarmManager.cancel(pendingIntent);

                //put extra string into intent
                //tells the clock that you pressed the alarm off button
                intent.putExtra("extra", "alarm off");


                //stop the ringtone
                sendBroadcast(intent);

                startActivity(new Intent(addAlarm.this, MainActivity.class));

            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(addAlarm.this, WeatherActivity.class));
            }
        });


    }

    //metodo que cambia el texto de dentro de un text view
    private void setAlarmText(String s) {
       tvUpdateText.setText(s);

    }


}
