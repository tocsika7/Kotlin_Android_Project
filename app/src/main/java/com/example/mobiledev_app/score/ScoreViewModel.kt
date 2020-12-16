package com.example.mobiledev_app.score

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.database.ResultDatabaseDao
import kotlinx.coroutines.launch

class ScoreViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {

    /* Value for showing the score */
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    /* Value for showing the username */
    private val _username = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _username

    /* Value for showing the date when the game has finished */
    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    /* Transformations: Converting the long date to a date format */
    val dateString = Transformations.map(date) {
        date -> convertLongToDateString(date)
    }

    /* Value for navigating to the Title Screen */
    private val _onGameRestart = MutableLiveData<Boolean>()
    val onGameRestart: LiveData<Boolean>
        get() = _onGameRestart

    /* Value for navigating to the LeaderBoard screen */
    private val _onShowLeaderboard = MutableLiveData<Boolean>()
    val onShowLeaderboard: LiveData<Boolean>
        get() = _onShowLeaderboard


    /* Invoking the dao function for getting the latest results */
    private suspend fun getResult(): Result? {
        return database.getLatestResult()
    }

    /* Getting the latest game's userdata from the db */
    private fun initScoreAndUserName(){
        viewModelScope.launch {
            val result = getResult()
            if (result != null) {
                _score.value = result.score
                _username.value = result.userName
                _date.value = result.date
                Log.i("Score", "Latest result queried from the db: ${result.toString()}")
            }
        }
    }

    init {
        initScoreAndUserName()
        _onGameRestart.value = false
        _onShowLeaderboard.value = false
    }

    /* Play Again Button clickListener */
    fun onPlayAgainClicked(){
        _onGameRestart.value = true
        Log.i("Score", "Play Again Button Clicked")
    }

    /* LeaderBoard Button clickListener */
    fun onLeaderboardClicked() {
        _onShowLeaderboard.value = true
        Log.i("Score", "LeaderBoard Button Clicked")
    }


}