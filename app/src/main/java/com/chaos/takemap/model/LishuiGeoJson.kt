package com.chaos.takemap.model

data class LishuiGeoJson(
    val crs: Crs,
    val features: List<LishuiFeature>,
    val type: String
) : BaseGeoJson()


data class Crs(
    val properties: LishuiProperties,
    val type: String
)

data class LishuiFeature(
    val geometry: LishuiGeometry,
    val properties: PropertiesX,
    val type: String
)

data class LishuiProperties(
    val name: String
)

data class LishuiGeometry(
    val coordinates: List<List<Any>>,
    val type: String
)

data class PropertiesX(
    val OBJECTID: Int,
    val Shape_Area: Double,
    val Shape_Leng: Double,
    val type: Any
)