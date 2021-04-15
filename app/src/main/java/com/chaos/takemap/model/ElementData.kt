package com.chaos.takemap.model

class ElementData : ArrayList<ElementDataItem>()

data class ElementDataItem(
    val name: String,
    val type: Int
)
