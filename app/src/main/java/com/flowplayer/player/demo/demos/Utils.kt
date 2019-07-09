package com.flowplayer.player.demo.demos

import com.flowplayer.android.player.media.ad.AdBreak
import com.flowplayer.android.player.media.video.ExternalVideo
import com.flowplayer.android.player.media.video.FlowplayerVideo
import com.flowplayer.android.player.media.video.Video
import com.flowplayer.player.demo.Constants

object Utils {

    /**
     * Utility method for creating a Video based on the media type.
     */
    fun getVideo(mediaType: String?): Video? {
        var video: Video? = null

        when (mediaType) {
            Constants.MEDIA_TYPE_FLOWPLAYER -> video = FlowplayerVideo(Constants.DEMO_MEDIA_ID, Constants.DEMO_PLAYER_ID)
            Constants.MEDIA_TYPE_DASH -> video = ExternalVideo(Constants.DEMO_URL_DASH, Constants.DEMO_URL_VMAP)
            Constants.MEDIA_TYPE_HLS -> video = ExternalVideo(Constants.DEMO_URL_HLS)
            Constants.MEDIA_TYPE_MP4 -> video = ExternalVideo(Constants.DEMO_URL_MP4)
            Constants.MEDIA_TYPE_SS -> {
                val preRollBreak = AdBreak(Constants.DEMO_URL_AD_PREROLL, AdBreak.Roll.PRE)
                val midRollBreak = AdBreak(
                    arrayListOf(
                        Constants.DEMO_URL_AD_MIDROLL1,
                        Constants.DEMO_URL_AD_MIDROLL2,
                        Constants.DEMO_URL_AD_MIDROLL3
                    ), 15
                )
                val postRollBreak = AdBreak(Constants.DEMO_URL_AD_POSTROLL, AdBreak.Roll.POST)

                video = ExternalVideo(Constants.DEMO_URL_SS, arrayListOf(preRollBreak, midRollBreak, postRollBreak))
            }
        }

        return video
    }
}