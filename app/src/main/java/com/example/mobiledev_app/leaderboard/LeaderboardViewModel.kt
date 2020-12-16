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

    /* Get all game results from the database */
    val results = database.getAllResults()

    /* Value for navigating to the a certain result item in the RecyclerView */
    private val _navigateToResultDetail = MutableLiveData<Long>()
    val navigateToResultDetail: LiveData<Long>
        get() = _navigateToResultDetail

    /* Value for navigating to the Title screen */
    private val _navigateToTitle = MutableLiveData<Boolean>()
    val navigateToTitle: LiveData<Boolean>
        get() = _navigateToTitle

    /* PlayAgain Button clickListener */
    fun onPlayAgainClicked() {
        _navigateToTitle.value = true
    }

    fun onTitleNavigated() {
        _navigateToTitle.value = false
    }

    /* RecyclerView item clickListener */
    fun onResultClicked(id: Long) {
        Log.i("LeaderBoard", "Play Again Button Clicked")
        _navigateToResultDetail.value = id
    }

    fun onResultNavigated() {
        _navigateToResultDetail.value = null
    }

    /* Formatting the date from long to a date format  */
    val resultsString = Transformations.map(results){ results ->
        formatResults(results, application.resources)
    }

    /* Function for deleting everything in the db */
    private suspend fun clear() {
        Log.i("LeaderBoard", "Clear Button Clicked")
        database.clear()
    }

    /* Clear button clickListener */
    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }



}