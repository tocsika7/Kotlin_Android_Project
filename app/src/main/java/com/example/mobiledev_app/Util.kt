package com.example.mobiledev_app

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledev_app.database.Result
import java.lang.StringBuilder
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}

fun formatResults(results: List<Result>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        results.forEach {
            append("<br>")
            append("<b>Player Name:</b>")
            append("\t${it.userName}<br>")
            append("<b>Score:</b>")
            append("\t${it.score}<br>")
            append("<b>Date:</b>")
            append("\t${convertLongToDateString(it.date)}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

class ResultItemViewHolder(var textView: TextView)
    : RecyclerView.ViewHolder(textView)

