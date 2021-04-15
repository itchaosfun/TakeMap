package com.chaos.takemap.model.enummodel

import com.chaos.takemap.R
import com.chaos.takemap.config.ThemeConfig
import com.chaos.takemap.model.*

enum class ViewIconEnum(var type: Int, var lightIcon: Int, var darkIcon: Int) {
    ICON_HEADER_LIGHT(ICON_TYPE_HEADER, R.mipmap.light_header, R.mipmap.dark_header),

    ICON_LAYER_LIGHT(ICON_TYPE_LAYER, R.mipmap.light_layer, R.mipmap.dark_layer),

    ;

    companion object {
        fun valueOfByTheme(type: Int): Int {
            values().forEach {
                if (it.type == type) {
                    return if (ThemeConfig.theme == THEME_DARK) {
                        it.darkIcon
                    } else {
                        it.lightIcon
                    }
                }
            }
            return R.mipmap.default_error
        }
    }
}