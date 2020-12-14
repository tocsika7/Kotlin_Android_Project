package com.example.mobiledev_app.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledev_app.convertLongToDateString
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.databinding.ResultItemViewBinding

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

    class ViewHolder private constructor (val binding: ResultItemViewBinding): RecyclerView.ViewHolder(binding.root){
        val userName: TextView = binding.userName
        val score: TextView = binding.score
        val date: TextView = binding.date

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
                val binding = ResultItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }





}