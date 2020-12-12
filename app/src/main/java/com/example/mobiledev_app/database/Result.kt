package com.example.mobiledev_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_result_table")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "game_date")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "user_name")
    var userName: String = "user",

    @ColumnInfo(name = "score")
    var score: Int = 0
)