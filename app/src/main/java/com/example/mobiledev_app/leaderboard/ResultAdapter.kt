package com.example.mobiledev_app.leaderboard

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledev_app.R
import com.example.mobiledev_app.ResultItemViewHolder
import com.example.mobiledev_app.database.Result

class ResultAdapter: RecyclerView.Adapter<ResultItemViewHolder>()  {
    var data = listOf<Result>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.score.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.result_item_view, parent, false) as TextView

        return ResultItemViewHolder(view)
    }





}