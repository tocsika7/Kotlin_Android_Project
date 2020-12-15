package com.example.mobiledev_app.leaderboard

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result

@BindingAdapter("dateFormatted")
fun TextView.getDateFormatted(item: Result) {
    text = convertLongToDateString(item.date)
}

@BindingAdapter("scoreString")
fun TextView.setScoreString(item: Result) {
    text = item.score.toString()
}

@BindingAdapter("userNameString")
fun TextView.setUserNameString(item: Result) {
    text = item.userName
}