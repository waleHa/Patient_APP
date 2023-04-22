package com.trends.patientapplication.presentation.feature.patient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.trends.patientapplication.core.BaseFragment
import com.trends.patientapplication.domain.model.delete.DeletePatientResponse
import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.presentation.R
import com.trends.patientapplication.presentation.databinding.FragmentPatientBinding
import com.trends.patientapplication.presentation.feature.patient.adapter.PatientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PatientFragment : BaseFragment<FragmentPatientBinding>(R.layout.fragment_patient) {
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
//        initAdapter()
        initObserver()
        initListener()
        initAdapterView()
    }

    private fun initListener() {
        binding.fabAddPatient.setOnClickListener {
            findNavController().navigate(R.id.addPatientFragment)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initAdapterView() {
        adapter = PatientAdapter(::deletePatient,::onClickItem)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.patientsListSuccess.collect(::patientsCallSuccess)
        }

        lifecycleScope.launch {
            viewModel.patientsError.collect (::patientsCallError)
        }

        lifecycleScope.launch {
            viewModel.deletePatientsSuccess.observe(viewLifecycleOwner,::onPatientDeletedSuccess)
        }

        lifecycleScope.launch {
            viewModel.patientsListLoading.collect(::patientLoadingStatus)
        }
    }

    private fun onPatientDeletedSuccess(response: DeletePatientResponse?){
        if (response != null) {
            Toast.makeText(requireContext(),response.message,Toast.LENGTH_LONG).show()
            viewModel.getPatients()
        }
    }

    private fun deletePatient(id: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are you sure you want to remove this item?")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                viewModel.deletePatient(id)
                dialog.dismiss()
            }.show()
    }

    private fun patientsCallSuccess(list: List<PatientResponse>) {
        if (list.isNotEmpty()) {
            binding.imageError.isVisible = false
//        initAdapterView()
            adapter.submitList(list)
        }
    }

    private fun patientsCallError(exception: Exception?) {
        if (exception != null) {
            Toast.makeText(
                requireContext(),
                exception?.localizedMessage.toString(),
                Toast.LENGTH_LONG
            )
                .show()
            binding.imageError.isVisible = true
        }
    }

    private fun patientLoadingStatus(b: Boolean) {
        binding.progress.isVisible = b
    }

    private fun onClickItem(id:String){
        Log.i("Logs: PatientFragment", id)
        findNavController().navigate(R.id.detailsPatientFragment, bundleOf("id" to id))
    }
}