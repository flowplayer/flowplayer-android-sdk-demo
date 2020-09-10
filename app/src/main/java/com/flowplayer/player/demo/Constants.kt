package com.flowplayer.player.demo

object Constants {
    const val EXTRA_MEDIA_TYPE = "MediaType"

    const val MEDIA_TYPE_FLOWPLAYER = "Flowplayer (mediaId + playerId)"
    const val MEDIA_TYPE_DASH = "DASH with VMAP"
    const val MEDIA_TYPE_SS = "SmoothStreaming with VAST waterfall"
    const val MEDIA_TYPE_HLS = "HLS"
    const val MEDIA_TYPE_MP4 = "MP4"

    const val DEMO_MEDIA_ID = "69891ec1-2d90-423c-9892-5a9ee87b6d86"
    const val DEMO_PLAYER_ID = "ac6dea7c-acf1-472a-bdab-efdd85f092f1"
    const val DEMO_URL_DASH = "https://edge.flowplayer.org/drive.mpd"
    const val DEMO_URL_SS = "https://playready.directtaps.net/smoothstreaming/SSWSS720H264/SuperSpeedway_720.ism"
    const val DEMO_URL_HLS = "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8"
    const val DEMO_URL_MP4 = "https://edge.flowplayer.org/drive.mp4"
    const val DEMO_URL_VMAP = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&cmsid=496&vid=short_onecue&correlator="
    const val DEMO_URL_AD_PREROLL = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&vad_type=linear&vpos=preroll&pod=1&ppos=1&lip=true&min_ad_duration=0&max_ad_duration=30000&vrid=6376&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0"
    const val DEMO_URL_AD_MIDROLL1 = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&cue=15000&vad_type=linear&vpos=midroll&pod=2&mridx=1&ppos=1&min_ad_duration=0&max_ad_duration=30000&vrid=6376&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0"
    const val DEMO_URL_AD_MIDROLL2 = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&cue=15000&vad_type=linear&vpos=midroll&pod=2&mridx=1&ppos=2&min_ad_duration=0&max_ad_duration=30000&vrid=6376&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0"
    const val DEMO_URL_AD_MIDROLL3 = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&cue=15000&vad_type=linear&vpos=midroll&pod=2&mridx=1&ppos=3&lip=true&min_ad_duration=0&max_ad_duration=30000&vrid=6376&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0"
    const val DEMO_URL_AD_POSTROLL = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&vad_type=linear&vpos=postroll&pod=3&ppos=1&lip=true&min_ad_duration=0&max_ad_duration=30000&vrid=6376&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0"
}