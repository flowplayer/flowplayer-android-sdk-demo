package com.flowplayer.player.demo.demos

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.chromecast.ChromecastManager
import com.flowplayer.android.chromecast.player.ChromecastPlayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.player.demo.R


class FlowplayerChromecastFragmentActivity : AppCompatActivity(){

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


        // Initialize Chromecast player
        val chromecastMediaPlayer = ChromecastPlayer.getInstance(this)
        //You can also use Mini and Expanded Controllers, of google's Cast UX Widgets, for controlling playback, when media is casted, instead of a controlView.
        //Check subsection "Control playback using Mini and Expanded Controllers" of developer's guide for more details.
        chromecastMediaPlayer.setControlView(findViewById(R.id.cast_control_view))

        // Pass the chromecast player to the player
        playerFragment.getPlayer().setChromecastMediaPlayer(chromecastMediaPlayer)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_player, menu)
        ChromecastManager.getInstance().setMediaRouteButton(this, menu, R.id.media_route_menu_item)

        return true
    }
}
