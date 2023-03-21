package com.trends.patientapplication.presentation.features.patient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import com.trends.patientapplication.data.repository.PatientRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val patientRepository: PatientRepositoryImp) : ViewModel() {
    val patientsListSuccess = MutableStateFlow<List<PatientRemoteModel>>(emptyList())
    val patientsListError = MutableStateFlow<Exception?>(null)
    val patientsListLoading = MutableStateFlow(true)

    init {
        getPatients()
    }

    private fun getPatients() {
        viewModelScope.launch {
            try {
                 patientsListSuccess.emit(patientRepository.getPatients())
            }catch (e:Exception){
                patientsListError.emit(e)
            }
            patientsListLoading.emit(false)
        }

    }
}