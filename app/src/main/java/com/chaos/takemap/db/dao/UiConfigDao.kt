package com.chaos.takemap.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chaos.takemap.db.UiConfigEntity

@Dao
interface UiConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(uiConfigEntity: UiConfigEntity)

    @Query("SELECT * FROM ui_config WHERE user_id == :userId LIMIT 1")
    abstract fun query(userId:String):UiConfigEntity
}