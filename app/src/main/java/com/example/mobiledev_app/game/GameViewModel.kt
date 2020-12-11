package com.example.mobiledev_app.game

import android.util.Log
import kotlin.random.Random
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(username: String): ViewModel() {


    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val username:String = username;

    fun getUsername(): String {
        return this.username
    }


    // Current Score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _playerChoice = MutableLiveData<String>()
    val playerChoice: LiveData<String>
        get() = _playerChoice

    private val _computerChoice = MutableLiveData<String>()
    val computerChoice: LiveData<String>
        get() = _computerChoice

    private val _lives = MutableLiveData<Int>()
    val lives: LiveData<Int>
        get() = _lives

    init {
        _score.value = 0
        _playerChoice.value = ""
        _computerChoice.value = ""
        _eventGameFinish.value = false
        _lives.value = 3
    }

    fun onRockClicked(){
        _playerChoice.value = "rock"
        setComputerChoice()
        checkWin()
        Log.i("GameViewModel", "Rock clicked")
    }

    fun onPaperClicked(){
        _playerChoice.value = "paper"
        setComputerChoice()
        checkWin()
        Log.i("GameViewModel", "Paper clicked")
    }

    fun onScissorsClicked(){
        _playerChoice.value = "scissors"
        setComputerChoice()
        checkWin()
        Log.i("GameViewModel", "Scissors clicked")
    }

    private fun setComputerChoice() {
        val choices = mutableListOf(
            "rock",
            "paper",
            "scissors"
        )
        _computerChoice.value = choices.random()
    }

    private fun checkWin(){
        when(playerChoice.value) {
            "rock" -> computerChoice.value?.let { checkWinRock(it) }
            "paper" -> computerChoice.value?.let { checkWinPaper(it) }
            "scissors" -> computerChoice.value?.let { checkWinScissors(it) }
        }
    }

    private fun checkWinRock(computerChoice: String) {
        when(computerChoice) {
            "rock" -> Log.i("GameViewModel", "Draw")
            "paper" -> onLoss()
            "scissors" -> onWin()
        }
    }

    private fun checkWinPaper(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onWin()
            "paper" -> Log.i("GameViewModel", "Draw")
            "scissors" -> onLoss()
        }
    }

    private fun checkWinScissors(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onLoss()
            "paper" -> onWin()
            "scissors" -> Log.i("GameViewModel", "Draw")
        }
    }

    fun onWin() {
        _score.value = (_score.value)?.plus(1)
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    private fun onLoss(){
        _lives.value = (_lives.value)?.minus(1)
    }




}