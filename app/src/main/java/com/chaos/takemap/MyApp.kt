package com.chaos.takemap

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chaos.takemap.db.AppDatabase

class MyApp : Application() {

    companion object {
        private var appDatabase: AppDatabase? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun setupContext(context: Context) {
            this.context = context
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupContext(this)
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "yc_db")
            .allowMainThreadQueries()
            .build()
    }

}