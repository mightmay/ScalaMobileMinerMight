package io.scalaproject.androidminer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.drm.DrmStore.Playback.START
import android.os.Build
import android.support.v4.media.session.PlaybackStateCompat
import android.telephony.ServiceState


class StartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
    }
}