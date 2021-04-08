package com.chaos.takemap.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaos.takemap.data.MapStyleEnum
import com.chaos.takemap.R
import com.chaos.takemap.util.LogUtil
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.fragment_map_box.view.*

class MapBoxFragment : BaseFragment() {

    private val TAG = "MapBoxFragment"

    private var layoutView: View? = null
    private var mapBoxMap: MapboxMap? = null

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
            val uiSettings = mapBoxMap.uiSettings
            uiSettings.isLogoEnabled = false
            uiSettings.isAttributionEnabled = false
            uiSettings.compassGravity = Gravity.BOTTOM
            mapBoxMap.setStyle(Style.LIGHT)
        }
    }

    /**
     * 设置地图的样式
     */
    override fun setStyle(mapStyle: Int) {
        if (mapBoxMap != null) {
            mapBoxMap!!.setStyle(
                MapStyleEnum.valueOfByType<String>(mapStyle) ?: Style.LIGHT
            )
        }
    }
}