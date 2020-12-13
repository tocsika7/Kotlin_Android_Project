package com.example.mobiledev_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDatabaseDao {

    @Insert
    suspend fun insert(result: Result)

    @Query("DELETE FROM game_result_table")
    suspend fun clear()

    @Query("SELECT * FROM game_result_table ORDER BY score DESC")
    fun getAllResults(): LiveData<List<Result>>

    @Query("SELECT * FROM game_result_table ORDER BY userId DESC LIMIT -1")
    suspend fun getLatestResult(): Result?
}