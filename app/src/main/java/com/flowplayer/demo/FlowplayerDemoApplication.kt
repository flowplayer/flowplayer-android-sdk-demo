package com.flowplayer.demo

import android.app.Application
import android.webkit.WebView

class FlowplayerDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
    }
}
