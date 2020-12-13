package com.example.mobiledev_app.leaderboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobiledev_app.database.ResultDatabaseDao

class LeaderboardViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {



}