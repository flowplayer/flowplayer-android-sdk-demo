package com.flowplayer.demo.test

/**
 * Identifies a class that holds a reference to a [FlowplayerViewIdlingResource]. Used by testing
 * helpers to register the resource with Espresso's internal registry.
 *
 * [flowplayerViewIdlingResource] is nullable because it should be initialised only for debug
 * builds, while in production it should be null
 */
interface FlowplayerIdlingResourceHolder {
    val flowplayerViewIdlingResource: FlowplayerViewIdlingResource?
}
