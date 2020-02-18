package com.flowplayer.player.demo.demos

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerView
import com.flowplayer.android.player.lifecycle.FlowplayerLifecycleObserver
import com.flowplayer.android.player.media.ExternalMedia
import com.flowplayer.android.player.media.FlowplayerMedia
import com.flowplayer.player.demo.Constants
import com.flowplayer.player.demo.R


class FlowplayerViewAppCompatActivity : AppCompatActivity() {

    private lateinit var flowplayerView: FlowplayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_view)

        flowplayerView = findViewById(R.id.player_view)

        // Create Video object and start player
        val video = Utils.getVideo(intent.extras?.getString(Constants.EXTRA_MEDIA_TYPE))
        when(video){
            is FlowplayerMedia -> flowplayerView.prepare(video, true)
            is ExternalMedia -> flowplayerView.prepare(video, true)
        }

        // Register the lifecycle so that the player is aware of it.
        FlowplayerLifecycleObserver.registerLifecycle(lifecycle)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            flowplayerView.setFullscreen(true)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            flowplayerView.setFullscreen(false)
        }
    }
}
