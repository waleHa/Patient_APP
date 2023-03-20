package com.trends.patientapplication.features.patients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.model.PatientRemoteModel
import com.trends.patientapplication.repository.PatientRepository
import com.trends.patientapplication.repository.PatientRepositoryImp
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