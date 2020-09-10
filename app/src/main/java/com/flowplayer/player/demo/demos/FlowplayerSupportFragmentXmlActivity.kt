package com.flowplayer.player.demo.demos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.player.demo.R


class FlowplayerSupportFragmentXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_support_fragment)

        val playerFragment = supportFragmentManager.findFragmentById(R.id.player_fragment) as FlowplayerSupportFragment
        PlayerHelper.initializePlayer(playerFragment.getPlayer(), intent.extras)
    }
}
