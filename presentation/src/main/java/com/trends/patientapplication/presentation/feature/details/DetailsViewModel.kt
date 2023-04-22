package com.trends.patientapplication.presentation.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.domain.usecase.details.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsSuccessStateFlow: MutableStateFlow<PatientResponse?> =
        MutableStateFlow(null)
    val detailsSuccessStateFlow = _detailsSuccessStateFlow.asStateFlow()

    private val _detailsLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow: StateFlow<Boolean> = _detailsLoadingStateFlow

    private val _detailsErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStateFlow: StateFlow<Exception?> = _detailsErrorStateFlow

    private val state = savedStateHandle
    init{
        details()
    }
    fun details() {
        val id = state.get<String>("id") ?: "-1"
        viewModelScope.launch {
            _detailsLoadingStateFlow.emit(true)
            try {
                _detailsSuccessStateFlow.emit(getPatientByIdUseCase(id))
            } catch (exception: Exception) {
                _detailsErrorStateFlow.emit(exception)
            }
            _detailsLoadingStateFlow.emit(false)
        }
    }
}