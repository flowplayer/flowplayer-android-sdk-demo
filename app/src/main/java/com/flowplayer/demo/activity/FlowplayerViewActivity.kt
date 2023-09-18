package com.flowplayer.demo.activity

import android.app.Activity
import android.app.PendingIntent
import android.content.res.Configuration
import android.os.Bundle
import com.flowplayer.android.player.FlowplayerView
import com.flowplayer.demo.R

class FlowplayerViewActivity : Activity() {
    private lateinit var flowplayerView: FlowplayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_view)

        flowplayerView = findViewById(R.id.player_view)
        PlayerHelper.initializePlayer(flowplayerView.flowplayer, intent.extras)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        flowplayerView.flowplayer.setMediaNotificationTapIntent(pendingIntent)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            flowplayerView.flowplayer.setFullscreen(true)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            flowplayerView.flowplayer.setFullscreen(false)
        }
    }

    override fun onStart() {
        super.onStart()
        flowplayerView.onStart()
    }

    override fun onResume() {
        super.onResume()
        flowplayerView.onResume()
    }

    override fun onPause() {
        flowplayerView.onPause()
        super.onPause()
    }

    override fun onStop() {
        flowplayerView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        flowplayerView.onDestroy()
        super.onDestroy()
    }
}
