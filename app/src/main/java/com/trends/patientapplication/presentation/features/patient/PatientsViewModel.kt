package com.trends.patientapplication.presentation.features.patient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.domain.usecase.patient.GetPatientSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val getPatientSortedByNameUseCase: GetPatientSortedByNameUseCase) : ViewModel() {
    val patientsListSuccess = MutableStateFlow<List<com.trends.patientapplication.domain.model.patient.PatientRemoteModel>>(emptyList())
    val patientsListError = MutableStateFlow<Exception?>(null)
    val patientsListLoading = MutableStateFlow(true)

    init {
        getPatients()
    }

    private fun getPatients() {
        viewModelScope.launch {
            try {
                 patientsListSuccess.emit(getPatientSortedByNameUseCase())
            }catch (e:Exception){
                patientsListError.emit(e)
            }
            patientsListLoading.emit(false)
        }

    }
}