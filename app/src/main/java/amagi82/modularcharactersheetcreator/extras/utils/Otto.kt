package amagi82.modularcharactersheetcreator.extras.utils

import com.squareup.otto.Bus

enum class Otto {
    BUS;

    private val bus = Bus()

    fun get(): Bus {
        return bus
    }
}