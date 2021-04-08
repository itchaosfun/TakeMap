package com.chaos.takemap.data

import com.chaos.takemap.R

enum class ViewIconEnum(var theme:Int,var type:Int,var icon:Int) {
    ICON_HEADER_LIGHT(THEME_LIGHT, ICON_TYPE_HEADER,R.mipmap.light_header),
    ICON_HEADER_DARK(THEME_DARK,ICON_TYPE_HEADER, R.mipmap.dark_header),
    ICON_HEADER_STREET(THEME_STREET,ICON_TYPE_HEADER, R.mipmap.light_header),

    ICON_LAYER_LIGHT(THEME_LIGHT, ICON_TYPE_LAYER, R.mipmap.light_layer),
    ICON_LAYER_DARK(THEME_DARK, ICON_TYPE_LAYER, R.mipmap.dark_layer),
    ICON_LAYER_STREET(THEME_STREET,ICON_TYPE_LAYER,  R.mipmap.light_layer),

    ;

    companion object{
        fun valueOfByTheme(theme: Int,type: Int):Int?{
            values().forEach {
                if (it.theme == theme && it.type == type){
                    return it.icon
                }
            }
            return null
        }
    }
}