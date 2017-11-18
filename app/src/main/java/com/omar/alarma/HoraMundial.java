package com.omar.alarma;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HoraMundial extends AppCompatActivity {
    android.icu.util.Calendar current;
    Spinner spinner;
    TextView timezone,txtCurrentTime,txtTimeZone;
    long milisegundos;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat sdf;
    Date resultDate;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora_mundial);


        spinner=(Spinner)findViewById(R.id.spinner);
        timezone=(TextView)findViewById(R.id.TimeZone);
        txtCurrentTime=(TextView)findViewById(R.id.txtHoraActual);
        txtTimeZone=(TextView)findViewById(R.id.txtTimeZone);


        String[] idArray= TimeZone.getAvailableIDs();
        sdf=new SimpleDateFormat("EEEE, ddMMMM yyyy HH:mm:ss");
        idAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idArray);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);
        getGMTtime();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                getGMTtime();
                String selectedID=(String)(parent.getItemAtPosition(position));

                TimeZone timeZone=TimeZone.getTimeZone(selectedID);
                String TimeZoneName=timeZone.getDisplayName();

                int TimeZoneOffset=timeZone.getRawOffset()/(60*1000);

                int hrs=TimeZoneOffset/60;
                int mins=TimeZoneOffset%60;

                milisegundos=milisegundos+timeZone.getRawOffset();

                resultDate=new Date(milisegundos);
                System.out.println(sdf.format(resultDate));

                timezone.setText(TimeZoneName+" : GMT " +hrs +":"+mins);
                txtTimeZone.setText(""+sdf.format(resultDate));
                milisegundos=0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getGMTtime(){
        current= android.icu.util.Calendar.getInstance();
        txtCurrentTime.setText(""+current.getTime());
        milisegundos=current.getTimeInMillis();
        TimeZone tzCurrent=current.getTimeZone();
        int offset=tzCurrent.getRawOffset();
        if(tzCurrent.inDaylightTime(new Date())){
            offset=offset + tzCurrent.getDSTSavings();

        }

        milisegundos=milisegundos-offset;

        resultDate=new Date(milisegundos);
        System.out.println(sdf.format(resultDate));

/*

        this.context = this;

        //alarm = new AlarmReceiver();
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);

        //  Obtengo el manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // configurar la alarma a la hora que elija
        final Calendar calendar = Calendar.getInstance();

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);



        Button start_alarma= (Button) findViewById(R.id.start_alarma);
        start_alarma.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND, 3);
                //setAlarmText("You clicked a button");

                final int hour = alarmTimePicker.getHour();
                final int minute = alarmTimePicker.getMinute();;

                Log.e("MyActivity", "In the receiver with " + hour + " and " + minute);
                setAlarmText("You clicked a " + hour + " and " + minute);


                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

                myIntent.putExtra("extra", "yes");
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                // now you should change the set Alarm text so it says something nice


                setAlarmText("Alarma programada a las: " + hour + ":" + minute);
                //Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
            }

        });

        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));

                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
                setAlarmText("Tono de alarma");
                //setAlarmText("You clicked a " + " canceled");
            }
        });
*/
    }


}
