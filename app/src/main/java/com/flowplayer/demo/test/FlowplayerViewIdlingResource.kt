package com.flowplayer.demo.test

import android.util.Log
import androidx.test.espresso.IdlingResource
import com.flowplayer.android.player.Flowplayer
import com.flowplayer.android.player.FlowplayerSupportFragment
import com.flowplayer.android.player.FlowplayerView
import com.flowplayer.android.player.event.*
import com.flowplayer.android.player.event.listener.*

/**
 * Custom IdlingResource that monitors the state of the underlying [FlowplayerView], and allows
 * tests to proceed only when the player is not buffering or idle.
 */
class FlowplayerViewIdlingResource private constructor() :
    IdlingResource,
    EventListener,
    OnIdleListener,
    OnBufferListener,
    OnPlayListener,
    OnPauseListener,
    OnCompleteListener {
    private var flowplayerSupportFragment: FlowplayerSupportFragment? = null
    private var flowplayerView: FlowplayerView? = null
    private var callback: IdlingResource.ResourceCallback? = null
    private var currentState: Flowplayer.State = Flowplayer.State.IDLE

    constructor(flowplayerView: FlowplayerView) : this() {
        this.flowplayerView = flowplayerView
        this.flowplayerView?.flowplayer?.addEventListener(this)
    }

    constructor(flowplayerSupportFragment: FlowplayerSupportFragment) : this() {
        this.flowplayerSupportFragment = flowplayerSupportFragment
        this.flowplayerSupportFragment?.getPlayer()?.addEventListener(this)
    }

    override fun getName(): String {
        return "Flowplayer"
    }

    override fun isIdleNow(): Boolean {
        return flowplayerView?.flowplayer?.let {
            it.getPlaybackState() > Flowplayer.State.BUFFERING
        }
            ?: flowplayerSupportFragment?.getPlayer()?.let {
                it.getPlaybackState() > Flowplayer.State.BUFFERING
            }
                ?: run {
                Log.e(
                    FlowplayerViewIdlingResource::class.java.simpleName,
                    "No FlowplayerView or FlowplayerSupportFragment, this Idling resource is now stuck")
                false
            }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    override fun onIdle(event: IdleEvent) {
        currentState = Flowplayer.State.IDLE
    }

    override fun onBuffer(event: BufferEvent) {
        currentState = Flowplayer.State.BUFFERING
    }

    override fun onPlay(event: PlayEvent) {
        callback?.onTransitionToIdle()
        currentState = Flowplayer.State.PLAYING
    }

    override fun onPause(event: PauseEvent) {
        callback?.onTransitionToIdle()
        currentState = Flowplayer.State.PAUSED
    }

    override fun onComplete(event: CompleteEvent) {
        callback?.onTransitionToIdle()
        currentState = Flowplayer.State.COMPLETED
    }
}
