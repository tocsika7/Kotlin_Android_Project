package com.example.mobiledev_app.leaderboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.database.ResultDatabaseDao
import com.example.mobiledev_app.formatResults
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {

    val results = database.getAllResults()


    private val _navigateToResultDetail = MutableLiveData<Long>()
    val navigateToResultDetail: LiveData<Long>
        get() = _navigateToResultDetail

    private val _navigateToTitle = MutableLiveData<Boolean>()
    val navigateToTitle: LiveData<Boolean>
        get() = _navigateToTitle

    fun onPlayAgainClicked() {
        _navigateToTitle.value = true
    }

    fun onTitleNavigated() {
        _navigateToTitle.value = false
    }

    fun onResultClicked(id: Long) {
        _navigateToResultDetail.value = id
    }

    fun onResultNavigated() {
        _navigateToResultDetail.value = null
    }


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