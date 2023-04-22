package com.trends.patientapplication.domain.repository.patient

import com.trends.patientapplication.domain.model.add.AddPatientRemoteModel
import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.domain.model.delete.DeletePatientResponseModel
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients(): List<PatientRemoteModel>
    suspend fun  addPatients(bodyAddPatientModel: BodyAddPatientRemoteModel): AddPatientRemoteModel
    suspend fun deletePatient(id: String): DeletePatientResponseModel
    suspend fun getPatientById(id: String): PatientRemoteModel
}