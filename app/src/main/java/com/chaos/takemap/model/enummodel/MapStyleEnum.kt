package com.chaos.takemap.model.enummodel

import com.mapbox.mapboxsdk.maps.Style

enum class MapStyleEnum(var typt: Int, var style: Any) {
    MAPBOX_LIGHT(0, Style.LIGHT),
    MAPBOX_DARK(1, Style.DARK),
    MAPBOX_STREET(2, Style.MAPBOX_STREETS);

    companion object {
        inline fun <reified T> valueOfByType(type: Int): T? {
            values().forEach {
                if (it.typt == type) {
                    if (it.style is T) {
                        return it.style as T
                    }
                }
            }
            return null
        }
    }
}