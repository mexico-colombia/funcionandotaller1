package tfg_alvaroperezdelgado.alarma;

import android.content.Context;

/**
 * Created by machi on 18/11/2017.
 */

public class Crono implements Runnable{

    public static final long MINUTOS = 60000;
    public static final long HORAS = 3600000;

    private Context mtContext;
    private long mtIniciarTiempo;
    private boolean mtEmpezar;

    public Crono(Context mtContext) {
        this.mtContext = mtContext;
    }

    public void start(){
        mtIniciarTiempo= System.currentTimeMillis();
        mtEmpezar = true;
    }

    public void stop(){
        mtEmpezar = false;
    }

    @Override
    public void run() {

        while(mtEmpezar){

            long Desde = System.currentTimeMillis()- mtIniciarTiempo;

            int segundos = (int) ((Desde / 1000)% 60);
            int minutos = (int) ((Desde / MINUTOS)% 60);
            int horas = (int) ((Desde / HORAS)% 24);
            int milisegundos = (int) Desde % 1000;

            ((Cronometro)mtContext).updateTimerText(String.format(
                    "%02d:%02d:%02d:%02d", horas, minutos, segundos, milisegundos  )
            );
        }
    }
}