package com.example.mobiledev_app.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledev_app.R
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result

class ResultAdapter: ListAdapter<Result, ResultAdapter.ViewHolder>(ResultDiffCallback()) {

    class ResultDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor (itemView: View): RecyclerView.ViewHolder(itemView){
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val score: TextView = itemView.findViewById(R.id.score)
        val date: TextView = itemView.findViewById(R.id.date)

        fun bind(
            item: Result
        ) {
            val res = itemView.context.resources
            userName.text = item.userName
            score.text = item.score.toString()
            date.text = convertLongToDateString(item.date)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.result_item_view, parent, false)

                return ViewHolder(view)
            }
        }

    }





}