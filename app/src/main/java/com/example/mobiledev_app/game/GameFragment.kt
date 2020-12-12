package com.example.mobiledev_app.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.mobiledev_app.R
import com.example.mobiledev_app.database.ResultDatabase
import com.example.mobiledev_app.databinding.FragmentGameBinding
import com.example.mobiledev_app.score.ScoreFragmentArgs
import com.example.mobiledev_app.score.ScoreViewModel
import com.example.mobiledev_app.score.ScoreViewModelFactory


class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = ResultDatabase.getInstance(application).resultDatabaseDao

        viewModelFactory = GameViewModelFactory(dataSource,
            application,
            GameFragmentArgs.fromBundle(requireArguments()).username)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.gameViewModel = viewModel
        viewModel.lives.observe(viewLifecycleOwner, Observer { lives ->
            if(lives == 0) gameFinished()
        })



        return binding.root
    }

    private fun gameFinished(){
        viewModel.onGameFinish()
        val action = GameFragmentDirections.actionGameToScore(
            score = viewModel.score.value?:0,
            username = viewModel.getUsername()
        )
        NavHostFragment.findNavController(this).navigate(action)
    }





}