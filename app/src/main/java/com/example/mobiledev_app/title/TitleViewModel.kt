package com.example.mobiledev_app.title

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel: ViewModel() {

    val userName = MutableLiveData<String>()


    private val _eventGameStart = MutableLiveData<Boolean>()
    val eventGameStart: LiveData<Boolean>
        get() = _eventGameStart

    private val _navigateToLeaderBoard = MutableLiveData<Boolean>()
    val navigateToLeaderBoard: LiveData<Boolean>
        get() = _navigateToLeaderBoard

    fun onClickLeaderBoard() {
        _navigateToLeaderBoard.value = true
    }

    fun doneNavigating() {
        _navigateToLeaderBoard.value = false
    }

    init {
        userName.value = "Enter your username"
        _eventGameStart.value = false
        _navigateToLeaderBoard.value = false
    }

    fun onPlayButtonClicked(){
        _eventGameStart.value = true
    }

}