package com.omar.alarma;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class Cronometro extends AppCompatActivity {
    Button btn_iniciar, btn_detener, btn_parcial;
    //Chronometer cronometro;
    TextView Cronome;
    EditText Parcial;
    ScrollView Scv;

    private Crono mtCronome;
    private Thread mtThread;
    private Context mtContext;
    private int Par = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        mtContext = this;


        Cronome = (TextView) findViewById(R.id.Cronometro);
        Parcial = (EditText) findViewById(R.id.tiempos);
        Scv = (ScrollView) findViewById(R.id.parcial);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_detener = (Button) findViewById(R.id.btn_detener);
        btn_parcial = (Button) findViewById(R.id.btn_parcial);

        btn_iniciar.setEnabled(true);
        Parcial.setEnabled(false);

        btn_iniciar.setVisibility(View.VISIBLE);
        btn_detener.setVisibility(View.GONE);
        btn_parcial.setVisibility(View.GONE);
        Parcial.setVisibility(View.GONE);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (mtCronome == null) {
                    mtCronome = new Crono(mtContext);
                    mtThread = new Thread(mtCronome);
                    mtThread.start();
                    mtCronome.start();
                    Par = 1;
                    Parcial.setText("");


                    btn_detener.setEnabled(true);
                    btn_parcial.setEnabled(true);

                    btn_iniciar.setVisibility(View.GONE);
                    btn_parcial.setVisibility(View.VISIBLE);
                    btn_detener.setVisibility(View.VISIBLE);
                    Cronome.setVisibility(View.VISIBLE);
                    Parcial.setVisibility(View.VISIBLE);

                }

            }

        });
        btn_detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mtCronome != null) {
                    mtCronome.stop();
                    mtThread.interrupt();
                    mtThread = null;
                    mtCronome = null;

                    btn_iniciar.setEnabled(true);
                    btn_iniciar.setVisibility(View.VISIBLE);
                    btn_detener.setVisibility(View.GONE);
                    btn_parcial.setVisibility(View.GONE);
                    Parcial.setVisibility(View.VISIBLE);
                }
            }
        });


        btn_parcial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mtCronome == null) {
                    btn_detener.setEnabled(true);
                    btn_parcial.setEnabled(true);

                    btn_iniciar.setVisibility(View.GONE);
                    btn_parcial.setVisibility(View.VISIBLE);
                    btn_detener.setVisibility(View.VISIBLE);
                    Cronome.setVisibility(View.VISIBLE);
                    Parcial.setVisibility(View.VISIBLE);
                    return;
                }

                btn_detener.setEnabled(true);
                btn_parcial.setEnabled(true);

                btn_iniciar.setVisibility(View.GONE);
                btn_parcial.setVisibility(View.VISIBLE);
                btn_detener.setVisibility(View.VISIBLE);
                Cronome.setVisibility(View.VISIBLE);
                Parcial.setVisibility(View.VISIBLE);

                Parcial.append("                                  " + String.valueOf(Par) + "      " + String.valueOf(Cronome.getText() + "\n"));

                Par++;
                Scv.post(new Runnable() {
                    @Override
                    public void run() {
                        Scv.smoothScrollTo(0, Parcial.getBottom());
                    }
                });
            }
        });


    }

    public void updateTimerText(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Cronome.setText(time);
            }
        });
    }
}
