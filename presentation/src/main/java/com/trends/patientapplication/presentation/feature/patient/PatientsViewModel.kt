package com.trends.patientapplication.presentation.feature.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.domain.model.delete.DeletePatientResponse
import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.domain.usecase.delete.DeletePatientUseCase
import com.trends.patientapplication.domain.usecase.patient.GetPatientSortedByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val getPatientSortedByNameUseCase: GetPatientSortedByNameUseCase, private val deletePatientUseCase: DeletePatientUseCase) : ViewModel() {
    private val _patientsListSuccess = MutableStateFlow<List<PatientResponse>>(emptyList())
    val patientsListSuccess : StateFlow<List<PatientResponse>> = _patientsListSuccess

    private val _patientsError = MutableStateFlow<Exception?>(null)
    val patientsError : StateFlow<Exception?> = _patientsError

    private val _patientsListLoading = MutableStateFlow(true)
    val patientsListLoading : StateFlow<Boolean> = _patientsListLoading

     private val _deletePatientsSuccess = MutableLiveData<DeletePatientResponse?>()
    val deletePatientsSuccess : LiveData<DeletePatientResponse?> = _deletePatientsSuccess

    init {
        getPatients()
    }

    fun getPatients() {
        viewModelScope.launch {
            _patientsListLoading.emit(true)
            try {
                 _patientsListSuccess.emit(getPatientSortedByNameUseCase())
            }catch (e:Exception){
                _patientsError.emit(e)
            }
            _patientsListLoading.emit(false)
        }
    }
    
    fun deletePatient(id:String){
        viewModelScope.launch {
            _patientsListLoading.emit(true)
            try {
                _deletePatientsSuccess.postValue(deletePatientUseCase(id))
            }catch (e:Exception){
                _patientsError.emit(e)
            }
            _patientsListLoading.emit(false)
        }
    }
}