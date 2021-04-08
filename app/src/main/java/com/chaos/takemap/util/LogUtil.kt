package com.chaos.takemap.util

import android.util.Log
import com.chaos.takemap.MyApp
import java.lang.StringBuilder
import java.util.*
import java.util.concurrent.Executors

object LogUtil {

    private val executors = Executors.newCachedThreadPool()

    fun d(tag: String, log: String) {
        Log.d(tag, log)
        writeLog(log)
    }

    fun e(tag: String, log: String) {
        Log.e(tag, log)
        writeLog(log)
    }

    fun i(tag: String, log: String) {
        Log.i(tag, log)
        writeLog(log)
    }

    fun w(tag: String, log: String) {
        Log.w(tag, log)
        writeLog(log)
    }

    private fun writeLog(log: String) {
        val currentThread = Thread.currentThread()
        val stringBuilder = StringBuilder()
        stringBuilder.append(Date().formatDate("yyyy-MM-dd HH:mm:ss.SSS"))
        stringBuilder.append(" ")
        stringBuilder.append("${currentThread.id}/${currentThread.name} ")
        stringBuilder.append(log)
        if (currentThread.name == "main") {
            executors.execute {
                FileUtils.writeLog(MyApp.context, stringBuilder.toString())
            }
        } else {
            FileUtils.writeLog(MyApp.context, stringBuilder.toString())
        }
    }
}