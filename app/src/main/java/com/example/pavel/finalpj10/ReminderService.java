package com.example.pavel.finalpj10;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by sma on 20.02.2018.
 */

public class ReminderService extends IntentService {
    private static final  String TAG = "ReminderService";

    public ReminderService(){
        super(TAG);
    }
    public ReminderService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("Reminder","Создаём напоминаие");
        Intent clickNotifIntent = new Intent(this, MainActivity.class);
        PendingIntent pClickNotifIntent = PendingIntent.getActivity(this, 0, clickNotifIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this).
                setSmallIcon(R.drawable.ic_launcher).
                setContentTitle("Напоминание").
                setContentText("Content Text").
                setContentIntent(pClickNotifIntent);
        Notification notification = notifBuilder.build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);

        //новое уведомление на месте старого - главное сохранить значение первого
        //аргумента в методе notify, т.е 1 - это признак, по которому система ищет
        // существующее уведомление. Другой аргумент => новое уведомление

    }

    public static void setServiceAlarm(Context context, boolean onOffService) {
        Intent intent = new Intent(context, ReminderService.class);
        PendingIntent pIntent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        long hours1, minuts1, nowtime, nexttime, raznitsa;
        int defaultValue = 0;
        hours1 = intent.getLongExtra("hours", defaultValue);
        minuts1 = intent.getLongExtra("minuts", defaultValue);
        nowtime = intent.getLongExtra("nowtime", defaultValue);


            for (int i = 1; i<10000;i++) {
                nowtime = System.currentTimeMillis();
                nexttime = hours1 + minuts1;
                raznitsa = nexttime - nowtime;

                if (raznitsa < 3000) {
                    if (onOffService) {
                        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 0, pIntent);

                    } else {
                        alarmManager.cancel(pIntent);
                        pIntent.cancel();
                    }
                }

            }

    }

}