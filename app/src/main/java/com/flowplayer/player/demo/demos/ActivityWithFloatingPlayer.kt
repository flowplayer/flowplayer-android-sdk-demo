package com.flowplayer.player.demo.demos

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.flowplayer.android.player.FlowplayerView
import com.flowplayer.android.player.control.PlayerControlConfig
import com.flowplayer.android.player.event.PlayEvent
import com.flowplayer.android.player.event.listener.OnPlayListener
import com.flowplayer.android.player.media.ExternalMedia
import com.flowplayer.player.demo.Constants
import com.flowplayer.player.demo.R
import com.flowplayer.player.demo.service.FloatingPlayerService

class ActivityWithFloatingPlayer : AppCompatActivity() {
    lateinit var player: FlowplayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_with_floating_view)

        player = findViewById(R.id.player)
        initializePlayer(player)

        findViewById<Button>(R.id.float_button).setOnClickListener {
            bindService(Intent(this, FloatingPlayerService::class.java), connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.onDestroy()
        removeFloatingPlayer()
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service is FloatingPlayerService.ServiceBinder) {
                val miniPlayer= service.getPlayer()
                initializePlayer(miniPlayer)
                player.pause()

                miniPlayer.addEventListener(object : OnPlayListener{
                    override fun onPlay(event: PlayEvent) {
                        miniPlayer.seek(player.getCurrentPosition())
                        miniPlayer.removeEventListener(this)
                    }

                })

                service.getCloseButton().setOnClickListener {view ->
                    view.setOnClickListener(null)
                    removeFloatingPlayer()
                }
            }
        }
    }

    fun removeFloatingPlayer() {
       unbindService(connection)
    }

    fun initializePlayer(player: FlowplayerView) {
        val config = PlayerControlConfig.Builder()
                .setMuteControl(true)
                .enablePlugins(arrayOf("speed", "subtitles", "qsel", "asel", "analytics"))
                .setCustom("speed.options", arrayOf(0.5, 1, 2, 5))
                .setCustom("speed.labels", arrayOf("Slow", "Normal", "Double", "Fast"))
                .build()

        player.setControlConfig(config)
        player.prepare(ExternalMedia(Constants.DEMO_URL_HLS), true)
    }
}