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

    init {
        _score.value = finalScore
        _username.value = username
    }
}