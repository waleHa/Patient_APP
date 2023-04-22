package com.trends.patientapplication.domain.usecase.add

import com.trends.patientapplication.domain.model.add.AddPatientRequest
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientRepository) {
    suspend operator fun invoke(addPatientRequest: AddPatientRequest) = repository.addPatients(addPatientRequest)

}