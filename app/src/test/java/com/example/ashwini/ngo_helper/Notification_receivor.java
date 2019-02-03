package com.my.new2pma;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by ashwini on 1/3/2018.
 */

public class Notification_receivor extends BroadcastReceiver {
int id=0;
    @Override
    public void onReceive(Context context,Intent intent)
    {

        Calendar temp=Calendar.getInstance();
        int id =(int)temp.getTimeInMillis();
        String des=intent.getStringExtra("des");
        boolean notify=false;
        notify=intent.getBooleanExtra("notify",false);
        NotificationManager notificationManager= (NotificationManager)context.getSystemService((Context.NOTIFICATION_SERVICE));
        Intent repetattion =new Intent(context,first.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,id,repetattion,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
        .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("new pma")
                .setContentText(des);
         Random random= new Random();

        notificationManager.notify(id,builder.build());







if(notify) {
    MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
    mediaPlayer.start();

    Vibrator v = (Vibrator) context.getSystemService((context.VIBRATOR_SERVICE));
    v.vibrate(10000);
    Toast.makeText(context, "vibrator set successfully", Toast.LENGTH_SHORT).show();
}
    }


}
