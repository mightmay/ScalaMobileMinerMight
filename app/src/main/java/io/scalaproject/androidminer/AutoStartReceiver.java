package io.scalaproject.androidminer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import androidx.core.content.ContextCompat;




public class AutoStartReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context, AutoStartService.class);
        ContextCompat.startForegroundService(context, intentService);

        /*Intent i = new Intent(context, SplashActivity.class);
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        ContextCompat.startForegroundService(context, i);*/

    }
}

