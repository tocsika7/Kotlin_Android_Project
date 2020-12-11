package com.example.mobiledev_app.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.mobiledev_app.R
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

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score,
            ScoreFragmentArgs.fromBundle(requireArguments()).username)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.onGameRestart.observe(viewLifecycleOwner, Observer { restart ->
            if(restart) onGameRestart()
        })

        return binding.root
    }

    private fun onGameRestart(){
        val action = ScoreFragmentDirections.actionScoreFragmentToTitleFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}