package com.example.mobiledev_app.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledev_app.R
import com.example.mobiledev_app.databinding.FragmentLeaderboardBinding


class LeaderboardFragment : Fragment() {


    private lateinit var viewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentLeaderboardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_leaderboard,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LeaderboardViewModel::class.java)
        binding.leaderBoardViewModel = viewModel

        return binding.root
    }

}