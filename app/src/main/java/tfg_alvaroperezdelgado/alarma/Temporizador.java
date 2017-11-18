package tfg_alvaroperezdelgado.alarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Temporizador extends AppCompatActivity {

    EditText Num, Sec;
    Button Inicia, Cancela;
    ToggleButton TPauRen;
    TextView Segundos, Txt;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    private boolean Pausa;
    private boolean Cancelar;
    private long LRenaudar = 0;
    private int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);


        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        Inicia = (Button) findViewById(R.id.Inicio);
        Cancela = (Button) findViewById(R.id.Cancelar);
        TPauRen = (ToggleButton) findViewById(R.id.toggleButton);
        Segundos = (TextView) findViewById(R.id.Numeros);
        Num = (EditText) findViewById(R.id.editText1);
        Sec = (EditText) findViewById(R.id.editText2);
        Txt = (TextView) findViewById(R.id.txt);


        Inicia.setVisibility(View.VISIBLE);
        TPauRen.setVisibility(View.GONE);
        Cancela.setVisibility(View.GONE);
        Inicia.setEnabled(true);
        TPauRen.setEnabled(false);
        Cancela.setEnabled(false);

        Intent intent = new Intent(this, TempoReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);



        Inicia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Inicia.setVisibility(View.GONE);
                TPauRen.setVisibility(View.VISIBLE);
                Cancela.setVisibility(View.VISIBLE);
                Inicia.setEnabled(false);
                TPauRen.setEnabled(true);
                Cancela.setEnabled(true);


                Pausa = false;
                Cancelar = false;

                final String numT = Num.getText().toString();
                final String secT = Sec.getText().toString();


                final long total = ((Integer.valueOf(numT)*60) + Integer.valueOf(secT));

                //final String num = Segundos.getText().toString();

                final long milisfuturo = total * 1000;
                long CountDown = 1000;

                CountDownTimer countDownTimer = new CountDownTimer(milisfuturo, CountDown) {
                    @Override
                    public void onTick(long milisTerminar) {
                        if (Pausa || Cancelar) {
                            cancel();
                        } else {
                            Segundos.setText(milisTerminar / 1000 + " segundos");
                            LRenaudar = milisTerminar;

                            Segundos.setEnabled(false);
                            Num.setEnabled(false);
                            Sec.setEnabled(false);
                            Txt.setText(numT + " Minutos" + "\n" + secT + " Segundos");

                        }


                    }

                    @Override
                    public void onFinish () {



                        Segundos.setText("");
                        Toast.makeText(Temporizador.this, "Tiempo finalizado", Toast.LENGTH_SHORT).show();
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, LRenaudar, 10000, pendingIntent);
                        Cancelar = true;
                        Inicia.setVisibility(View.VISIBLE);
                        TPauRen.setVisibility(View.GONE);
                        Cancela.setVisibility(View.GONE);
                        Segundos.setText("");
                        Txt.setText("");
                        Num.setEnabled(true);
                        Sec.setEnabled(true);
                        Num.setText("");
                        Sec.setText("");
                        Segundos.setEnabled(true);

                        Inicia.setEnabled(true);
                        TPauRen.setEnabled(false);
                        Cancela.setEnabled(false);

                    }
                }.start();

            }
        });

        TPauRen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TPauRen.isChecked()) {
                    Pausa = true;
                } else {
                    Pausa = false;
                    Inicia.setVisibility(View.GONE);
                    TPauRen.setVisibility(View.VISIBLE);
                    Cancela.setVisibility(View.VISIBLE);

                    String num = Segundos.getText().toString();

                    final long milisfuturo = LRenaudar;
                    long CountDown = 1000;


                    CountDownTimer countDownTimer = new CountDownTimer(milisfuturo , CountDown) {
                        @Override
                        public void onTick(long milisTerminar) {
                            Segundos.setText( milisTerminar/1000 + " segundos");
                            LRenaudar = milisTerminar;
                            Segundos.setEnabled(false);

                        }

                        @Override
                        public void onFinish() {
                            //Txt.setText("Ha terminado el tiempo");
                            Segundos.setText("");
                            Toast.makeText(Temporizador.this, "Tiempo finalizado", Toast.LENGTH_SHORT).show();
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, LRenaudar, 10000, pendingIntent);
                            Cancelar = true;
                            Inicia.setVisibility(View.VISIBLE);
                            TPauRen.setVisibility(View.GONE);
                            Cancela.setVisibility(View.GONE);
                            Segundos.setText("");
                            Txt.setText("");
                            Num.setEnabled(true);
                            Sec.setEnabled(true);
                            Num.setText("");
                            Sec.setText("");
                            Segundos.setEnabled(true);

                            Inicia.setEnabled(true);
                            TPauRen.setEnabled(false);
                            Cancela.setEnabled(false);

                        }
                    }.start();

                }
            }
        });

        Cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cancelar = true;
                Inicia.setVisibility(View.VISIBLE);
                TPauRen.setVisibility(View.GONE);
                Cancela.setVisibility(View.GONE);
                Segundos.setText("");
                Txt.setText("");
                Num.setEnabled(true);
                Sec.setEnabled(true);
                Num.setText("");
                Sec.setText("");
                Segundos.setEnabled(true);

                Inicia.setEnabled(true);
                TPauRen.setEnabled(false);
                Cancela.setEnabled(false);

                alarmManager.cancel(pendingIntent);
                Toast.makeText(Temporizador.this, "Tiempo Cancelado", Toast.LENGTH_SHORT).show();

            }

        });
    }

}
