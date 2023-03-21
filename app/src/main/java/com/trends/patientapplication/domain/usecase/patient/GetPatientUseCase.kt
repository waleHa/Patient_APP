package com.trends.patientapplication.domain.usecase.patient

import com.trends.patientapplication.domain.repository.PatientRepository
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(private val repository: PatientRepository) {
    suspend operator fun invoke() = repository.getPatients().sortedBy { it.name }

}