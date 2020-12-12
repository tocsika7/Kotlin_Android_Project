package com.example.mobiledev_app.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledev_app.database.ResultDatabaseDao
import java.lang.IllegalArgumentException

class GameViewModelFactory(
    private val dataSource: ResultDatabaseDao,
    private val application: Application,
    private val username: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(dataSource , application ,username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}