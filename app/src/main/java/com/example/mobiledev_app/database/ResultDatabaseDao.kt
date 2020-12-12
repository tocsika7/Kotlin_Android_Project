package com.example.mobiledev_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDatabaseDao {

    @Insert
    fun insert(result: Result)

    @Query("DELETE FROM game_result_table")
    fun clear()

    @Query("SELECT * FROM game_result_table ORDER BY score")
    fun getAllResults(): LiveData<List<Result>>
}