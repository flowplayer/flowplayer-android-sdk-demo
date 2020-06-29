package com.flowplayer.player.demo

import android.app.Application
import com.flowplayer.android.chromecast.ChromecastManager

class DemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ChromecastManager.initialize(this)
    }
}