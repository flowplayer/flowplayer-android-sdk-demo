package com.flowplayer.player.demo.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Binder
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.flowplayer.android.player.FlowplayerView
import com.flowplayer.player.demo.R

class FloatingPlayerService : Service() {
    private lateinit var floatingView: View
    private lateinit var manager: WindowManager
    private lateinit var player: FlowplayerView
    private lateinit var closeButton: Button

    override fun onDestroy() {
        super.onDestroy()
        player.onDestroy()
        manager.removeView(floatingView)
    }

    override fun onBind(intent: Intent?): IBinder {
        return ServiceBinder()
    }

    inner class ServiceBinder : Binder() {
        fun getPlayer() = player
        fun getCloseButton() = closeButton
    }

    override fun onCreate() {
        super.onCreate()
        createFloatingView()
    }

    private fun createFloatingView() {
        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_view_layout, null)

        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.BOTTOM or Gravity.LEFT


        manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        manager.addView(floatingView, params)

        player = floatingView.findViewById(R.id.mini_player)
        player.setUseControls(false)

        closeButton = floatingView.findViewById(R.id.close_button)
    }
}