package io.scalaproject.androidminer;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.common.internal.Constants;

public class AutoStartService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        startActivity();
        return START_STICKY;

    }
    /*public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        Intent notificationIntent = new Intent(this, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, MobileMinerApplication.CHANNEL_ID)
                .setContentTitle("Auto Start Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager  mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel( MobileMinerApplication.CHANNEL_ID, MobileMinerApplication.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
            new NotificationCompat.Builder(this, MobileMinerApplication.CHANNEL_ID);
        }
        startForeground(1, notification);
        Utils.showToast(getApplicationContext(), "STARTED BOOT SERVICE MINER", Toast.LENGTH_SHORT, Tools.TOAST_YOFFSET_BOTTOM);

        return START_STICKY;
    }*/
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startActivity() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            String CHANNEL_ID = BuildConfig.APPLICATION_ID.concat("_notification_id");
            String CHANNEL_NAME = BuildConfig.APPLICATION_ID.concat("_notification_name");
            assert notificationManager != null;

            NotificationChannel mChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (mChannel == null) {
                mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

            builder.setSmallIcon(R.drawable.ic_warning)
                    .setContentTitle("xla")
                    .setContentText("xla")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_CALL)
                    .setFullScreenIntent(openScreen(20121212), true)
                    .setAutoCancel(true)
                    .setOngoing(true);

            Notification notification = builder.build();
            notificationManager.notify(20121212, notification);
        } else {
            startActivity(new Intent(this, SplashActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    private PendingIntent openScreen(int notificationId) {
        Intent fullScreenIntent = new Intent(this, SplashActivity.class);
        fullScreenIntent.putExtra("20121212", notificationId);
        return PendingIntent.getActivity(this, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}

