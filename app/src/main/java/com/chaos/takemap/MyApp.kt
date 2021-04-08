package com.chaos.takemap

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApp:Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun setupContext(context: Context){
            this.context = context
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupContext(this)
    }

}