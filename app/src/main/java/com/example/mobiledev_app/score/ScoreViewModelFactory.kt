package com.example.mobiledev_app.score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledev_app.database.ResultDatabaseDao

class ScoreViewModelFactory(
    private val dataSource: ResultDatabaseDao,
    private val application: Application
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(dataSource , application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}