package com.chaos.takemap.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaos.takemap.model.enummodel.MapStyleEnum
import com.chaos.takemap.R
import com.chaos.takemap.model.*
import com.chaos.takemap.ui.helper.MapBoxHelper
import com.chaos.takemap.util.FileUtils
import com.chaos.takemap.util.LogUtil
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import kotlinx.android.synthetic.main.fragment_map_box.view.*
import java.io.File

class MapBoxFragment : BaseFragment() {

    private val TAG = "MapBoxFragment"

    private var layoutView: View? = null
    private var mapBoxMap: MapboxMap? = null
    private var style: Style? = null
    private var mapBoxHelper: MapBoxHelper? = null

    companion object {
        @JvmStatic
        fun newInstance() = MapBoxFragment()
    }

    override fun layoutInflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Mapbox.getInstance(this.requireContext(), getString(R.string.mapbox_access_token))
        layoutView = inflater.inflate(R.layout.fragment_map_box, container, false)
        initView(savedInstanceState)
        return layoutView!!
    }

    private fun initView(savedInstanceState: Bundle?) {
        if (layoutView == null) {
            LogUtil.e(TAG, "layoutView is null")
            return
        }
        layoutView!!.mapView.onCreate(savedInstanceState)
        layoutView!!.mapView.getMapAsync { mapBoxMap ->
            this.mapBoxMap = mapBoxMap
            mapBoxMap.setStyle(Style.LIGHT) {
                this.style = it
                mapBoxHelper =
                    MapBoxHelper(layoutView!!.mapView, this.requireContext(), mapBoxMap, it)
                mapBoxHelper!!.uiSetting()
            }
        }
    }

    /**
     * 设置地图的样式
     */
    override fun setStyle(mapStyle: Int) {
        if (mapBoxMap != null) {
            mapBoxMap!!.setStyle(
                MapStyleEnum.valueOfByType<String>(mapStyle) ?: Style.LIGHT
            ) {
                this.style = it
            }
        }
    }

    /**
     * 加载地图
     */
    fun loadMap(type: Int, content: Content) {
        if (mapBoxHelper != null) {
            mapBoxHelper!!.loadMap(type,content)
        }
    }

}