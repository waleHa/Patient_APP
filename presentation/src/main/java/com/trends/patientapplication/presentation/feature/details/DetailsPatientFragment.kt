package com.trends.patientapplication.presentation.feature.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.trends.patientapplication.core.BaseFragment
import com.trends.patientapplication.presentation.R
import com.trends.patientapplication.presentation.databinding.FragmentDetailsPatientBinding
import com.trends.patientapplication.presentation.databinding.FragmentPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsPatientFragment :  BaseFragment<FragmentDetailsPatientBinding>(R.layout.fragment_details_patient ) {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch{
            viewModel.detailsSuccessStateFlow.collect{
                if(it != null){
                    binding.model = it
                }
            }
        }

        lifecycleScope.launch {
            viewModel.detailsLoadingStateFlow.collect{
                binding.progress.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.detailsErrorStateFlow.collect{
                if(it != null){
                    Log.e("Error on Details P:",it.localizedMessage)
                }
            }
        }
    }
}
