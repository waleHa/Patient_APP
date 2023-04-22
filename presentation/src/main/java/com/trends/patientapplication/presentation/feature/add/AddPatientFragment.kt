package com.trends.patientapplication.presentation.feature.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.trends.patientapplication.core.BaseFragment
import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.presentation.R
import com.trends.patientapplication.presentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : BaseFragment<FragmentAddPatientBinding>(R.layout.fragment_add_patient) {
    private val viewModel: AddPatientViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.buttonAdd.setOnClickListener {
            if (infoIsValid()) {
                val body = getPatientInfo()
                viewModel.addPatient(
                    body
                )
            }
        }
    }

    private fun getPatientInfo(): BodyAddPatientRemoteModel {
        return BodyAddPatientRemoteModel(
            binding.editTextName.text.toString(),
            binding.editTextAddress.text.toString(),
            binding.editTextGender.text.toString(),
            binding.editTextBirthday.text.toString(),
            binding.editTextMobile.text.toString(),
            binding.editTextEmail.text.toString(),
        )
    }

    private fun infoIsValid(): Boolean {
        var isValid = true

        setTextErrorNull()

        if (binding.editTextName.text?.isEmpty() == true) {
            isValid = false
            binding.textName.error = "Name is empty"
        }

        if (binding.editTextEmail.text?.isEmpty() == true) {
            isValid = false
            binding.textEmail.error = "Email is empty"
        }

        if (binding.editTextAddress.text?.isEmpty() == true) {
            isValid = false
            binding.textAddress.error = "Address is empty"
        }

        if (binding.editTextBirthday.text?.isEmpty() == true) {
            isValid = false
            binding.textBirthday.error = "Birthday is empty"
        }

        if (binding.editTextGender.text?.isEmpty() == true) {
            isValid = false
            binding.textGender.error = "Gender is empty"
        }

        if (binding.editTextMobile.text?.isEmpty() == true) {
            isValid = false
            binding.textMobile.error = "Mobile is empty"
        }
        return isValid
    }

    private fun setTextErrorNull() {
        binding.textName.error = null
        binding.textEmail.error = null
        binding.textAddress.error = null
        binding.textBirthday.error = null
        binding.textGender.error = null
        binding.textMobile.error = null
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.addPatientSuccess.collect {
                if (it != null) {
                    Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.addPatientError.collect {
                if (it != null) {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        lifecycleScope.launch {
            lifecycleScope.launch {
                viewModel.addPatientLoading.collect {
                    binding.progress.isVisible = it
                }
            }
        }
    }

}




