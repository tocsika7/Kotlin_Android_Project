package com.example.mobiledev_app.result_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledev_app.database.ResultDatabaseDao
import java.lang.IllegalArgumentException

class ResultDetailViewModelFactory(
    private val resultId: Long,
    private val dataSource: ResultDatabaseDao): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultDetailViewModel::class.java)) {
            return ResultDetailViewModel(resultId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}