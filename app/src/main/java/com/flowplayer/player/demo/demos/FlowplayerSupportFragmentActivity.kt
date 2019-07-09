package com.flowplayer.player.demo.demos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.android.player.event.*
import com.flowplayer.android.player.event.listener.*
import com.flowplayer.android.player.media.video.ExternalVideo
import com.flowplayer.android.player.media.video.FlowplayerVideo
import com.flowplayer.player.demo.Constants
import com.flowplayer.player.demo.R


class FlowplayerSupportFragmentActivity : AppCompatActivity(), OnPlayListener, OnPauseListener, OnIdleListener, OnCompleteListener, OnBufferListener,
        OnAdStartListener, OnAdSkipListener, OnAdResumeListener, OnAdPauseListener, OnAdCompleteListener, OnAdClickListener, OnFullscreenListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_holder)

        val playerFragment = FlowplayerSupportFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.player_holder, playerFragment)
                .commit()

        // When adding the fragment programmatically, we have to execute all pending transactions if we want to immediately get the instance of the PlayerView.
        supportFragmentManager.executePendingTransactions()

        playerFragment.getPlayer().addEventListener(this)

        // Create Video object and start player
        val video = Utils.getVideo(intent.extras?.getString(Constants.EXTRA_MEDIA_TYPE))
        when(video){
            is FlowplayerVideo -> playerFragment.getPlayer().prepare(video, true)
            is ExternalVideo -> playerFragment.getPlayer().prepare(video, true)
        }
    }

    override fun onFullscreen(event: FullscreenEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnFullscreen: ${event.isFullscreen}")
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
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnPause. Previous: ${event.previousState}}")
    }

    override fun onPlay(event: PlayEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnPlay. Previous: ${event.previousState}}")
    }

    override fun onBuffer(event: BufferEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnBuffer. Previous: ${event.previousState}}")
    }

    override fun onComplete(event: CompleteEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnComplete. Previous: ${event.previousState}}")
    }

    override fun onIdle(event: IdleEvent) {
        Log.d(FlowplayerSupportFragmentActivity::class.java.simpleName, "OnIdle. Previous: ${event.previousState}}")
    }
}
