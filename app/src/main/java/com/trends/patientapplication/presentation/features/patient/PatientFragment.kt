package com.trends.patientapplication.presentation.features.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.trends.patientapplication.databinding.FragmentPatientBinding
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import com.trends.patientapplication.presentation.features.patient.adapter.PatientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment : Fragment() {
    private lateinit var binding: FragmentPatientBinding
    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PatientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollecter()

    }

    private fun initRecyclerView(list: List<PatientRemoteModel>) {
        adapter = PatientAdapter(list)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun initCollecter() {
        lifecycleScope.launch {
            viewModel.patientsListSuccess.collect {
                if (it.isNotEmpty())
                    patientsCallSuccess(it)
            }
        }
        lifecycleScope.launch {
            viewModel.patientsListError.collect {
                if (it != null)
                    patientsCallError(it)
            }
        }
        lifecycleScope.launch {
            viewModel.patientsListLoading.collect {
                patientLoadingStatus(it)
            }
        }
    }


    private fun patientsCallSuccess(list: List<PatientRemoteModel>) {
//        Toast.makeText(requireContext(), list.toString(), Toast.LENGTH_LONG).show()
        initRecyclerView(list)
    }

    private fun patientsCallError(exception: Exception?) {
        Toast.makeText(requireContext(), exception?.localizedMessage.toString(), Toast.LENGTH_LONG)
            .show()
    }

    private fun patientLoadingStatus(b: Boolean) {
        binding.progress.isVisible = b
    }

}