package com.example.mobiledev_app.title

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mobiledev_app.R
import com.example.mobiledev_app.databinding.FragmentTitleBinding
import com.example.mobiledev_app.game.GameFragmentDirections
import com.example.mobiledev_app.game.GameViewModel


class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding
    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.titleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventGameStart.observe(viewLifecycleOwner, Observer { start ->
                if (start) onPlayStart()
            })

        return binding.root



    }

    private fun onPlayStart() {
        Log.i("Title", "onPlayStart called")
        val action = TitleFragmentDirections.actionTitleFragmentToGameFragment(
            username = viewModel.userName.value!!
        )
        Log.i("Title", "username valued passed")
        NavHostFragment.findNavController(this).navigate(action)
    }
}



