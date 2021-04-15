package com.chaos.takemap.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chaos.takemap.db.dao.UiConfigDao

@Database(entities = [UiConfigEntity::class],version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun uiConfigDao():UiConfigDao
}