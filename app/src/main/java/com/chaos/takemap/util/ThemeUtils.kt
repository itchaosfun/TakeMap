package com.chaos.takemap.util

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.chaos.takemap.config.ThemeConfig
import com.chaos.takemap.model.THEME_DARK

object ThemeUtils {

    /**
     * 设置状态栏颜色
     */
    fun setStatusBar(activity: Activity, color: Int) {
        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
        val statusView = View(activity)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            getStatusBarHeight(activity)
        )
        statusView.setBackgroundColor(color)
        setStatusText(activity)
        viewGroup.addView(statusView, layoutParams)
    }

    /**
     * 设置状态栏的文字颜色,目前仅支持亮色和暗色两种
     */
    fun setStatusText(activity: Activity) {
        if (ThemeConfig.theme == THEME_DARK) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}