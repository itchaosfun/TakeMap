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
    val id: Int
)