package com.example.mobiledev_app.game

import android.app.Application

import android.util.Log
import androidx.lifecycle.*
import com.example.mobiledev_app.database.ResultDatabaseDao
import com.example.mobiledev_app.database.Result
import kotlinx.coroutines.launch

class GameViewModel(val database: ResultDatabaseDao,
                    application: Application,
                    username: String): AndroidViewModel(application) {


    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username


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

    private val _lastMatchResult = MutableLiveData<String>()
    val lastMatchResult: LiveData<String>
        get() = _lastMatchResult

    init {
        _lives.value = 3
        _score.value = 0
        _playerChoice.value = ""
        _computerChoice.value = ""
        _username.value = username
        _lastMatchResult.value = ""
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
            "rock" -> onDraw()
            "paper" -> onLoss()
            "scissors" -> onWin()
        }
    }

    private fun checkWinPaper(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onWin()
            "paper" -> onDraw()
            "scissors" -> onLoss()
        }
    }

    private fun checkWinScissors(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onLoss()
            "paper" -> onWin()
            "scissors" -> onDraw()
        }
    }

    fun onDraw() {
        _lastMatchResult.value = "Draw!"
    }

    fun onWin() {
        _score.value = (_score.value)?.plus(1)
        _lastMatchResult.value = "You Won!"
    }

    fun onGameFinish() {
        viewModelScope.launch {
            val result = Result(score = _score.value!!, userName = username.value.toString() )
            Log.i("GameViewModel", "Result added To Database: $result")
            insert(result)
        }
    }

    private suspend fun insert(result: Result) {
        database.insert(result)
    }

    private fun onLoss(){
        _lives.value = (_lives.value)?.minus(1)
        _lastMatchResult.value = "You Lost!"
    }



}