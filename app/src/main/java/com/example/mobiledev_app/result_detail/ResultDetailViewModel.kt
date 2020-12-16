package com.example.mobiledev_app.result_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.database.ResultDatabaseDao


class ResultDetailViewModel(
    private val resultId: Long = 0L,
    dataSource: ResultDatabaseDao) : ViewModel() {

    val database = dataSource
    private val result: LiveData<Result>

    fun getResult() = result

    fun formatDate(date: Long) = convertLongToDateString(date)

    init {
        result = database.getResultById(resultId)
    }

    private val _navigateToLeaderBoard = MutableLiveData<Boolean?>()
    val navigateToLeaderBoard: LiveData<Boolean?>
        get() = _navigateToLeaderBoard

    fun doneNavigating() {
        _navigateToLeaderBoard.value = null
    }

    fun onClose() {
        _navigateToLeaderBoard.value = true
    }


}