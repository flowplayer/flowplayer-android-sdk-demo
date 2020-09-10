package com.flowplayer.player.demo.demos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.player.demo.R


class FlowplayerSupportFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowplayer_holder)

        val playerFragment = FlowplayerSupportFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.player_holder, playerFragment)
                .commit()

        // When adding the fragment programmatically, we have to execute all pending transactions if we want to immediately get the instance of the PlayerView.
        supportFragmentManager.executePendingTransactions()

        PlayerHelper.initializePlayer(playerFragment.getPlayer(), intent.extras)
    }
}
