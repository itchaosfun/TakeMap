package com.chaos.takemap

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup

object ThemeUtils {

    fun setStatusBar(activity: Activity, color: Int) {
        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
        val statusView = View(activity)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            getStatusBarHeight(activity)
        )
        statusView.setBackgroundColor(color)
        setStatusText(activity,true)
        viewGroup.addView(statusView, layoutParams)
    }

    fun setStatusText(activity: Activity, light: Boolean) {
        if (light) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}