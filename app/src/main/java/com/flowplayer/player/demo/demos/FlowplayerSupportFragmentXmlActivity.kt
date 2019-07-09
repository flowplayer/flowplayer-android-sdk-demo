package com.flowplayer.player.demo.demos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.android.player.media.video.ExternalVideo
import com.flowplayer.android.player.media.video.FlowplayerVideo
import com.flowplayer.player.demo.Constants
import com.flowplayer.player.demo.R


class FlowplayerSupportFragmentXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_support_fragment)

        val playerFragment = supportFragmentManager.findFragmentById(R.id.player_fragment) as FlowplayerSupportFragment

        // Create Video object and start player
        val video = Utils.getVideo(intent.extras?.getString(Constants.EXTRA_MEDIA_TYPE))
        when(video){
            is FlowplayerVideo -> playerFragment.getPlayer().prepare(video, true)
            is ExternalVideo -> playerFragment.getPlayer().prepare(video, true)
        }
    }
}
