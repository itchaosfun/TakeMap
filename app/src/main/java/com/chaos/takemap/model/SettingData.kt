package com.chaos.takemap.model

class SettingData : ArrayList<SettingDataItem>()

data class SettingDataItem(
    val content: List<Content>?,
    val name: String,
    val type: Int
)

data class Content(
    val asset: String,
    val desc: String,
    var select:Int=0,
    val id: Int
)