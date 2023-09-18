package com.flowplayer.demo.activity

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.demo.R

class FlowplayerSupportFragmentPiPActivity : AppCompatActivity() {
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
        flowplayer.setShouldPauseOnBackground(false)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        flowplayer.setMediaNotificationTapIntent(pendingIntent)
    }

    override fun onUserLeaveHint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val params = PictureInPictureParams.Builder().build()
            enterPictureInPictureMode(params)
        } else {
            Toast.makeText(this, "PiP is supported only for API 26 and above", Toast.LENGTH_LONG)
                .show()
        }

        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.show()
    }
}
