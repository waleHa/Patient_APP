package com.trends.patientapplication.presentation.feature.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.domain.model.add.AddPatientResponse
import com.trends.patientapplication.domain.model.add.AddPatientRequest
import com.trends.patientapplication.domain.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(val addPatientUseCase: AddPatientUseCase) :
    ViewModel() {

    private val _addPatientSuccess = MutableStateFlow<AddPatientResponse?>(null)
    val addPatientSuccess: StateFlow<AddPatientResponse?> = _addPatientSuccess

    private val _addPatientError = MutableStateFlow<Exception?>(null)
    val addPatientError: StateFlow<Exception?> = _addPatientError

    private val _addPatientLoading = MutableStateFlow<Boolean>(false)
    val addPatientLoading: StateFlow<Boolean> = _addPatientLoading

    fun addPatient(addPatientRequest: AddPatientRequest) {
        viewModelScope.launch {
            _addPatientLoading.emit(true)
            try {
                _addPatientSuccess.emit(addPatientUseCase(addPatientRequest))
            } catch (e: Exception) {
                _addPatientError.emit(e)
            }
            _addPatientLoading.emit(false)
        }
    }
}