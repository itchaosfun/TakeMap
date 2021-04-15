package com.chaos.takemap.ui.helper

import android.content.Context
import android.graphics.Color
import com.chaos.takemap.model.*
import com.chaos.takemap.model.enummodel.DefaultLocationEnum
import com.chaos.takemap.util.FileUtils
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource

class MapBoxHelper(
    private var mapView: MapView,
    private var context: Context,
    private var mapboxMap: MapboxMap,
    private var style: Style
) {
    /**
     * 相机视角位置
     */
    fun updateCameraPosition(latLng: LatLng, zoom: Double = 18.0, tilt: Double = 0.0, bearing: Double = 0.0) {
        try {
            val position = CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom)
                .tilt(tilt)
                .bearing(bearing)
                .build()
            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 100)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * uiSetting
     */
    fun uiSetting() {
        val uiSettings = mapboxMap.uiSettings
        uiSettings.isLogoEnabled = false
        uiSettings.isAttributionEnabled = false
    }

    /**
     * 加载地图
     */
    fun loadMap(type: Int, content: Content) {
        when (type) {
            MAP_TYPE_GEO -> {
                loadGeoMap(content)
            }
            MAP_TYPE_MAP -> {
                loadMapMap(content)
            }
        }
        val defaultLocation = DefaultLocationEnum.valueOfLocationByType(content.desc)
        updateCameraPosition(LatLng(defaultLocation.defaultLat,defaultLocation.defaultLon))
    }

    /**
     * 加载MAP协议地图
     */
    private fun loadMapMap(content: Content) {

    }

    /**
     * 加载geojson地图
     */
    private fun loadGeoMap(content: Content) {
        val geoJson =
            FileUtils.getAssertAsData<YouduGeoJson>(context, content.asset)
        setUpGeoJsonLayer(geoJson)
    }

    /**
     * 设置geojson地图
     */
    private fun setUpGeoJsonLayer(baseGeoJson: BaseGeoJson) {
        if (baseGeoJson is LishuiGeoJson) {
            setUpLishuiGeoLayer(baseGeoJson)
        } else if (baseGeoJson is YouduGeoJson) {
            setUpYouduGeoLayer(baseGeoJson)
        }
    }

    /**
     * 由度空间的geojson
     */
    private fun setUpYouduGeoLayer(youduGeoJson: YouduGeoJson) {
        val features = mutableListOf<Feature>()
        youduGeoJson.features.forEach { feature ->
            val coordinates = feature.geometry.coordinates
            val routeCoordinates = mutableListOf<Point>()

            coordinates.forEach { coordinate ->
                routeCoordinates.add(
                    Point.fromLngLat(coordinate[0], coordinate[1])
                )
            }
            val lineString = LineString.fromLngLats(routeCoordinates)
            features.add(Feature.fromGeometry(lineString))
        }

        val featureCollection =
            FeatureCollection.fromFeatures(features)

        val geoJsonSource = GeoJsonSource("youdu-geojson", featureCollection)
        style.addSource(geoJsonSource)

        style.addLayer(
            LineLayer("youdu-geojson", "youdu-geojson").withProperties(
                PropertyFactory.lineDasharray(arrayOf<Float>(0.01f, 0.01f)),
                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                PropertyFactory.lineWidth(2f),
                PropertyFactory.lineColor(Color.parseColor("#e55e5e"))
            )
        )
    }

    /**
     * 溧水的geojson
     */
    private fun setUpLishuiGeoLayer(lishuiGeoJson: LishuiGeoJson) {

        val features = mutableListOf<com.mapbox.geojson.Feature>()

        lishuiGeoJson.features.forEach { feature ->
            if (feature.geometry.type == "LineString") {

                val coordinates = feature.geometry.coordinates
                val routeCoordinates = mutableListOf<Point>()
                coordinates.forEach { coordinate ->
                    if (coordinate is MutableList<*>) {
                        if (coordinate[0] is Double) {
                            routeCoordinates.add(
                                Point.fromLngLat(coordinate[0] as Double, coordinate[1] as Double)
                            )
                        }
                    }

                }
                val lineString = LineString.fromLngLats(routeCoordinates)
                features.add(com.mapbox.geojson.Feature.fromGeometry(lineString))
            } else if (feature.geometry.type == "Polygon") {
                val coordinates = feature.geometry.coordinates[0]
                val routeCoordinates = mutableListOf<Point>()
                coordinates.forEach { coordinate ->
                    if (coordinate is MutableList<*>) {
                        if (coordinate[0] is Double) {
                            routeCoordinates.add(
                                Point.fromLngLat(coordinate[0] as Double, coordinate[1] as Double)
                            )
                        }
                    }
                }
                val lineString = LineString.fromLngLats(routeCoordinates)
                features.add(com.mapbox.geojson.Feature.fromGeometry(lineString))
            }
        }
        val featureCollection =
            FeatureCollection.fromFeatures(features)
        val geoJsonSource = GeoJsonSource("lishui-geojson", featureCollection)
        style.addSource(geoJsonSource)

        style.addLayer(
            LineLayer("lishui-geojson", "lishui-geojson").withProperties(
                PropertyFactory.lineDasharray(arrayOf<Float>(0.01f, 0.01f)),
                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                PropertyFactory.lineWidth(2f),
                PropertyFactory.lineColor(Color.parseColor("#FF03DAC5"))
            )
        )
    }
}