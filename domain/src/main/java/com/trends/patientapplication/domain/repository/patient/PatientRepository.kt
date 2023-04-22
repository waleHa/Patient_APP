package com.trends.patientapplication.domain.repository.patient

import com.trends.patientapplication.domain.model.add.AddPatientResponse
import com.trends.patientapplication.domain.model.add.AddPatientRequest
import com.trends.patientapplication.domain.model.delete.DeletePatientResponse
import com.trends.patientapplication.domain.model.patient.PatientResponse

interface PatientRepository {
    suspend fun getPatients(): List<PatientResponse>
    suspend fun  addPatients(bodyAddPatientModel: AddPatientRequest): AddPatientResponse
    suspend fun deletePatient(id: String): DeletePatientResponse
    suspend fun getPatientById(id: String): PatientResponse
}