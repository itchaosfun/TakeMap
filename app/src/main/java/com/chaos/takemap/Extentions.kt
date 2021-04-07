package com.chaos.takemap

import android.content.Context
import android.graphics.Point
import com.google.gson.Gson
import java.lang.Exception
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Context.fitDp() {
    val appDisplayMetrics = applicationContext.resources.displayMetrics
    println("widthPixels=${appDisplayMetrics.widthPixels}，heightPixels=${appDisplayMetrics.heightPixels}")
    val targetDensity = appDisplayMetrics.widthPixels.toFloat() / 1280
    println("density $targetDensity")
    val targetDpi = 160 * targetDensity
    println("dpi ${targetDpi.toInt()}")
    val targetScaleDensity =
        appDisplayMetrics.scaledDensity / appDisplayMetrics.density * targetDensity
    println("scaledDensity $targetScaleDensity")
    applicationContext.resources.displayMetrics.density = targetDensity
    applicationContext.resources.displayMetrics.densityDpi = targetDpi.toInt()
    applicationContext.resources.displayMetrics.scaledDensity = targetScaleDensity

    resources.displayMetrics.density = targetDensity
    resources.displayMetrics.densityDpi = targetDpi.toInt()
    resources.displayMetrics.scaledDensity = targetScaleDensity
}

fun Date.formatDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.CHINA)
    return simpleDateFormat.format(this)
}

fun Long.formatDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.CHINA)
    return simpleDateFormat.format(Date(this))
}

fun <E> MutableList<E>.removeObjIf(function: (E) -> Boolean) {
    val each = iterator()
    while (each.hasNext()) {
        val next = each.next()
        if (function(next)) {
            each.remove()
        }
    }
}

fun <E> MutableList<E>.removeElementIf(condition: (E) -> Boolean, done: (E) -> Unit) {
    val iterator = iterator()
    println("size = $size")
    while (iterator.hasNext()) {
        val next = iterator.next()
        if (condition(next)) {
            iterator.remove()
            done(next)
        }
    }
}

fun Point.isInBounds(dst: Point, toleranceX: Int = 0, toleranceY: Int = 0): Boolean {
    return abs(dst.x - x) <= toleranceX && abs(dst.y - y) <= toleranceY
}

fun Number?.r6(): String {
    return this.run {
        if (this == null) {
            "0"
        } else {
            val df = BigDecimal(this.toDouble())
            df.setScale(6, RoundingMode.HALF_UP).toString()
        }
    }
}

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun <T> String.fromJson(clazz: Class<T>): T? {
    try {
        return Gson().fromJson(this, clazz)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

fun <T> String.fromJson(type: Type): T {
    return Gson().fromJson(this, type)
}

val <T : Number> T?.value
    get() = this.run { this ?: 0 as T }



