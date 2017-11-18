package com.omar.alarma;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by machi on 18/11/2017.
 */

public class TempoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager NM = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent repeating = new Intent(context, MainActivity.class);
        repeating.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent PI = PendingIntent.getActivity(context, 100, repeating, PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(PI)
                .setSmallIcon(R.drawable.ic_icon)
                .setContentTitle("Temporizador")
                .setContentText("Ha finalizado su tiempo")
                .setAutoCancel(true);

        NM.notify(100, builder.build());

        Toast.makeText(context, "¡¡Tiempo Culminado!!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }}
