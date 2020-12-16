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

    /* Value for showing the Username */
    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    /* Value for showing the Current Score */
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    /* Variable for string the last choice of the Player */
    private var playerChoice: String = ""

    /* Value for the last choice of the Computer */
    private val _computerChoice = MutableLiveData<String>()
    val computerChoice: LiveData<String>
        get() = _computerChoice

    /* Value for showing the remaining lives of the Player */
    private val _lives = MutableLiveData<Int>()
    val lives: LiveData<Int>
        get() = _lives

    /* Value for showing the latest matches result */
    private val _lastMatchResult = MutableLiveData<String>()
    val lastMatchResult: LiveData<String>
        get() = _lastMatchResult

    init {
        _lives.value = 3
        _score.value = 0
        _computerChoice.value = ""
        _username.value = username
        _lastMatchResult.value = ""
    }

    /* Function for generating the choice of the Computer */
    private fun setComputerChoice() {
        val choices = mutableListOf(
            "rock",
            "paper",
            "scissors"
        )
        _computerChoice.value = choices.random()
    }

    /* Rock Button clickListener */
    fun onRockClicked(){
        playerChoice = "rock"
        setComputerChoice()
        checkWin()
        Log.i("Game", "Rock Button clicked")
    }

    /* Paper Button clickListener */
    fun onPaperClicked(){
        playerChoice = "paper"
        setComputerChoice()
        checkWin()
        Log.i("Game", "Paper Button clicked")
    }

    /* Scissors Button clickListener */
    fun onScissorsClicked(){
        playerChoice = "scissors"
        setComputerChoice()
        checkWin()
        Log.i("Game", "Scissors Button clicked")
    }

    /* Function for checking if the player have won */
    private fun checkWin(){
        when(playerChoice) {
            "rock" -> computerChoice.value?.let { checkWinRock(it) }
            "paper" -> computerChoice.value?.let { checkWinPaper(it) }
            "scissors" -> computerChoice.value?.let { checkWinScissors(it) }
        }
    }

    /* Function for deciding the winner on a Rock pick */
    private fun checkWinRock(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onDraw()
            "paper" -> onLoss()
            "scissors" -> onWin()
        }
    }

    /* Function for deciding the winner on a Paper pick */
    private fun checkWinPaper(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onWin()
            "paper" -> onDraw()
            "scissors" -> onLoss()
        }
    }

    /* Function for deciding the winner on a Scissors pick */
    private fun checkWinScissors(computerChoice: String) {
        when(computerChoice) {
            "rock" -> onLoss()
            "paper" -> onWin()
            "scissors" -> onDraw()
        }
    }

    /* Function for draw result */
    private fun onDraw() {
        _lastMatchResult.value = "Draw!"
        Log.i("Game", "Game Result: Draw")
    }

    /* Function for player win result, increasing the score */
    private fun onWin() {
        _score.value = (_score.value)?.plus(1)
        _lastMatchResult.value = "You Won!"
        Log.i("Game", "Game Result: Player won.")
    }

    /* Function for player loss result, decreasing the score */
    private fun onLoss(){
        _lives.value = (_lives.value)?.minus(1)
        _lastMatchResult.value = "You Lost!"
        Log.i("Game", "Game Result: Player lost.")
    }

    /* Function for invoking the db's insert function */
    private suspend fun insert(result: Result) {
        database.insert(result)
    }

    /* Function called on the end of the Game inserting the date into the db */
    fun onGameFinish() {
        viewModelScope.launch {
            val result = Result(score = _score.value!!, userName = username.value.toString() )
            insert(result)
            Log.i("Game", "GameResult added to the Database: $result")
        }
    }







}