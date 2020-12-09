package com.example.mobiledev_app.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledev_app.R
import com.example.mobiledev_app.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel: GameViewModel


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

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.gameViewModel = viewModel

        return binding.root
    }

    private fun gameFinished(){
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
    }





}