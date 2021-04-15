package com.chaos.takemap.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaos.takemap.R
import com.chaos.takemap.config.PointConfig
import com.chaos.takemap.config.ThemeConfig
import com.chaos.takemap.model.*
import com.chaos.takemap.model.enummodel.ViewIconEnum
import com.chaos.takemap.ui.adapter.ElementAdapter
import com.chaos.takemap.ui.adapter.SettingAdapter
import com.chaos.takemap.ui.fragment.MapBoxFragment
import com.chaos.takemap.util.FileUtils
import com.chaos.takemap.util.ThemeUtils.setStatusBar
import com.chaos.takemap.util.ThemeUtils.setStatusText
import com.chaos.takemap.util.fitDp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_config.*
import kotlinx.android.synthetic.main.layout_mine.*

class MainActivity : AppCompatActivity() {

    var mapBoxFragment: MapBoxFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.fitDp()

        setContentView(R.layout.activity_main)

        setStatusBar(this, resources.getColor(R.color.transparent, resources.newTheme()))
        drawerLayout.setScrimColor(resources.getColor(R.color.transparent, resources.newTheme()))

        initListener()

        initMapView()

        initDrawerLayoutView()
    }

    private fun initMapView() {
        mapBoxFragment = MapBoxFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.flMap, mapBoxFragment!!).commitNow()
    }

    private fun initDrawerLayoutView() {
        ivLightMapSelect.visibility = View.VISIBLE

        val roadFeature = FileUtils.getAssertAsData<ElementData>(this, "road_feature.json")
        val roadSign = FileUtils.getAssertAsData<ElementData>(this, "road_sign.json")
        val roadAttr = FileUtils.getAssertAsData<ElementData>(this, "road_attr.json")
        val setting = FileUtils.getAssertAsData<SettingData>(this, "setting.json")

        rvRoadFeature.layoutManager = GridLayoutManager(this, 5)
        rvRoadFeature.adapter = ElementAdapter(this, roadFeature)

        rvRoadSign.layoutManager = GridLayoutManager(this, 5)
        rvRoadSign.adapter = ElementAdapter(this, roadSign)

        rvRoadAttr.layoutManager = GridLayoutManager(this, 5)
        rvRoadAttr.adapter = ElementAdapter(this, roadAttr)

        rvSetting.layoutManager = LinearLayoutManager(this)
        rvSetting.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val settingAdapter = SettingAdapter(this, setting)
        rvSetting.adapter = settingAdapter
        settingAdapter.setOnMapDataSelectListener { type,content ->
            mapBoxFragment?.loadMap(type,content)
        }
    }


    private fun initListener() {
        llMapTypeLight.setOnClickListener {
            if (ThemeConfig.theme == THEME_LIGHT) {
                return@setOnClickListener
            }
            ThemeConfig.theme = THEME_LIGHT
            if (mapBoxFragment != null) {
                mapBoxFragment!!.setStyle(ThemeConfig.theme)
            }
            updateStyle()
            updateMapStyleSelect()
        }

        llMapTypeDark.setOnClickListener {
            if (ThemeConfig.theme == THEME_DARK) {
                return@setOnClickListener
            }
            ThemeConfig.theme = THEME_DARK
            if (mapBoxFragment != null) {
                mapBoxFragment!!.setStyle(ThemeConfig.theme)
            }
            updateStyle()
            updateMapStyleSelect()
        }

        llMapTypeStreet.setOnClickListener {
            if (ThemeConfig.theme == THEME_STREET) {
                return@setOnClickListener
            }
            ThemeConfig.theme = THEME_STREET
            if (mapBoxFragment != null) {
                mapBoxFragment!!.setStyle(ThemeConfig.theme)
            }
            updateStyle()
            updateMapStyleSelect()
        }

        llNodePoint.setOnClickListener {
            if (PointConfig.pointType == POINT_NODE) {
                return@setOnClickListener
            }
            PointConfig.pointType = POINT_NODE
            updatePointSelect()
        }

        llRoadPoint.setOnClickListener {
            if (PointConfig.pointType == POINT_ROAD) {
                return@setOnClickListener
            }
            PointConfig.pointType = POINT_ROAD
            updatePointSelect()
        }

        llLanePoint.setOnClickListener {
            if (PointConfig.pointType == POINT_LANE) {
                return@setOnClickListener
            }
            PointConfig.pointType = POINT_LANE
            updatePointSelect()
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

    /**
     * 更新点类型选中效果
     */
    private fun updatePointSelect() {
        ivNodePointSelect.visibility = if (PointConfig.pointType == POINT_NODE) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        ivRoadPointSelect.visibility = if (PointConfig.pointType == POINT_ROAD) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        ivLanePointSelect.visibility = if (PointConfig.pointType == POINT_LANE) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        drawerLayout.closeDrawer(GravityCompat.END)
    }

    /**
     * 更新选中效果
     */
    private fun updateMapStyleSelect() {
        ivLightMapSelect.visibility = if (ThemeConfig.theme == THEME_LIGHT) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        ivDarkMapSelect.visibility = if (ThemeConfig.theme == THEME_DARK) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        ivStreetMapSelect.visibility = if (ThemeConfig.theme == THEME_STREET) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        drawerLayout.closeDrawer(GravityCompat.END)
    }

    /**
     * 更新样式
     * 包括:
     * 1.头像,图层等的icon
     * 2.状态栏文字颜色
     */
    private fun updateStyle() {
        ivHeader.setImageResource(
            ViewIconEnum.valueOfByTheme(ICON_TYPE_HEADER)
        )
        ivLayer.setImageResource(
            ViewIconEnum.valueOfByTheme(ICON_TYPE_LAYER)
        )
        setStatusText(this)
    }
}