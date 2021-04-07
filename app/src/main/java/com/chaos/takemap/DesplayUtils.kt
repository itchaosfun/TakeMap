package com.chaos.takemap

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue


object DesplayUtils {
    var screenWidthPx = 0 //屏幕宽 px

    var screenHeightPx = 0 //屏幕高 px

    var density = 0f//屏幕密度

    var densityDPI = 0//屏幕密度

    var screenWidthDip = 0f //  dp单位

    var screenHeightDip = 0f //  dp单位


    fun <T> isEmpty(list: List<T>?): Boolean {
        return list == null || list.isEmpty()
    }

    /** 获取屏幕的高度  */
    fun getWindowsHeight(activity: Activity): Int {
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    /** 获取屏幕的宽度  */
    fun getWindowsWidth(activity: Activity): Int {
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float):Float {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    // 将px值转换为sp值
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale: Float = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun dip2px2(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2px(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        )
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    fun sp2px(context: Context, spValue: Float): Float {
        val fontScale: Float = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f)
    }
}