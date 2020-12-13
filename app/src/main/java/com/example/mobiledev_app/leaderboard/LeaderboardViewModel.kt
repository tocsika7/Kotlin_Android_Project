package com.example.mobiledev_app.leaderboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.mobiledev_app.database.ResultDatabaseDao
import com.example.mobiledev_app.formatResults

class LeaderboardViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {

    val results = database.getAllResults()

    val resultsString = Transformations.map(results){ results ->
        formatResults(results, application.resources)
    }

}