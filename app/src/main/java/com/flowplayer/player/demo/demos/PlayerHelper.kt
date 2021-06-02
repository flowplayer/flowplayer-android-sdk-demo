package com.flowplayer.player.demo.demos

import android.os.Bundle
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.control.PlayerControlConfig
import com.flowplayer.android.player.media.ExternalMedia
import com.flowplayer.android.player.media.FlowplayerMedia
import com.flowplayer.android.player.media.Subtitle
import com.flowplayer.android.player.media.ad.AdBreak
import com.flowplayer.player.demo.Constants


object PlayerHelper {
    fun initializePlayer(player: Flowplayer, intentExtras: Bundle?) {

        val config = PlayerControlConfig.Builder()
                .setMuteControl(true)
                .enablePlugins(arrayOf("speed", "subtitles", "qsel", "asel", "analytics"))
                .setControlUrl("https://tv2play.staging.greenformatics.hu/assets/player/index.html?token=[TOKEN]&[CONFIG]")
                .setCustom("speed.options", arrayOf(0.5, 1, 2, 5))
                .setCustom("speed.labels", arrayOf("Slow", "Normal", "Double", "Fast"))
                .build()

        player.setControlConfig(config)

        val mediaType = intentExtras?.getString(Constants.EXTRA_MEDIA_TYPE)
        when (mediaType) {
            Constants.MEDIA_TYPE_FLOWPLAYER -> {
                player.prepare(FlowplayerMedia(Constants.DEMO_MEDIA_ID, Constants.DEMO_PLAYER_ID), true)
            }
            Constants.MEDIA_TYPE_DASH -> {
                player.prepare(ExternalMedia(Constants.DEMO_URL_DASH, Constants.DEMO_URL_VMAP), true)
            }
            Constants.MEDIA_TYPE_SS -> {
                val preRoll = AdBreak(Constants.DEMO_URL_AD_PREROLL, AdBreak.Roll.PRE)
                val midRoll1 = AdBreak(arrayListOf(Constants.DEMO_URL_AD_MIDROLL1, Constants.DEMO_URL_AD_MIDROLL2, Constants.DEMO_URL_AD_MIDROLL3), 15)
                val postRoll = AdBreak(Constants.DEMO_URL_AD_POSTROLL, AdBreak.Roll.POST)

                player.prepare(ExternalMedia(Constants.DEMO_URL_SS, arrayListOf(preRoll, midRoll1, postRoll)), true)
            }
            Constants.MEDIA_TYPE_HLS -> {
                player.prepare(ExternalMedia(Constants.DEMO_URL_HLS), true)
            }
            Constants.MEDIA_TYPE_MP4 -> {
                player.prepare(ExternalMedia(Constants.DEMO_URL_MP4, subtitles = arrayListOf(Subtitle("English", "http://www.storiesinflight.com/js_videosub/jellies.srt"))), true)
            }
        }
    }
}