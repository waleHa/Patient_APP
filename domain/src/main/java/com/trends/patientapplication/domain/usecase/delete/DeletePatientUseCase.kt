package com.trends.patientapplication.domain.usecase.delete

import com.trends.patientapplication.domain.model.delete.DeletePatientResponseModel
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(private val repository: PatientRepository){
    suspend operator fun invoke(id:String): DeletePatientResponseModel = repository.deletePatient(id)
}