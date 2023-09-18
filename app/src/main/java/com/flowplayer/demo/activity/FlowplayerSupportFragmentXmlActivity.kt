package com.flowplayer.demo.activity

import android.app.PendingIntent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.demo.R

class FlowplayerSupportFragmentXmlActivity : AppCompatActivity() {
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
        PlayerHelper.initializePlayer(flowplayer, intent.extras)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        flowplayer.setMediaNotificationTapIntent(pendingIntent)
    }
}
