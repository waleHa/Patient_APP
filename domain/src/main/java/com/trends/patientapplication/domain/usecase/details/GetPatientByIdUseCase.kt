package com.trends.patientapplication.domain.usecase.details

import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject

class GetPatientByIdUseCase @Inject constructor(private val repository: PatientRepository) {
    suspend operator fun invoke(id: String): PatientResponse
        = repository.getPatientById(id)

}