package com.flowplayer.demo.activity

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.trackPipAnimationHintView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.demo.R
import kotlinx.coroutines.launch

class FlowplayerSupportFragmentPiPActivity : AppCompatActivity() {
    private lateinit var flowplayer: Flowplayer
    private lateinit var playerFragment: FlowplayerSupportFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_support_fragment)

        playerFragment = FlowplayerSupportFragment.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.player_holder, playerFragment)
                .commitNow()

        flowplayer = playerFragment.getPlayer()
        flowplayer.setShouldPauseOnBackground(false)
        setupPiP()
        initializePlayer(intent)
    }

    /**
     * This is a SingleInstance activity, which is a good pattern to adopt when dealing with PiP
     * activities. Here we need to ensure that we properly process the new intents as they are
     * received by the Activity, for example when a new media is selected in the demo app while this
     * activity is in the background.
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        initializePlayer(intent)
    }

    private fun initializePlayer(intent: Intent) {
        PlayerHelper.initializePlayer(flowplayer, intent.extras)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        flowplayer.setMediaNotificationTapIntent(pendingIntent)
    }

    /**
     * PiP is properly supported only for API level 26 and above (Android 8.0). If the device runs
     * on an older version, nothing happens when the user tries to leave the app
     */
    override fun onUserLeaveHint() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val params = PictureInPictureParams.Builder().build()
            enterPictureInPictureMode(params)
        }
    }

    /**
     * [trackPipAnimationHintView] is a useful helper method that improves the enter/exit PiP
     * animation by providing the OS with the view that should be tracked during the transitions.
     *
     * [PictureInPictureParams.Builder.setAutoEnterEnabled] is available only for API 31 and above,
     * it makes the transition to PiP automatic without any additional code. You still have to add
     * code to [onUserLeaveHint] if you support older Android versions
     */
    private fun setupPiP() {
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lifecycleScope.launch { trackPipAnimationHintView(playerFragment.getPlayerView()) }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val params = PictureInPictureParams.Builder().setAutoEnterEnabled(true).build()
            setPictureInPictureParams(params)
        }
    }
}
