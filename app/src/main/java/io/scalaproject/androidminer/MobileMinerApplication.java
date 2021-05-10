// Copyright (c) 2021 Scala
//
// Please see the included LICENSE file for more information.

package io.scalaproject.androidminer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraMailSender;

import static io.scalaproject.androidminer.MainActivity.contextOfApplication;
import static io.scalaproject.androidminer.MainActivity.getContextOfApplication;

@AcraCore(buildConfigClass = BuildConfig.class)
@AcraMailSender(mailTo = "hello@scalaproject.io")
public class MobileMinerApplication extends Application implements LifecycleObserver {

    public static final String CHANNEL_ID = "autoStartServiceChannel";
    public static final String CHANNEL_NAME = "Auto Start Service Channel";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);

        ACRA.init(this);

        SharedPreferences preferences = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
        Config.initialize(preferences);

        ACRA.getErrorReporter().setEnabled(Config.read(Config.CONFIG_SEND_DEBUG_INFO, "0").equals("1"));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        if(MainActivity.isDeviceMiningBackground())
            Utils.showToast(contextOfApplication, getResources().getString(R.string.miningbackground), Toast.LENGTH_SHORT);
    }
}