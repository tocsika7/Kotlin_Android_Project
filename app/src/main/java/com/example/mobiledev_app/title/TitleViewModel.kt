package com.example.mobiledev_app.title

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel: ViewModel() {


    /* Value for showing the Username */
    val userName = MutableLiveData<String>()

    /* Field for navigating to the Game Screen */
    private val _eventGameStart = MutableLiveData<Boolean>()
    val eventGameStart: LiveData<Boolean>
        get() = _eventGameStart

    /* Field for navigating to the LeaderBoard screen */
    private val _navigateToLeaderBoard = MutableLiveData<Boolean>()
    val navigateToLeaderBoard: LiveData<Boolean>
        get() = _navigateToLeaderBoard


    init {
        userName.value = "Enter your username"
        _eventGameStart.value = false
        _navigateToLeaderBoard.value = false
    }

    /* LeaderBoard button clickListener */
    fun onClickLeaderBoard() {
        Log.i("Title", "LeaderBoard Button clicked.")
        _navigateToLeaderBoard.value = true
    }

    /* Play button clickListener */
    fun onPlayButtonClicked(){
        Log.i("Title", "Play Button clicked.")
        _eventGameStart.value = true
    }

    /* Function for setting the LeaderBoard navigation value to false after navigation */
    fun doneNavigating() {
        _navigateToLeaderBoard.value = false
    }



}