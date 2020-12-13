package com.example.mobiledev_app.leaderboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.mobiledev_app.database.ResultDatabaseDao
import com.example.mobiledev_app.formatResults
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {

    val results = database.getAllResults()

    val resultsString = Transformations.map(results){ results ->
        formatResults(results, application.resources)
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

}