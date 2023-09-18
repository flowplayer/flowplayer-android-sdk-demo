package com.flowplayer.demo.activity

import android.app.PendingIntent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.android.player.event.*
import com.flowplayer.android.player.event.listener.*
import com.flowplayer.demo.R
import com.flowplayer.demo.test.FlowplayerIdlingResourceHolder
import com.flowplayer.demo.test.FlowplayerViewIdlingResource

class FlowplayerSupportFragmentActivity :
    AppCompatActivity(),
    OnPlayListener,
    OnPauseListener,
    OnIdleListener,
    OnCompleteListener,
    OnBufferListener,
    OnAdStartListener,
    OnAdSkipListener,
    OnAdResumeListener,
    OnAdPauseListener,
    OnAdCompleteListener,
    OnAdClickListener,
    OnFullscreenListener,
    OnMuteListener,
    OnVolumeListener,
    OnSpeedListener,
    FlowplayerIdlingResourceHolder {
    override lateinit var flowplayerViewIdlingResource: FlowplayerViewIdlingResource
    private lateinit var flowplayer: Flowplayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_support_fragment)

        val playerFragment = FlowplayerSupportFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.player_holder, playerFragment)
            .commitNow()

        flowplayer = playerFragment.getPlayer()
        flowplayer.addEventListener(this)
        PlayerHelper.initializePlayer(flowplayer, intent.extras)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        flowplayer.setMediaNotificationTapIntent(pendingIntent)

        flowplayer.setShouldPauseOnBackground(false)
        flowplayerViewIdlingResource = FlowplayerViewIdlingResource(playerFragment)
    }

    override fun onFullscreen(event: FullscreenEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnFullscreen: ${event.isFullscreen}")
    }

    override fun onMute(event: MuteEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnMute: muted ${event.isMuted}")
    }

    override fun onAdStart(event: AdStartEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdStart")
    }

    override fun onAdSkip(event: AdSkipEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdSkip")
    }

    override fun onAdResume(event: AdResumeEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdResume")
    }

    override fun onAdPause(event: AdPauseEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdPause")
    }

    override fun onAdComplete(event: AdCompleteEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdComplete")
    }

    override fun onAdClick(event: AdClickEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnAdClick")
    }

    override fun onPause(event: PauseEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnPause. Previous: ${event.previousState}")
    }

    override fun onPlay(event: PlayEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnPlay. Previous: ${event.previousState}")
    }

    override fun onBuffer(event: BufferEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnBuffer. Previous: ${event.previousState}")
    }

    override fun onComplete(event: CompleteEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnComplete. Previous: ${event.previousState}")
    }

    override fun onIdle(event: IdleEvent) {
        Log.d(
            FlowplayerSupportFragmentActivity::class.java.simpleName,
            "OnIdle. Previous: ${event.previousState}")
    }

    override fun onVolume(event: VolumeEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnVolume. ${event.volume}")
    }

    override fun onSpeed(event: SpeedEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnSpeed. ${event.speed}")
    }
}
