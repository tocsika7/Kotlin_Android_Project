package com.example.mobiledev_app.game

import android.util.Log
import kotlin.random.Random
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

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

    init {
        _score.value = 0
        _playerChoice.value = ""
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
            "paper" -> Log.i("GameViewModel", "Loss")
            "scissors" -> onWin()
        }
    }

    private fun checkWinPaper(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onWin()
            "paper" -> Log.i("GameViewModel", "Draw")
            "scissors" -> Log.i("GameViewModel", "Loss")
        }
    }

    private fun checkWinScissors(computerChoice: String) {
        when(computerChoice) {
            "rock" -> Log.i("GameViewModel", "Loss")
            "paper" -> onWin()
            "scissors" -> Log.i("GameViewModel", "Draw")
        }
    }

    fun onWin() {
        _score.value = (_score.value)?.plus(1)
    }



}