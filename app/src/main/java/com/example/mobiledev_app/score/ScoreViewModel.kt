package com.example.mobiledev_app.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int , username: String) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _username = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _username

    private val _onGameRestart = MutableLiveData<Boolean>()
    val onGameRestart: LiveData<Boolean>
        get() = _onGameRestart

    init {
        _score.value = finalScore
        _username.value = username
        _onGameRestart.value = false
    }

    fun onPlayAgainClicked(){
        _onGameRestart.value = true
    }
}