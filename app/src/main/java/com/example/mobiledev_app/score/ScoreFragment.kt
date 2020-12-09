package com.example.mobiledev_app.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}