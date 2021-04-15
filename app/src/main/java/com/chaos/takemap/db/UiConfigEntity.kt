package com.chaos.takemap.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chaos.takemap.util.formatDate
import java.util.*

@Entity(tableName = "ui_config")
data class UiConfigEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "theme") var theme: Int,
    @ColumnInfo(name = "user_id") var userId: String,
    @ColumnInfo(name = "create_time") var createTime: String = Date().formatDate("yyyy-MM-dd HH:mm:ss"),
    @ColumnInfo(name = "modify_time") var modifyTime: String = Date().formatDate("yyyy-MM-dd HH:mm:ss"),
)