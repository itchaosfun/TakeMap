package com.chaos.takemap

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.chaos.takemap.ThemeUtils.setStatusBar
import com.chaos.takemap.ThemeUtils.setStatusText
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_config.*

class MainActivity : AppCompatActivity() {

    var mapType = 0
    var mapBoxMap: MapboxMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.fitDp()

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_main)

        setStatusBar(this, resources.getColor(R.color.transparent, resources.newTheme()))
        drawerLayout.setScrimColor(resources.getColor(R.color.transparent, resources.newTheme()))
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapBoxMap ->
            this.mapBoxMap = mapBoxMap
            val uiSettings = mapBoxMap.uiSettings
            uiSettings.isLogoEnabled = false
            uiSettings.isAttributionEnabled = false
            uiSettings.compassGravity = Gravity.BOTTOM

            mapBoxMap.setStyle(Style.LIGHT)
            ivLightMapSelect.visibility = View.VISIBLE
            mapType = 0
        }

        initListener()

        initDrawerLayoutView()
    }

    private fun initDrawerLayoutView() {
        rvRoadFeature.layoutManager = GridLayoutManager(this,5)
        rvRoadFeature.adapter = ElementAdapter(this)

        rvRoadSign.layoutManager = GridLayoutManager(this,5)
        rvRoadSign.adapter = ElementAdapter(this)

        rvRoadAttr.layoutManager = GridLayoutManager(this,5)
        rvRoadAttr.adapter = ElementAdapter(this)
    }

    private fun initListener() {
        llMapTypeLight.setOnClickListener {
            if (mapType == 0) {
                return@setOnClickListener
            }
            if (mapBoxMap != null) {
                mapBoxMap!!.setStyle(Style.LIGHT)
            }
            mapType = 0
            updateStyle()
        }

        llMapTypeDark.setOnClickListener {
            if (mapType == 1) {
                return@setOnClickListener
            }
            if (mapBoxMap != null) {
                mapBoxMap!!.setStyle(Style.DARK)
            }
            mapType = 1
            updateStyle()
        }

        llMapTypeStreet.setOnClickListener {
            if (mapType == 2) {
                return@setOnClickListener
            }
            if (mapBoxMap != null) {
                mapBoxMap!!.setStyle(Style.MAPBOX_STREETS)
            }
            mapType = 2
            updateStyle()
        }

        llNodePoint.setOnClickListener {
            ivNodePointSelect.visibility = View.VISIBLE
            ivRoadPointSelect.visibility = View.INVISIBLE
            ivLanePointSelect.visibility = View.INVISIBLE
            drawerLayout.closeDrawer(GravityCompat.END)
        }

        llRoadPoint.setOnClickListener {
            ivRoadPointSelect.visibility = View.VISIBLE
            ivNodePointSelect.visibility = View.INVISIBLE
            ivLanePointSelect.visibility = View.INVISIBLE
            drawerLayout.closeDrawer(GravityCompat.END)
        }

        llLanePoint.setOnClickListener {
            ivLanePointSelect.visibility = View.VISIBLE
            ivRoadPointSelect.visibility = View.INVISIBLE
            ivNodePointSelect.visibility = View.INVISIBLE
            drawerLayout.closeDrawer(GravityCompat.END)
        }

        ivLayer.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        ivHeader.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun updateStyle() {
        when (mapType) {
            0 -> {
                //light
                ivHeader.setImageResource(R.mipmap.light_header)
                ivLayer.setImageResource(R.mipmap.light_layer)
                setStatusText(this, true)
                ivLightMapSelect.visibility = View.VISIBLE
                ivDarkMapSelect.visibility = View.INVISIBLE
                ivStreetMapSelect.visibility = View.INVISIBLE

            }
            1 -> {
                //dark
                ivHeader.setImageResource(R.mipmap.dark_header)
                ivLayer.setImageResource(R.mipmap.dark_layer)
                setStatusText(this, false)
                ivLightMapSelect.visibility = View.INVISIBLE
                ivDarkMapSelect.visibility = View.VISIBLE
                ivStreetMapSelect.visibility = View.INVISIBLE
            }
            2 -> {
                //street
                ivHeader.setImageResource(R.mipmap.light_header)
                ivLayer.setImageResource(R.mipmap.light_layer)
                setStatusText(this, true)
                ivLightMapSelect.visibility = View.INVISIBLE
                ivDarkMapSelect.visibility = View.INVISIBLE
                ivStreetMapSelect.visibility = View.VISIBLE
            }
        }
        drawerLayout.closeDrawer(GravityCompat.END)

    }
}