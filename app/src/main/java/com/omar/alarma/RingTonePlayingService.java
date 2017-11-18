package com.omar.alarma;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import Model.Container;

/**
 * Esta clase se ocupa de reproducir el sonido de la alarma
 */
public class RingTonePlayingService extends Service {

    private int NOTIFICATION_ID=1;
    //mediaPlayer sirve para reproducir canciones
    private MediaPlayer mediaPlayer;
    //start_id controla que boton esta pulsado
    private int start_id;
    //isRunning controla si esta funcionando esta clase
    private boolean isRunning;
    //container contiene lo que queremos leer
    Container container;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int start_id) {
        Log.e("RingtonePlayingService", ", OnStartCommand");
        Log.i("LocalService", "Received start id " + start_id + ": " + intent);

        //fetch the extra string from the intent
        String state=intent.getExtras().getString("extra");


        Log.e("Ringtone state:extra is",state);





        //comprobar que state no tenga null
        assert state !=null;

        //change the id in function extra string
        switch (state) {
            case "alarm on":
                start_id = 1;
                break;
            case "alarm off":
                start_id = 0;
                break;
            default:
                start_id = 0;
                break;
        }


        //if there is no music playing an the user pressed "alarm on"
        //music should start playing
        if(!this.isRunning&& start_id==1){
            //obtenemos una instancia de container
            container=Container.getInstance();
            Log.e("There is no music, ", "and you want start");

            //si hay algo en el song, o es distinto de nulo que lea esa cancion.
            if(!(container.getSong()==null)) {
                //creamos una uri a traves de nuestro archivo File cancion para pasarselo al mediaPlayer
                Uri u = Uri.parse(container.getSong().toString());

                //creamos el media player pasandole el contexto y el audio que queremos escuchar
                mediaPlayer = MediaPlayer.create(this, u);
            }//sino que ponga una cancion por defecto
            else {
                //cancion por defecto
                mediaPlayer = MediaPlayer.create(this, R.raw.dove);
            }

            //Start the ringtone
            mediaPlayer.start();
            //Como iniciar una actividad desde aqui
            //startActivity(new Intent(RingTonePlayingService.this, AddMessageUser.class));

            this.isRunning=true;
            this.start_id=0;




            //TODO poner icono y contenido de los mensajes y mirar lo del build y que se muestre bajando y tambien mirar lo del api
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                //notification
                //set up the notification service
                NotificationManager notificationManager=(NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                //Hacemos un intent que nos lleve a la clase Speech
                Intent intent2=new Intent(this.getApplicationContext(),addAlarm.class);

                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntentMainActivity=PendingIntent.getActivity(this,0,intent2,0);


                //construimos la notificacion
                Notification notificationPopup= null;
                //make the notification parameters
                notificationPopup = new Notification.Builder(this)
                        .setContentTitle("Alarma!!")
                        .setContentText("alarma")
                        .setContentIntent(pendingIntentMainActivity)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();


            //set up the notification start command
            notificationManager.notify(0,notificationPopup);


            }else{//si esta por debajo de jellybean ejecutara esta notficacion
                //Hacemos un intent que nos lleve a la clase Speech
                Intent intent2= new Intent(new Intent(this.getApplicationContext(), addAlarm.class));

                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntent=PendingIntent.getActivity(this.getApplicationContext(),0,intent2,0);


                //construimos la notificacion
                NotificationCompat.Builder builder=new NotificationCompat.Builder(RingTonePlayingService.this);
                builder.setSmallIcon(R.drawable.ic_icon);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon));
                builder.setContentTitle("Titulo notificacion");
                builder.setContentText("Contenido hola mundo");
                builder.setSubText("Subtexto eyy que paso");

                //Enviar la notificacion
                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID,builder.build());
            }

        }
        //if there is music playing, and the usser pressed "alarm off"
        //music should stop playing
        else if(this.isRunning&&start_id==0){
            Log.e("There is music, ","and you want end");

            //Stop the ringtone
            mediaPlayer.stop();

            mediaPlayer.reset();


            this.isRunning=false;
            this.start_id=0;


        }
        //music is not playing and press "alarm off"
        // you prove the alarm
        //do nothing
        else if(!this.isRunning&&start_id==0){
            Log.e("There is no music, ","and you want end");

            this.isRunning=false;
            this.start_id=0;
        }
        //if the music is playing and the user pressed "alarm on"
        //do nothing
        else if(this.isRunning&&start_id==1){
            Log.e("There is music, ","and you want start");

            this.isRunning=true;
            this.start_id=1;
        }
        //cant think of anything else, just to catch the odd event
        else {
            Log.e("Else  ","somehow");
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("RingTonePlayingService",", onDestroy");

        super.onDestroy();
        this.isRunning=false;
    }




}
