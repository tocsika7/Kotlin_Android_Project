package com.example.mobiledev_app.score

import android.app.Application
import androidx.lifecycle.*
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.database.ResultDatabaseDao
import kotlinx.coroutines.launch

class ScoreViewModel(
    val database: ResultDatabaseDao,
    application: Application)
    : AndroidViewModel(application) {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _username = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _username

    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    val dateString = Transformations.map(date) {
        date -> convertLongToDateString(date)
    }

    private val _onGameRestart = MutableLiveData<Boolean>()
    val onGameRestart: LiveData<Boolean>
        get() = _onGameRestart

    private val _onShowLeaderboard = MutableLiveData<Boolean>()
    val onShowLeaderboard: LiveData<Boolean>
        get() = _onShowLeaderboard

    init {
        initScoreAndUserName()
        _onGameRestart.value = false
        _onShowLeaderboard.value = false
    }

    fun onPlayAgainClicked(){
        _onGameRestart.value = true
    }

    private fun initScoreAndUserName(){
        viewModelScope.launch {
            var result = getResult()
            if (result != null) {
                _score.value = result.score
            }
            if (result != null) {
                _username.value = result.userName
            }
            if (result != null) {
                _date.value = result.date
            }
        }
    }

    private suspend fun getResult(): Result? {
        var result = database.getLatestResult()
        return result
    }


    fun onLeaderboardClicked() {
        _onShowLeaderboard.value = true
    }
}