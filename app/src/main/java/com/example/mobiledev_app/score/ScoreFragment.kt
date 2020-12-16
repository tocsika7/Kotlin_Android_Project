package com.example.mobiledev_app.score

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.mobiledev_app.R
import com.example.mobiledev_app.database.ResultDatabase
import com.example.mobiledev_app.database.ResultDatabaseDao
import com.example.mobiledev_app.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val binding: FragmentScoreBinding = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_score,
           container,
           false
       )


        val application = requireNotNull(this.activity).application
        val dataSource = ResultDatabase.getInstance(application).resultDatabaseDao

        viewModelFactory = ScoreViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /* Observer for the Play Again Button's LiveData */
        viewModel.onGameRestart.observe(viewLifecycleOwner, Observer { restart ->
            if(restart) onGameRestart()
        })

        /* Observer for the LeaderBoard Button's LiveData */
        viewModel.onShowLeaderboard.observe(viewLifecycleOwner, Observer { leaderboard ->
            if(leaderboard) onShowLeaderboard()
        })

        return binding.root
    }

    /* Function for navigating to the LeaderBoard screen */
    private fun onShowLeaderboard(){
        val action = ScoreFragmentDirections.actionScoreFragmentToLeaderboardFragment()
        NavHostFragment.findNavController(this).navigate(action)
        Log.i("Score", "Navigating to the LeaderBoard screen")
    }

    /* Function for navigating to the Title screen */
    private fun onGameRestart(){
        val action = ScoreFragmentDirections.actionScoreFragmentToTitleFragment()
        NavHostFragment.findNavController(this).navigate(action)
        Log.i("Score", "Navigating to the Title screen")
    }

}