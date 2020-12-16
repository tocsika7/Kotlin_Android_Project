package com.example.mobiledev_app.result_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobiledev_app.R
import com.example.mobiledev_app.database.ResultDatabase
import com.example.mobiledev_app.databinding.FragmentResultDetailBinding

class ResultDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_result_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments =  ResultDetailFragmentArgs.fromBundle(requireArguments())

        val dataSource = ResultDatabase.getInstance(application).resultDatabaseDao
        val viewModelFactory = ResultDetailViewModelFactory(arguments.resultId, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(ResultDetailViewModel::class.java)

        binding.resultDetailViewModel= viewModel
        binding.setLifecycleOwner(this)

        viewModel.navigateToLeaderBoard.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    ResultDetailFragmentDirections.actionResultDetailFragmentToLeaderboardFragment()
                )
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }


}