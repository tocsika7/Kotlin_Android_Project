package com.example.mobiledev_app.title

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel: ViewModel() {

    val userName = MutableLiveData<String>()


    init {
        userName.value = "Username"
    }

}