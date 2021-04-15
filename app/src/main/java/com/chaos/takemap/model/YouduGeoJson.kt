package com.chaos.takemap.model

data class YouduGeoJson(
    val features: List<Feature>,
    val type: String
):BaseGeoJson()

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)

data class Geometry(
    val coordinates: List<List<Double>>,
    val type: String
)

data class Properties(
    val Id: Int,
    val desc: String
)
