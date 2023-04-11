package com.trends.patientapplication.domain.usecase.add

import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val repository: PatientRepository) {
    suspend operator fun invoke(bodyAddPatientRemoteModel: BodyAddPatientRemoteModel) = repository.addPatient(bodyAddPatientRemoteModel)

}